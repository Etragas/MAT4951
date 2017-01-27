package ArgumentHandlers;
import Enums.MathematicaArgumentKeys;
import Enums.MathematicaArgumentKeys.*;
import java.util.Dictionary;
import java.util.Hashtable;

import static Enums.MathematicaArgumentKeys.*;

/**
 * Created by Etrag on 27/01/2017.
 */
public class MathematicaArgumentHandler {

    public static Dictionary<Enum, String> parseArgs(String[] args) {
        //Format for mathematica arguments is as follows:
        // Package_Name | Package Arguments | Link arguments | Executable Location
        Dictionary<Enum,String> argDict = new Hashtable<Enum,String>();
        argDict.put(PACKAGE_NAME,args[0]);
        argDict.put(PACKAGE_ARGUMENTS,args[1]);
        argDict.put(LINK_ARGUMENTS,args[2]);
        argDict.put(EXECUTABLE_PATH,args[3]);
        return argDict;
    }
}
