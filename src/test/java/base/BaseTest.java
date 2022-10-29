package base;

import data.GuestUser;
import data.TestUser;
import driver.factory.WebDriverFactory;
import driver.types.DriverTypes;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.application.PrestaShop;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;

import static io.qameta.allure.Allure.addAttachment;
import static io.restassured.RestAssured.get;
import static java.lang.System.currentTimeMillis;
import static org.testng.ITestResult.SKIP;

/**
 * BaseTest class that is responsible for test set up and tear down
 */
@Slf4j
public class BaseTest {

    private WebDriver driver;
    protected PrestaShop prestaShop;
    protected TestUser testUser;
    protected GuestUser guestUser;

    @BeforeSuite
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

    /**
     * Creates or gets Test and Guest user before running test classes
     */
    @BeforeTest
    protected void prepareTestUsers() {
        testUser = TestUser.getUser();
        guestUser = GuestUser.getUser();
    }

    @BeforeMethod
    @Parameters({"browser", "environment"})
    protected void setUp(String browser, String environment) {
        driver = new WebDriverFactory().getDriver(DriverTypes.valueOf(browser));
        driver.get(environment);
        prestaShop = new PrestaShop(driver);
    }

    /**
     * Quits the driver and screenshots entire page upon fail after each test
     *
     * @param result result of a test needed to determine whether screenshot is needed
     */
    @AfterMethod(alwaysRun = true)
    protected void tearDown(ITestResult result) {
        if (!result.isSuccess() && result.getStatus() != SKIP) {
            addAttachment("Test failure " + new Timestamp(currentTimeMillis()),
                          new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        driver.quit();
    }
}