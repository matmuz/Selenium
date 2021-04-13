package pages.products;

import base.BasePage;
import io.qameta.allure.Step;
import models.OrderModel;
import models.ProductModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.menu.TopMenuPage;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h1")
    private WebElement productNameBox;

    @FindBy(css = "span[itemprop='price']")
    private WebElement priceBox;

    @FindBy(css = ".btn.btn-primary.add-to-cart")
    private WebElement addToCartButton;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityBox;

    @FindBy(css = ".material-icons.touchspin-up")
    private WebElement increaseQuantitySpin;

    @FindBy(css = ".material-icons.touchspin-down")
    private WebElement decreaseQuantitySpin;

    @FindBy(css = ".btn.btn-secondary")
    private WebElement continueShoppingButton;

    public String getProductName() {
        return productNameBox.getText();
    }

    public double getProductPrice() {
        String price = priceBox.getText()
                .replace(",", ".");
        String[] split = (price.split(" "));
        return Double.parseDouble(split[0]);
    }

    @Step("Add product to cart")
    public ProductPage addProductToCart(OrderModel order) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ProductModel productToAdd = new ProductModel(getProductName(), getProductPrice(), getQuantity());
        order.addProductToList(productToAdd);
        addToCartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
        return this;
    }

    @Step("Add product to cart")
    public ProductPage addProductToCart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
        return this;
    }

    public int getQuantity() {
        return Integer.parseInt(quantityBox.getAttribute("value"));
    }

    @Step("Increase quantity")
    public ProductPage increaseQuantityBy(int number) {
        for (int i = 0; i < number; i++) {
            increaseQuantitySpin.click();
        }
        return this;
    }

    @Step("Decrease quantity")
    public ProductPage decreaseQuantityBy(int number) {
        for (int i = 0; i < number; i++) {
            decreaseQuantitySpin.click();
        }
        return this;
    }

    @Step("Go to products page")
    public void goToProductsPage() {
        driver.navigate()
                .back();
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}