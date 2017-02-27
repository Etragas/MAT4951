package Controller.ArgumentHandlers;

import org.apache.commons.cli.CommandLine;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Etrag on 27/01/2017.
 */
public interface ArgumentHandler {

    public static final String GENARGINDICATOR = "genArgs";
    public static final String MINARGINDICATOR= "minArgs";
    public static final String SIMARGINDICATOR = "simArgs";

    public static final String GENPACKAGENAME ="genPackage";
    public static final String GENPACKAGEARGUMENTS ="genPackageArguments";
    public static final String GENKERNELPATH ="genKernel";
    public static final String GENKERNELARGS ="genKernelArguments";
    public static final List<String> GENARGS = Arrays.asList(GENPACKAGENAME, GENPACKAGEARGUMENTS,
                                                GENKERNELPATH, GENKERNELARGS);

    public static final String MINPACKAGENAME ="minPackage";
    public static final String MINPACKAGEARGUMENTS ="minPackageArguments";
    public static final String MINKERNELPATH ="minKernel";
    public static final String MINKERNELARGS ="minKernelArguments";
    public static final List<String> MINARGS = Arrays.asList(MINPACKAGEARGUMENTS,MINPACKAGENAME,
            MINKERNELARGS,MINKERNELPATH);
    public Dictionary<String, String> parseArgs(CommandLine cl);
}
