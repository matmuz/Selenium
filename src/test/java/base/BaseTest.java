package base;

import data.GuestUser;
import data.TestUser;
import driver.manager.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.application.PrestaShop;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;

import static driver.manager.DriverManagerFactory.getManager;
import static io.qameta.allure.Allure.addAttachment;
import static java.lang.System.currentTimeMillis;
import static org.testng.ITestResult.SKIP;

/**
 * BaseTest class that is responsible for test preparation
 */
public class BaseTest {

    private DriverManager driverManager;
    private WebDriver driver;
    protected PrestaShop prestaShop;
    protected TestUser testUser;
    protected GuestUser guestUser;

    @BeforeTest
    public void prepareTestUsers() {
        testUser = TestUser.getUser();
        guestUser = GuestUser.getUser();
    }

    @BeforeMethod
    @Parameters({"browser", "environment"})
    public void setUp(String browser, String environment) {
        driverManager = getManager(browser);
        driver = driverManager.getDriver();
        driver.get(environment);
        prestaShop = new PrestaShop(driver);
    }

    @AfterMethod()
    public void tearDown(ITestResult result) {
        if (!result.isSuccess() && result.getStatus() != SKIP) {
            addAttachment("Test failure " + new Timestamp(currentTimeMillis()),
                          new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        driverManager.quitDriver();
    }
}