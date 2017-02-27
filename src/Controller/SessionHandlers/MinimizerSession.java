package Controller.SessionHandlers;

import Controller.FunctionGenerators.FunctionGenerator;
import Controller.FunctionMinimizers.FunctionMinimizer;
import com.wolfram.jlink.MathLinkException;

import java.util.Dictionary;

/**
 * Created by Etrag on 03/02/2017.
 */
public abstract class MinimizerSession {
    public abstract FunctionMinimizer buildFunctionMinimizer(Dictionary<String, String> args) throws MathLinkException;

}
