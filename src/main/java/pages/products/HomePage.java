package pages.products;

import io.qameta.allure.Step;
import models.OrderModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

import static helpers.utils.Utils.getRandomElementFromList;
import static helpers.utils.Utils.getWebElementFromListByName;

/**
 * Home page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public final class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h3.product-title")
    private List<WebElement> popularProducts;

    @Step("Enter popular product")
    public ProductPage enterPopularProduct(String popularProductName) {
        getWebElementFromListByName(popularProducts, popularProductName).click();
        return new ProductPage(driver);
    }

    @Step("Enter random popular product")
    public ProductPage enterRandomPopularProduct() {
        popularProducts.get(getRandomElementFromList(popularProducts)).click();
        return new ProductPage(driver);
    }

    @Step("Add random popular product")
    public HomePage addRandomPopularProducts(OrderModel order, int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            enterRandomPopularProduct().addProductToCart(order).goToProductsPage();
        }
        return this;
    }
}