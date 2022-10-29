package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

/**
 * A test class that checks app's availability and exits the program if it is not
 */
@Slf4j
public final class HealthCheckTest {

    @BeforeSuite
    @Test
    @Parameters({"environment"})
    public void healthCheck(String environment) {
        log.info("Running health check...");
        int statusCode = get(environment).then().extract().response().statusCode();
        if (statusCode != 200) {
            log.info(String.format("Health check failed with status code: %s. Tests will not run.", statusCode));
            System.exit(0);
        }
        log.info(String.format("Health check ended with status code: %s.", statusCode));
    }
}