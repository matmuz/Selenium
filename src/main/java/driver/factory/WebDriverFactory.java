package driver.factory;

import driver.types.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static driver.types.DriverTypes.CHROME;
import static driver.types.DriverTypes.EDGE;

public final class WebDriverFactory {

    public WebDriver getDriver(DriverTypes driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeDriver((ChromeOptions) WebDriverOptionsFactory.getWebDriverOptions(CHROME));
            case EDGE:
                return new EdgeDriver((EdgeOptions) WebDriverOptionsFactory.getWebDriverOptions(EDGE));
            default:
                throw new UnsupportedOperationException("Unsupported driver type selected");
        }
    }
}