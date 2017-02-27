package Controller.FunctionGenerators;

import com.wolfram.jlink.Expr;
import com.wolfram.jlink.ExprFormatException;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;

import java.io.File;

import static java.lang.String.*;

/**
 * Created by Etrag on 27/01/2017.
 */
public class MathematicaFunctionGenerator implements FunctionGenerator {
    String packageName;
    String packageArguments;
    KernelLink link;
    static final String FUNCTION_NAME = "\"function_to_minimize.m\"";

    public MathematicaFunctionGenerator(String packageName, String packageArguments, KernelLink link) throws MathLinkException {
        this.packageName = packageName;
        this.packageArguments = packageArguments;
        this.link = link;
        this.link.discardAnswer();

        this.link.evaluate("<<MyPackage.m");
        this.link.discardAnswer();

    }

    @Override
    public String getFunction() throws MathLinkException, ExprFormatException {
        System.out.println(this.packageName);

        File min_file = new File("Files/fun_to_min.m");
        System.out.println("This is the path" + min_file.getParentFile().getAbsolutePath());
        link.evaluate(String.format("SetDirectory[\"%s\"]",min_file.getParentFile().getAbsolutePath()));
        link.discardAnswer();
        String expr = link.evaluateToOutputForm(String.format("ReadString[\"%s\"]",min_file.getName()),0);
        link.evaluate("Needs[\"ToMatlab`\"]");
        link.discardAnswer();
        System.out.println(link.evaluateToOutputForm("Directory[]",0));

        String function = link.evaluateToOutputForm(format("ToMatlab[ToExpression[\"%s\",TraditionalForm]]",expr),0);
        link.evaluate("Close[f]");

        //        link.waitForAnswer();
//        System.out.println("Meow");
//        link.evaluate("2+2");
//        link.waitForAnswer();

//        return String.valueOf(link.getInteger());
        return function;
//        link.evaluate(packageName + " " + packageArguments);
//        link.waitForAnswer();
//        //Do the mathematica stuff here;
//        return link.getString();
    }
}
