package driver.manager;

import org.openqa.selenium.WebDriver;

/**
 * An interface responsible for providing methods to DriverManager class
 */
public interface IDriverManager {

    WebDriver getDriver();

    void quitDriver();

    void stopService();

}