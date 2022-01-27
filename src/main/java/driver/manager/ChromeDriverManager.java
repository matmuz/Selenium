package driver.manager;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

/**
 * Concrete class that is responsible for managing ChromeDriver's cycle of life
 */
public final class ChromeDriverManager extends DriverManager {

    /**
     * Creates - if absent - a Chrome Driver Service instance
     */
    @Override
    public void startService() {
        if (service == null) {
            try {
                service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/drivers/chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                service.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Sets Chrome options and creates a Driver on a related service
     */
    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new RemoteWebDriver(service.getUrl(), options);
    }
}