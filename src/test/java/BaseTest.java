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

    WebDriver driver;
    TopMenuPage topMenuPage;
    HomePage homePage;
    ProductPage productPage;
    ContactUsPage contactUsPage;
    CartPage cartPage;
    ProductsPage productsPage;
    CheckoutPage checkoutPage;
    int i = 1;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("http://5.196.7.235/");

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
