package base;

import data.ITestData;
import data.TestUser;
import data.UserProvider;
import driver.DriverManager;
import driver.DriverManagerFactory;
import io.qameta.allure.Allure;
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
import java.io.IOException;

public class BaseTest implements ITestData {

    private DriverManager driverManager;
    private WebDriver driver;
    protected PrestaShop prestaShop;
    protected UserProvider testUser, guestUser;
    protected TestUser existingUser;

    @BeforeTest
    public void prepareTestData() throws IOException {
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
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            Allure.addAttachment("Test failure", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        driverManager.quitDriver();
    }
}