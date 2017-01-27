package Controller;

import Controller.FunctionGenerators.FunctionGenerator;
import Controller.FunctionMinimizers.FunctionMinimizer;
import Controller.SessionHandlers.GeneratorSession;
import Controller.SessionHandlers.MathematicaSession;
import Controller.SessionHandlers.SimulationEngine;
import com.sun.deploy.util.StringUtils;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.*;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

import static Enums.MathematicaArgumentKeys.*;

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
    static Dictionary<Enum,String> generatorParams;

    static FunctionMinimizer functionMinimizer;
    static Dictionary<Enum,String> minimizerParams;

    static SimulationEngine simulationEngine;
    static Dictionary<Enum,String> simulationParams;


    public static void main(String[] args) throws MathLinkException {
        List<String> argL = Arrays.asList(StringUtils.join(Arrays.asList(args)," ").split(" "));
        String minimizerArgs = StringUtils.join(argL.subList(argL.indexOf(GENERATORDELIM)+2,argL.indexOf(MINIMIZERDELIM))," ");

        //Parse the configuration file
        int generatorEndIndex = argL.indexOf(MINIMIZERDELIM);
        int minimizerEndIndex = argL.indexOf(SIMULATIONDELIM);

        //Its strucutre is GENERATOR ... MINIMIZER ... SIMULATION ...
        generatorParams = ArgumentHandlers.MathematicaArgumentHandler.parseArgs(minimizerArgs.split("\\s*[|]\\s*"));
        generatorSession = new MathematicaSession(generatorParams);
        functionGenerator= generatorSession.buildFunctionGenerator(generatorParams);
        System.out.println(functionGenerator.getFunction());

    }
}
