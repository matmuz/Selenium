package base;

import application.PrestaShop;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    private WebDriver driver;
    protected PrestaShop prestaShop;

    int i = 1;

    protected WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://5.196.7.235/");
        prestaShop = new PrestaShop(driver);

    }

    @AfterMethod
    public void takeScreenshot() throws IOException {

        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src\\main\\results\\test_case_" + i + "_result.png");
        FileUtils.copyFile(sourceFile, destinationFile);
        i++;

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

    }

}
