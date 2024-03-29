package driver.factory;

import driver.types.DriverTypes;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public final class WebDriverOptionsFactory {

    public static AbstractDriverOptions<? extends AbstractDriverOptions<?>> getRemoteWebDriverOptions(DriverTypes driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeOptions().addArguments("window-size=1920,1080")
                                          .addArguments("incognito")
                                          .setHeadless(true);
            case EDGE:
                return new EdgeOptions().addArguments("window-size=1920,1080")
                                        .addArguments("inprivate")
                                        .setHeadless(true);
            default:
                throw new UnsupportedOperationException("Unsupported driver type selected");
        }
    }

    public static AbstractDriverOptions<? extends AbstractDriverOptions<?>> getLocalWebDriverOptions(DriverTypes driverType) {
        switch (driverType) {
            case CHROME:
                return new ChromeOptions().addArguments("start-maximized")
                                          .addArguments("incognito");
            case EDGE:
                return new EdgeOptions().addArguments("start-maximized")
                                        .addArguments("inprivate");
            default:
                throw new UnsupportedOperationException("Unsupported driver type selected");
        }
    }
}