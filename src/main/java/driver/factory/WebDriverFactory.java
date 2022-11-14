package driver.factory;

import driver.types.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class WebDriverFactory {

    public WebDriver getDriver(DriverTypes driverType, boolean isLocal) {
        switch (driverType) {
            case CHROME:
                if (isLocal) {
                    return new ChromeDriver((ChromeOptions) WebDriverOptionsFactory.getLocalWebDriverOptions(driverType));
                }
                return new ChromeDriver((ChromeOptions) WebDriverOptionsFactory.getRemoteWebDriverOptions(driverType));
            case EDGE:
                if (isLocal) {
                    return new EdgeDriver((EdgeOptions) WebDriverOptionsFactory.getLocalWebDriverOptions(driverType));
                }
                return new EdgeDriver((EdgeOptions) WebDriverOptionsFactory.getRemoteWebDriverOptions(driverType));
            default:
                throw new UnsupportedOperationException("Unsupported driver type selected");
        }
    }
}