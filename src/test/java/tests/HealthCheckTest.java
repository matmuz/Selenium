package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

/**
 * A test class that checks app's availability and exits the program if it is not
 */
public final class HealthCheckTest {

    @Test
    @Parameters({"environment"})
    public void healthCheck(String environment) {
        System.out.println("Running health check...");
        int statusCode = get(environment).then().extract().response().statusCode();
        System.out.println(("Health check ended with status code: " + statusCode));
        if (statusCode != 200) {
            System.out.println(("Health check failed with status code: " + statusCode + " Tests will not run."));
            System.exit(0);
        }
    }
}