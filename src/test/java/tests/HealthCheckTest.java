package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static logging.Logging.log;

/**
 * A test class that checks app's availability and exits the program if it is not
 */
public final class HealthCheckTest {

    @Test
    @Parameters({"environment"})
    public void healthCheck(String environment) {
        log().info("Running health check...");
        int statusCode = get(environment).then().extract().response().statusCode();
        log().info("Health check ended with status code: " + statusCode);
        if (statusCode != 200) {
            log().info("Health check failed with status code: " + statusCode + " Tests will not run.");
            System.exit(0);
        }
    }
}