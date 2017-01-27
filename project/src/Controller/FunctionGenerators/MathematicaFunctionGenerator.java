package Controller.FunctionGenerators;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;

/**
 * Created by Etrag on 27/01/2017.
 */
public class MathematicaFunctionGenerator implements FunctionGenerator {
    String packageName;
    String packageArguments;
    KernelLink link;
    public MathematicaFunctionGenerator(String packageName, String packageArguments, KernelLink link) throws MathLinkException {
        this.packageName = packageName;
        this.packageArguments = packageArguments;
        this.link = link;
        this.link.discardAnswer();

        this.link.evaluate("<<MyPackage.m");
        this.link.discardAnswer();

    }

    @Override
    public String getFunction() throws MathLinkException {

        link.evaluate("2+2");
        link.waitForAnswer();

        return String.valueOf(link.getInteger());
//        link.evaluate(packageName + " " + packageArguments);
//        link.waitForAnswer();
//        //Do the mathematica stuff here;
//        return link.getString();
    }
}
