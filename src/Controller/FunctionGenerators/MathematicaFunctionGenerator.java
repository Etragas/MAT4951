package Controller.FunctionGenerators;

import com.wolfram.jlink.Expr;
import com.wolfram.jlink.ExprFormatException;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.*;

/**
 * Created by Etrag on 27/01/2017.
 */
public class MathematicaFunctionGenerator implements FunctionGenerator {
    String packageName;
    String packageArguments;
    String functionString;
    List<String> variables;
    KernelLink link;
    static final String FUNCTION_NAME = "\"function_to_minimize.m\"";

    public MathematicaFunctionGenerator(String packageName, String packageArguments, KernelLink link) throws MathLinkException {
        this.packageName = packageName;
        this.packageArguments = packageArguments;
        this.link = link;
        this.link.discardAnswer();
        this.variables = new ArrayList<>();
        this.link.evaluate("<<MyPackage.m");
        this.link.discardAnswer();

    }

    public void initFunction() throws MathLinkException, ExprFormatException {
        System.out.println(this.packageName);

        File min_file = new File("Files/fun_to_min.m");
        System.out.println("This is the path" + min_file.getParentFile().getAbsolutePath());
        link.evaluate(String.format("SetDirectory[\"%s\"]",min_file.getParentFile().getAbsolutePath()));
        link.discardAnswer();
        link.evaluate(String.format("file = ReadString[\"%s\"]",min_file.getName()));
        link.discardAnswer();
        link.evaluate("Needs[\"ToMatlab`\"]");
        link.discardAnswer();
        link.evaluate("$NumberMarks = False");
        link.discardAnswer();
        String temp = link.evaluateToOutputForm("ToMatlab[Extract[ToExpression[file,InputForm,],1]]",0);

        System.out.println("function is" + temp);
        this.functionString = temp.replace("\n","").replace("...","").replaceAll("\\s+","").replace(";","");
        
        String variableString = link.evaluateToOutputForm("Variables[Extract[ToExpression[file, InputForm,], 1]]",0);
        Pattern reg = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = reg.matcher(variableString);
        while (m.find()){
            this.variables.add(m.group());
        }; //Keep only numbers and letters

        link.evaluate("Close[f]");

    }

    public List<String> getVariables(){
        return this.variables;
    }
    @Override
    public String getFunction() throws MathLinkException, ExprFormatException {


//        System.out.println(this.packageName);
//
//        File min_file = new File("Files/fun_to_min.m");
//        System.out.println("This is the path" + min_file.getParentFile().getAbsolutePath());
//        link.evaluate(String.format("SetDirectory[\"%s\"]",min_file.getParentFile().getAbsolutePath()));
//        link.discardAnswer();
//        String expr = link.evaluateToOutputForm(String.format("ReadString[\"%s\"]",min_file.getName()),0);
//        link.evaluate("Needs[\"ToMatlab`\"]");
//        link.discardAnswer();
//        link.evaluate("$NumberMarks = False");
//        link.discardAnswer();
//        link.evaluate("Needs[\"ToMatlab`\"]");
//        link.discardAnswer();
//        System.out.println(link.evaluateToOutputForm("Directory[]",0));
//
//        String function = link.evaluateToOutputForm(format("ToMatlab[Extract[ToExpression[ReadString[\"%s\"],TraditionalForm,],1]]",min_file.getName()),0);
//        link.evaluate("Close[f]");

        //        link.waitForAnswer();
//        System.out.println("Meow");
//        link.evaluate("2+2");
//        link.waitForAnswer();

//        return String.valueOf(link.getInteger());
        return this.functionString;
//        link.evaluate(packageName + " " + packageArguments);
//        link.waitForAnswer();
//        //Do the mathematica stuff here;
//        return link.getString();
    }
}
