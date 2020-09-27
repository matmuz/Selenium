package pages.products;

import base.BasePage;
import models.OrderModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.menu.TopMenuPage;

import java.util.List;
import java.util.Random;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-description")
    private List<WebElement> allProducts;

    public ProductPage goToRandomProduct() {
        Random random = new Random();
        allProducts.get(random.nextInt(allProducts.size()))
                .click();
        return new ProductPage(driver);
    }

    public ProductPage goToProduct(String productName) {
        for (WebElement allProduct : allProducts) {
            if (allProduct.getText()
                    .contains(productName.toUpperCase())) {
                allProduct.click();
                break;
            }
        }
        return new ProductPage(driver);
    }

    public ProductsPage addRandomProducts(OrderModel order, int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            goToRandomProduct().addProductToCart(order)
                    .goToProductsPage();
        }
        return new ProductsPage(driver);
    }

    public ProductsPage addRandomProducts(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            goToRandomProduct().addProductToCart()
                    .goToProductsPage();
        }
        return new ProductsPage(driver);
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}