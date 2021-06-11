package driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

/**
 * Concrete class that is responsible for managing ChromeDriver's cycle of life
 */

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chromeDriverService;

    @Override
    public void startService() {
        if (null == chromeDriverService) {
            try {
                chromeDriverService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/drivers/chromedriver"))
                        .usingAnyFreePort()
                        .build();
                chromeDriverService.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chromeDriverService && chromeDriverService.isRunning())
            chromeDriverService.stop();
    }

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(chromeDriverService, options);
    }
}