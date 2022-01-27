package base;

import data.GuestUser;
import data.TestUser;
import driver.manager.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.application.PrestaShop;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;

import static driver.manager.DriverManagerFactory.getManager;
import static io.qameta.allure.Allure.addAttachment;
import static java.lang.System.currentTimeMillis;
import static org.testng.ITestResult.SKIP;

/**
 * BaseTest class that is responsible for test set up and tear down
 */
public class BaseTest {

    private DriverManager driverManager;
    private WebDriver driver;
    protected PrestaShop prestaShop;
    protected TestUser testUser;
    protected GuestUser guestUser;

    /**
     * Creates or gets Test and Guest user before running test classes
     */
    @BeforeTest
    protected void prepareTestUsers() {
        testUser = TestUser.getUser();
        guestUser = GuestUser.getUser();
    }

    /**
     * Prepares Driver and its Manager, finally it creates a new PrestaShop instance before each test
     *
     * @param browser     browser on which tests will be run
     * @param environment base url of the application
     */
    @BeforeMethod
    @Parameters({"browser", "environment"})
    protected void setUp(String browser, String environment) {
        driverManager = getManager(browser);
        driver = driverManager.getDriver();
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
        driverManager.quitDriver();
    }

    /**
     * Quits Driver Service via manager after all tests
     */
    @AfterSuite(alwaysRun = true)
    protected void stopService() {
        driverManager.stopService();
    }
}