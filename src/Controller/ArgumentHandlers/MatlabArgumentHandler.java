package Controller.ArgumentHandlers;

import org.apache.commons.cli.CommandLine;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Etrag on 27/01/2017.
 */
public class MatlabArgumentHandler implements ArgumentHandler{

    public Dictionary<String, String> parseArgs(CommandLine cl) {
        //Format for mathematica arguments is as follows:
        // Package_Name | Package Arguments | Link arguments | Executable Location
        Dictionary<String,String> argDict = new Hashtable<String,String>();
        argDict.put(MINKERNELPATH,cl.getOptionValue(MINKERNELPATH));
        argDict.put(MINKERNELARGS,cl.getOptionValue(MINKERNELARGS));
        return argDict;
    }

}
