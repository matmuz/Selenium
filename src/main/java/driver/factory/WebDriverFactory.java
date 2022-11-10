package driver.factory;

import driver.types.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class WebDriverFactory {

    public WebDriver getDriver(DriverTypes driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeDriver((ChromeOptions) WebDriverOptionsFactory.getWebDriverOptions(driverType));
            case EDGE:
                return new EdgeDriver((EdgeOptions) WebDriverOptionsFactory.getWebDriverOptions(driverType));
            default:
                throw new UnsupportedOperationException("Unsupported driver type selected");
        }
    }
}