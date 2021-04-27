package pages.application;

import pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.products.HomePage;

public class PrestaShop extends BasePage {

    public PrestaShop(WebDriver driver){
        super(driver);
    }

    @Step("Open PrestaShop")
    public HomePage openPrestaShop(){
        return new HomePage(driver);
    }
}
