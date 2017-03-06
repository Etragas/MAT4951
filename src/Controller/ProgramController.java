package Controller;

import Controller.ArgumentHandlers.*;
import Controller.ArgumentHandlers.Parser;
import Controller.FunctionGenerators.FunctionGenerator;
import Controller.FunctionMinimizers.FunctionMinimizer;
import Controller.SessionHandlers.*;
import com.sun.deploy.util.StringUtils;
import com.sun.deploy.util.SystemUtils;
import com.wolfram.jlink.ExprFormatException;
import com.wolfram.jlink.MathLinkException;
import matlabcontrol.*;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;
import static Controller.ArgumentHandlers.ArgumentHandler.*;

/**
 * Created by Etrag on 27/01/2017.
 */

/*
This class is responsible for keeping track of every component of the program.
It implements the logic of parsing a user request, setting up the appropriate resources to fulfill it and then executing
 */
public class ProgramController {
    //The file that will generate
    private static final String GENERATORDELIM = "GENERATOR";
    private static final String MINIMIZERDELIM = "MINIMIZER";
    private static final String SIMULATIONDELIM = "SIMULATION";

    static FunctionGenerator functionGenerator;
    static GeneratorSession generatorSession;
    static Dictionary<String,String> generatorParams;

    static FunctionMinimizer functionMinimizer;
    static MinimizerSession minimizerSession;
    static Dictionary<String,String> minimizerParams;

    static SimulationEngine simulationEngine;
    static Dictionary<String,String> simulationParams;


    public static void main(String[] args) throws MathLinkException, ParseException, MatlabConnectionException, MatlabInvocationException, ExprFormatException {

        if (System.getProperty("os.name").contains("Windows")) {
            File dll = new File("lib/JlinkNativeLibrary.dll");
            System.load(dll.getAbsolutePath());
        }
        if (System.getProperty("os.name").contains("OSX")) {
            String jLinkDir = "/Applications/Mathematica.app/Contents/SystemFiles/Links/JLink/JLink.jar";
            System.setProperty("com.wolfram.jlink.libdir", jLinkDir);
        }


        Options options = new Options();
        for (String arg : GENARGS){
            options.addOption(arg,true,"");
        }
        for (String arg : MINARGS){
            options.addOption(arg,true,"");
        }
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        //        options.addOption(GENPACKAGENAME,true,"The package that will be loaded into the kernel");
//        options.addOption(GENPACKAGEARGUMENTS,true,"The package arguments");
//        options.addOption(GENKERNELPATH,true,"The Kernel executable");
//        options.addOption(GENKERNELARGS,true,"The arguments for the kernel");
        CommandLineParser parser = new Parser();
        CommandLine line = parser.parse(options,args);

        //Its structure is GENERATOR ... MINIMIZER ... SIMULATION ...
        generatorParams = new MathematicaArgumentHandler().parseArgs(line);
        generatorSession = new MathematicaSession(generatorParams);
        functionGenerator= generatorSession.buildFunctionGenerator(generatorParams);
        System.out.println(functionGenerator.getFunction());
        minimizerParams = new MatlabArgumentHandler().parseArgs(line);
        //Create a proxy, which we will use to control MATLAB
        minimizerSession = new MatlabSession(minimizerParams);
        functionMinimizer = minimizerSession.buildFunctionMinimizer(minimizerParams);
        functionMinimizer.minimize(functionGenerator.getFunction());


        //Display 'hello world' just like when using the demo
        //proxy.eval("disp('hello world')");


        //Disconnect the proxy from MATLAB
//        proxy.disconnect();


    }
}
