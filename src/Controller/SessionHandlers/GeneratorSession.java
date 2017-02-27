package Controller.SessionHandlers;

import Controller.FunctionGenerators.FunctionGenerator;
import com.wolfram.jlink.MathLinkException;

import java.util.Dictionary;

/**
 * Created by Etrag on 27/01/2017.
 */
public interface GeneratorSession {
    public FunctionGenerator buildFunctionGenerator(Dictionary<String,String> args) throws MathLinkException;
}
