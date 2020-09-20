import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

    WebDriver driver;
    ProductPage productPage;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product-description")
    List<WebElement> allProducts;


    public void goToProduct(String productName) {
        for (WebElement allProduct : allProducts) {
            if (allProduct.getText().contains(productName.toUpperCase())) {
                allProduct.click();
                break;
            }
        }
    }

    public void addProduct(String productName) {
        goToProduct(productName);
        productPage = new ProductPage(driver);
        productPage.addProductToCart();
    }
}
