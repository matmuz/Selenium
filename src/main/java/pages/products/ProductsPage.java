package pages.products;

import io.qameta.allure.Step;
import models.OrderModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.menu.TopMenuPage;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * A group of products page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public final class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h3.product-title")
    private List<WebElement> allProducts;

    @Step("Go to random product")
    public ProductPage goToRandomProduct() {
        Random random = new Random();
        allProducts.get(random.nextInt(allProducts.size()))
                .click();
        return new ProductPage(driver);
    }

    @Step("Go to product")
    public ProductPage goToProduct(String productName) {
        allProducts.stream()
                .filter(WebElement -> WebElement.getText()
                        .toUpperCase()
                        .contains(productName.toUpperCase()))
                .collect(Collectors.toList())
                .get(0)
                .click();
        return new ProductPage(driver);
    }

    @Step("Add random products")
    public ProductsPage addRandomProducts(OrderModel order, int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            goToRandomProduct().addProductToCart(order)
                    .goToProductsPage();
        }
        return this;
    }

    @Step("Add random products")
    public ProductsPage addRandomProducts(int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            goToRandomProduct().addProductToCart()
                    .goToProductsPage();
        }
        return this;
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}