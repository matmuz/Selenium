package driver.manager;

import org.openqa.selenium.WebDriver;

/**
 * An interface responsible for providing methods to DriverManager class
 */
public interface IDriverManager {

    void quitDriver();
    WebDriver getDriver();

}