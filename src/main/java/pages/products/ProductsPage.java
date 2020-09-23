package pages.products;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.menu.TopMenuPage;

import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-description")
    private List<WebElement> allProducts;

    public List<WebElement> getAllProducts() {
        return allProducts;
    }

    public ProductPage goToProduct(String productName) {
        for (WebElement allProduct : allProducts) {
            if (allProduct.getText().contains(productName.toUpperCase())) {
                allProduct.click();
                break;
            }
        }
        return new ProductPage(driver);
    }
    public TopMenuPage getTopMenuPage(){
        return new TopMenuPage(driver);
    }

}
