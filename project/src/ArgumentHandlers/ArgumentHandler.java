package ArgumentHandlers;

import java.util.Dictionary;

/**
 * Created by Etrag on 27/01/2017.
 */
public interface ArgumentHandler {
    public Dictionary<Enum,String> parseArgs(String args[]);
}