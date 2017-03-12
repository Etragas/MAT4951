package Controller.FunctionMinimizers;

import matlabcontrol.MatlabInvocationException;

import java.util.List;

/**
 * Created by Etrag on 27/01/2017.
 */
public abstract class FunctionMinimizer {
    public List<Float> minimizationParameters() {
        return null;
    }

    public List<Float> minimize(List<String> variables, String function) throws MatlabInvocationException {
        return null;
    }
}
