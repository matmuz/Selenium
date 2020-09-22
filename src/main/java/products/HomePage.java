package products;

import base.BasePage;
import menu.TopMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "product-description")
    private List<WebElement> popularProducts;

    public List<WebElement> getPopularProducts() {
        return popularProducts;
    }

    public ProductPage enterPopularProduct(String popularProductName) {
        for (WebElement popularProduct : popularProducts) {
            if (popularProduct.getText().toLowerCase().contains(popularProductName)) {
                popularProduct.click();
                break;
            }
        }
        return new ProductPage(getDriver());
    }
    public TopMenuPage getTopMenuPage(){
        return new TopMenuPage(getDriver());
    }
}
