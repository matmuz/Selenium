package pages.application;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import pages.products.HomePage;

/**
 * This class is responsible for returning the home page of Presta app when the driver gets the environment
 */
public final class PrestaShop extends BasePage {

    public PrestaShop(WebDriver driver) {
        super(driver);
    }

    @Step("Open PrestaShop")
    public HomePage openPrestaShop() {
        return new HomePage(driver);
    }
}