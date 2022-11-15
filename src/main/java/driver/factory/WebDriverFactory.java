package driver.factory;

import driver.types.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class WebDriverFactory {

    public WebDriver getDriver(DriverTypes driverType, boolean isLocal) {
        if (isLocal) {
            return getLocalDriver(driverType);
        } else {
            return getRemoteDriver(driverType);
        }
    }

    private WebDriver getLocalDriver(DriverTypes driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeDriver((ChromeOptions) WebDriverOptionsFactory.getLocalWebDriverOptions(driverType));
            case EDGE:
                return new EdgeDriver((EdgeOptions) WebDriverOptionsFactory.getLocalWebDriverOptions(driverType));
            default:
                throw new UnsupportedOperationException("Unsupported local driver type selected");
        }
    }

    private WebDriver getRemoteDriver(DriverTypes driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeDriver((ChromeOptions) WebDriverOptionsFactory.getRemoteWebDriverOptions(driverType));
            case EDGE:
                return new EdgeDriver((EdgeOptions) WebDriverOptionsFactory.getRemoteWebDriverOptions(driverType));
            default:
                throw new UnsupportedOperationException("Unsupported remote driver type selected");
        }
    }
}