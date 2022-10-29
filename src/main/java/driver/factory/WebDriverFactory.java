package driver.factory;

import driver.types.DriverTypes;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
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
                ChromeDriverManager.chromedriver().setup();
                return new ChromeDriver((ChromeOptions) WebDriverOptionsFactory.getWebDriverOptions(CHROME));
            case EDGE:
                EdgeDriverManager.edgedriver().setup();
                return new EdgeDriver((EdgeOptions) WebDriverOptionsFactory.getWebDriverOptions(EDGE));
            default:
                throw new UnsupportedOperationException("Unsupported driver type selected");
        }
    }
}