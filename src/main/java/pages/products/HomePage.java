package pages.products;

import base.BasePage;
import models.OrderModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.menu.TopMenuPage;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-description")
    private List<WebElement> popularProducts;

    public ProductPage enterPopularProduct(String popularProductName) {
        for (WebElement popularProduct : popularProducts) {
            if (popularProduct.getText()
                    .toLowerCase()
                    .contains(popularProductName)) {
                popularProduct.click();
                break;
            }
        }
        return new ProductPage(driver);
    }

    public ProductPage enterRandomPopularProduct() {
        Random random = new Random();
        popularProducts.get(random.nextInt(popularProducts.size()))
                .click();
        return new ProductPage(driver);
    }

    public HomePage addRandomPopularProducts(OrderModel order, int numberOfProducts) {
        for (int i = 0; i < numberOfProducts; i++) {
            enterRandomPopularProduct().addProductToCart(order)
                    .goToProductsPage();
        }
        return new HomePage(driver);
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}