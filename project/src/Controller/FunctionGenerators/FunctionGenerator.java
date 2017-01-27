package Controller.FunctionGenerators;

import com.wolfram.jlink.MathLinkException;

/**
 * Created by Etrag on 27/01/2017.
 */

/*
This class is responsible for generating the function which will be minimized.
In cases where the function is already available, this class may be omitted.
 */
public interface FunctionGenerator {

    public String getFunction() throws MathLinkException;
}
