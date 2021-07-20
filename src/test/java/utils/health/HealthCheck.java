package utils.health;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

/**
 * Class responsible for providing a method to check the app's availability.
 */
public final class HealthCheck {

    @Test
    @Parameters({"environment"})
    public void healthCheck(String environment) {
        int statusCode = checkAppsAvailability(environment);
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