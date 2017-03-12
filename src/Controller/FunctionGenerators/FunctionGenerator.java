package Controller.FunctionGenerators;

import com.wolfram.jlink.ExprFormatException;
import com.wolfram.jlink.MathLinkException;

import java.util.List;

/**
 * Created by Etrag on 27/01/2017.
 */

/*
This class is responsible for generating the function which will be minimized.
In cases where the function is already available, this class may be omitted.
 */
public interface FunctionGenerator {

    public List<String> getVariables();
    public String getFunction() throws MathLinkException, ExprFormatException;
    public void initFunction() throws MathLinkException, ExprFormatException;

}
