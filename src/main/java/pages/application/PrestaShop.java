package pages.application;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import pages.products.HomePage;

public class PrestaShop extends BasePage {

    public PrestaShop(WebDriver driver){
        super(driver);
    }

    public HomePage openPrestaShop(){
        return new HomePage(driver);
    }
}
