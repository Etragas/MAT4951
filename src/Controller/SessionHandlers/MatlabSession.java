package Controller.SessionHandlers;

import Controller.FunctionMinimizers.FunctionMinimizer;
import Controller.FunctionMinimizers.MatlabFunctionMinimizer;
import com.wolfram.jlink.MathLinkException;
import matlabcontrol.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import static Controller.ArgumentHandlers.ArgumentHandler.*;

/**
 * Created by Etrag on 03/02/2017.
 */
public class MatlabSession extends MinimizerSession {

    MatlabProxyFactoryOptions.Builder builder;
    MatlabProxyFactoryOptions matlabProxyFactoryOptions;
    MatlabProxyFactory factory;
    MatlabProxy proxy;
    public MatlabSession(Dictionary<String, String> minimizerParams) {
        builder = new MatlabProxyFactoryOptions.Builder();

        if (minimizerParams.get(MINKERNELPATH) != null){
            builder.setMatlabLocation(minimizerParams.get(MINKERNELPATH));
        }
        //Handle args
        //Handle file
        //builder.setLicenseFile();
        matlabProxyFactoryOptions = builder.build();
        this.factory = new MatlabProxyFactory(matlabProxyFactoryOptions);
        this.proxy = buildProxy();
    }

    public MatlabProxy buildProxy(){
        try {
        this.proxy = factory.getProxy();
        return this.proxy;}
        catch (Exception e) {
            System.out.println("Connection Failed");
            return null;
        }
    }

    @Override
    public FunctionMinimizer buildFunctionMinimizer(Dictionary<String, String> args) throws MathLinkException {
        FunctionMinimizer fm = new MatlabFunctionMinimizer() {
            @Override
            public List<Float> minimize(String function) throws MatlabInvocationException {
                function = "'" + function.replace("\n","").replace("...","").replaceAll("\\s+","").replace(";","") + "'";
                System.out.println(function);
                System.out.println(proxy.isConnected());
                proxy.returningEval(String.format("cd(\'%s\')",System.getProperty("user.dir")+"/Files"),0);
                Object[] results = proxy.returningEval(String.format("minimizer(%s);",function),1);
                double[] params = (double[]) results[0];
                System.out.println(params);
                System.out.println(params[0]);
                System.out.println(params[1]);
                System.out.println(params[2]);
                System.out.println(params[3]);
                List<Float> floatzl = new ArrayList<>();
                floatzl.add((float) 3.0);
                return floatzl;
            }
        };
        return fm;
    }
}
