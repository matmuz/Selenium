package driver.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

/**
 * Abstract class responsible for providing the core for subclasses (particular drivers) and managing a driver
 */
public abstract class DriverManager implements IDriverManager {

    protected WebDriver driver;
    protected DriverService service;

    protected abstract void startService();

    protected abstract void createDriver();

    /**
     * Creates driver if absent, starts service if needed
     *
     * @return driver instance
     */
    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            startService();
            createDriver();
        }
        return driver;
    }

    /**
     * Quits driver
     */
    @Override
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Stops driver service if needed
     */
    @Override
    public void stopService() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}