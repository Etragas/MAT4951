package Controller.ArgumentHandlers;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.List;

/**
 * Created by Etrag on 02/02/2017.
 */
public class Parser extends DefaultParser {


    private boolean isOption(String token) {
        Options opts = this.options;
        List<String> matches = opts.getMatchingOptions(token);
        if (matches == null){
            return false;
        }
        else{
            System.out.println(token);
            System.out.println(matches.toString());
            return matches.contains(token);
        }
    }

}
