package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverProvider {

    private static DriverProvider instanceOfDriverProvider = null;

    private WebDriver driver;

    private DriverProvider() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    public static DriverProvider getInstanceOfDriverProvider() {
        if (instanceOfDriverProvider == null) {
            instanceOfDriverProvider = new DriverProvider();
        }
        return instanceOfDriverProvider;
    }

    public WebDriver getDriver() {
        return driver;
    }


}