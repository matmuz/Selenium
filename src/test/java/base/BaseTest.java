package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.application.PrestaShop;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BaseTest {

    private DriverManager driverManager;
    private WebDriver driver;
    private String testName;
    protected TestData userData;
    protected TestData guestData;
    protected TestData testData;
    protected TestData newUserData;
    protected PrestaShop prestaShop;

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
    public void getTestName(ITestResult result) {
        testName = result.getMethod()
                .getMethodName();
    }

    @AfterMethod
    public void takeScreenshot() throws IOException {
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "png", new File("src\\test\\resources\\results\\" + testName + "TestResult.png"));
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver();
    }
}