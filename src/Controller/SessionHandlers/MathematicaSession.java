package Controller.SessionHandlers;
import Controller.FunctionGenerators.FunctionGenerator;
import Controller.FunctionGenerators.MathematicaFunctionGenerator;
import com.wolfram.jlink.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

import static Controller.ArgumentHandlers.ArgumentHandler.*;
import static Controller.Enums.MathematicaArgumentKeys.*;
/**
 * Created by Etrag on 27/01/2017.
 */
public class MathematicaSession implements Controller.SessionHandlers.GeneratorSession {
    private List<String> linkArguments;
    FunctionGenerator functionGenerator;
    KernelLink link;

    public MathematicaSession(Dictionary <String,String> sessionArgs) {
        this.linkArguments = new ArrayList<String>(Arrays.asList(sessionArgs.get(GENKERNELARGS).split(" ")));
        this.linkArguments.add(sessionArgs.get(GENKERNELPATH));
        System.out.println(this.linkArguments);

        try {
            this.link = MathLinkFactory.createKernelLink(linkArguments.toArray(new String[0]));
        } catch (MathLinkException e) {
            System.out.println("Fatal error opening link: " + e.getMessage());
        }
    }

    @Override
    public FunctionGenerator buildFunctionGenerator(Dictionary <String,String> sessionArgs) throws MathLinkException {
        this.functionGenerator = new MathematicaFunctionGenerator(sessionArgs.get(GENPACKAGENAME),
                                                                    sessionArgs.get(GENPACKAGEARGUMENTS),
                                                                    this.link);
        return this.functionGenerator;
    }
}
