package logging;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Class responsible for providing a logger
 */
public class Logging {

    private static Logger logger;

    private Logging() {
        BasicConfigurator.configure();
        logger.setLevel(Level.INFO);
    }

    public static Logger log() {
        if (logger == null) {
            logger = Logger.getRootLogger();
        }
        return logger;
    }
}