package Controller.ArgumentHandlers;
import com.sun.deploy.util.StringUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.apache.commons.cli.CommandLine;


import java.util.*;

import static Controller.ArgumentHandlers.ArgumentHandler.*;
import static Controller.Enums.MathematicaArgumentKeys.*;

/**
 * Created by Etrag on 27/01/2017.
 */
public class MathematicaArgumentHandler implements ArgumentHandler{

    public Dictionary<String, String> parseArgs(CommandLine cl) {
        //Format for mathematica arguments is as follows:
        // Package_Name | Package Arguments | Link arguments | Executable Location
        Dictionary<String,String> argDict = new Hashtable<String,String>();
        argDict.put(GENPACKAGENAME, cl.getOptionValue(GENPACKAGENAME));
        argDict.put(GENPACKAGEARGUMENTS,cl.getOptionValue(GENPACKAGEARGUMENTS));
        System.out.println(cl.getOptionValue(GENKERNELPATH));
        argDict.put(GENKERNELPATH,"\"" + cl.getOptionValue(GENKERNELPATH) + "\"");
        System.out.println(cl.getOptionValue(GENKERNELARGS));
        argDict.put(GENKERNELARGS,cl.getOptionValue(GENKERNELARGS));
        return argDict;
    }
}
