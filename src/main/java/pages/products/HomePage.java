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
        popularProducts.stream()
                .filter(WebElement -> WebElement.getText()
                        .toUpperCase()
                        .contains(popularProductName.toUpperCase()))
                .collect(Collectors.toList())
                .get(0)
                .click();
        return new ProductPage(driver);
    }

    @Step("Enter random popular product")
    public ProductPage enterRandomPopularProduct() {
        Random random = new Random();
        popularProducts.get(random.nextInt(popularProducts.size()))
                .click();
        return new ProductPage(driver);
    }

    @Step("Add random popular product")
    public HomePage addRandomPopularProducts(OrderModel order, int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            enterRandomPopularProduct().addProductToCart(order)
                    .goToProductsPage();
        }
        return this;
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}