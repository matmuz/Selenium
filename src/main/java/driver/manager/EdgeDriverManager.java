package driver.manager;

import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

/**
 * Concrete class that is responsible for managing EdgeDriver's cycle of life
 */
public final class EdgeDriverManager extends DriverManager {

    /**
     * Creates - if absent - a Edge Driver Service instance
     */
    @Override
    public void startService() {
        if (service == null) {
            try {
                service = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/drivers/msedgedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                service.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Sets Edge options and creates a Driver on a related service
     */
    @Override
    public void createDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        driver = new RemoteWebDriver(service.getUrl(), options);
    }
}
