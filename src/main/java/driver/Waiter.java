package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Default Waiter class that returns WebDriverWait to avoid multiple instantiations
 */

public class Waiter {

    private static WebDriverWait wait;

    private Waiter() {
    }

    public static WebDriverWait wait(WebDriver driver) {
        if (wait == null) {
            wait = new WebDriverWait(driver, 10);
        }
        return wait;
    }
}