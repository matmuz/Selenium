package driver.manager;

import org.openqa.selenium.WebDriver;

/**
 * Abstract class responsible for providing the core for subclasses (particular drivers) and managing a driver
 */
public abstract class DriverManager implements IDriverManager {

    protected WebDriver driver;

    protected abstract void startService();

    protected abstract void stopService();

    protected abstract void createDriver();

    @Override
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            startService();
            createDriver();
        }
        return driver;
    }
}