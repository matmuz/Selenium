package driver.waiter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Default Waiter class that returns WebDriverWait to avoid multiple instantiations
 */
public final class Waiter {

    private static WebDriverWait wait;

    /**
     * Private constructor - do not allow to create an instance
     */
    private Waiter() {
    }

    /**
     * Gets or creates a Web Driver Wait instance with duration of max 10 seconds
     *
     * @param driver web driver instance which needs to wait
     * @return Web Driver Wait instance
     */
    public static WebDriverWait wait(WebDriver driver) {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }
}