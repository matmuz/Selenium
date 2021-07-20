package utils.health;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

/**
 * Class responsible for providing a method to check the app's availability.
 */
public final class HealthCheck {

    private final static Logger logger = Logger.getLogger(HealthCheck.class);

    @Test
    @Parameters({"environment"})
    public void healthCheck(String environment) {
        BasicConfigurator.configure();
        logger.info("Running health check...");
        int statusCode = checkAppsAvailability(environment);
        logger.info("Health check ended with status code: " + statusCode);
        if (statusCode != 200) {
            System.out.println("Health check failed with status code:" + statusCode + " Tests will not run.");
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