package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.application.PrestaShop;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    private DriverManager driverManager;
    private WebDriver driver;
    protected TestData userData;
    protected TestData guestData;
    protected TestData testData;
    protected TestData newUserData;
    protected PrestaShop prestaShop;

    private int i = 1;

    @BeforeMethod
    public void setUp() throws IOException {
        driverManager = DriverManagerFactory.getManager(DriverManagerFactory.DriverType.CHROME);
        driver = driverManager.getDriver();
        testData = TestData.get("src\\test\\resources\\testdata\\test-data.json");
        userData = TestData.get("src\\test\\resources\\testdata\\existing-user.json");
        guestData = TestData.get("src\\test\\resources\\testdata\\guest-user.json");
        newUserData = TestData.get("src\\test\\resources\\testdata\\new-user.json");
        driver.get("http://5.196.7.235/");
        prestaShop = new PrestaShop(driver);
    }

    @AfterMethod
    public void takeScreenshot() throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src\\test\\resources\\results\\test_case_" + i + "_result.png");
        FileUtils.copyFile(sourceFile, destinationFile);
        i++;
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver();
    }
}