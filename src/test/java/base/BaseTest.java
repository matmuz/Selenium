package base;

import data.GuestUser;
import data.TestUser;
import driver.DriverManager;
import driver.DriverManagerFactory;
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

import static io.qameta.allure.Allure.addAttachment;

/**
 * BaseTest class that is responsible for test preparation:
 * - gets test users
 * - sets up driver
 * - launches driver at designated environment
 * - instantiates HomePage
 * - adds screenshot on fail
 * - quits the driver
 */
public class BaseTest {

    private DriverManager driverManager;
    private WebDriver driver;
    protected PrestaShop prestaShop;
    protected GuestUser guestUser;
    protected TestUser testUser;

    @BeforeTest
    public void prepareTestUsers() {
        testUser = TestUser.getUser();
        guestUser = GuestUser.getUser();
    }

    @BeforeMethod
    @Parameters({"browser", "environment"})
    public void setUp(String browser, String environment) {
        driverManager = DriverManagerFactory.getManager(browser);
        driver = driverManager.getDriver();
        driver.get(environment);
        prestaShop = new PrestaShop(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess() && result.getStatus() != 3) {
            addAttachment("Screenshot on failure", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        driverManager.quitDriver();
    }
}