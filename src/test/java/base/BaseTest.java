package base;

import data.TestData;
import data.TestUser;
import data.UserProvider;
import driver.DriverManager;
import driver.DriverManagerFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.application.PrestaShop;

import java.io.IOException;

public class BaseTest {

    private DriverManager driverManager;
    private WebDriver driver;
    protected PrestaShop prestaShop;
    protected UserProvider testUser, guestUser;
    protected TestData testData;
    protected TestUser existingUser;

    @BeforeTest
    public void prepareTestData() throws IOException {
        testData = TestData.getTestData();
        testUser = UserProvider.getUser();
        guestUser = UserProvider.getGuestUser();
        existingUser = TestUser.get("src\\test\\resources\\test-data\\existing-user.json");
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
    public byte[] onTestFailure(ITestResult result) {
        if (!result.isSuccess()) {
            return takeScreenshot();
        } else {
            return null;
        }
    }

    @Attachment(value = "Test result", type = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver();
    }
}