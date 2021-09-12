package utils.health;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

/**
 * Class responsible for providing a method to check the app's availability.
 */
public final class HealthCheck {

    private final static Logger LOGGER = Logger.getRootLogger();

    private HealthCheck() {
        BasicConfigurator.configure();
        LOGGER.setLevel(Level.INFO);
    }

    @Test
    @Parameters({"environment"})
    public void healthCheck(String environment) {
        LOGGER.info("Running health check...");
        int statusCode = checkAppsAvailability(environment);
        LOGGER.info("Health check ended with status code: " + statusCode);
        if (statusCode != 200) {
            LOGGER.info("Health check failed with status code: " + statusCode + " Tests will not run.");
            System.exit(0);
        }
    }

    private static int checkAppsAvailability(String environment) {
        return get(environment).then()
                .extract()
                .response()
                .statusCode();
    }
}