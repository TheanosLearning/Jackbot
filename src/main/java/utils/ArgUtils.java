package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArgUtils {

    protected static final Logger LOG = LoggerFactory.getLogger(ArgUtils.class);

    public static boolean isValid(String[] args) {
        boolean valid;
        if(args.length < 2) {
            LOG.error("Jackbot needs your permission to scrap. Please provide a username & password.");
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }

}
