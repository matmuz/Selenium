package base;

import data.TestData;
import data.UserProvider;
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
    protected TestMethods test;
    protected PrestaShop prestaShop;
    protected UserProvider testUser, guestUser;
    protected TestData testData;

    @BeforeTest
    public void prepareTestData() throws IOException {
        test = new TestMethods();
        testUser = UserProvider.getUserProvider();
        guestUser = UserProvider.getGuestUserProvider();
        testData = TestData.get("src\\test\\resources\\testdata\\test-data.json");
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
    @Attachment(value = "Test result", type = "png")
    public byte[] screenshotOnTestFailure(ITestResult result) {
        if (!result.isSuccess()) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } else {
            return null;
        }
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver();
    }
}