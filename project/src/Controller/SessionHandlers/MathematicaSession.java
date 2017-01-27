package Controller.SessionHandlers;
import Controller.FunctionGenerators.FunctionGenerator;
import Controller.FunctionGenerators.MathematicaFunctionGenerator;
import com.wolfram.jlink.*;

import java.util.Dictionary;
import static Enums.MathematicaArgumentKeys.*;
/**
 * Created by Etrag on 27/01/2017.
 */
public class MathematicaSession implements GeneratorSession{
    String[] linkArguments;
    FunctionGenerator functionGenerator;
    KernelLink link;

    public MathematicaSession(Dictionary <Enum,String> sessionArgs) {
        //this.linkArguments = sessionArgs.get(LINK_ARGUMENTS)  + " " + "\"" +sessionArgs.get(EXECUTABLE_PATH) + "\"";
        this.linkArguments = new String[4];
        this.linkArguments[0] = "-linkmode";
        this.linkArguments[1]=  "launch" ;
        this.linkArguments[2] = "-linkname";
        this.linkArguments[3] = "\"c:\\program files\\wolfram research\\mathematica\\11.0\\mathkernel.exe\"";
        System.out.println("THIS IS" + this.linkArguments[0]);
        for (String x : this.linkArguments){
            System.out.println(x);
        }
        try {
            this.link = MathLinkFactory.createKernelLink(linkArguments);
        } catch (MathLinkException e) {
            System.out.println("Fatal error opening link: " + e.getMessage());
        }
    }

    @Override
    public FunctionGenerator buildFunctionGenerator(Dictionary <Enum,String> sessionArgs) throws MathLinkException {
        this.functionGenerator = new MathematicaFunctionGenerator(sessionArgs.get(PACKAGE_NAME),
                                                                    sessionArgs.get(PACKAGE_ARGUMENTS),
                                                                    this.link);
        return this.functionGenerator;
    }
}
