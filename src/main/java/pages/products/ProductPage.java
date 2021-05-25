package pages.products;

import driver.Waiter;
import io.qameta.allure.Step;
import models.OrderModel;
import models.ProductModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;
import pages.menu.TopMenuPage;

import java.util.List;

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

    @FindBy(css = ".product-message")
    private WebElement productMessage;

    @FindBy(css = ".btn.btn-primary.float-xs-right")
    private WebElement saveCustomisation;

    @FindBy(css = ".btn.btn-secondary")
    private WebElement continueShoppingButton;

    @FindBy(css = "span[itemprop='name']")
    private List<WebElement> categories;

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
        if (getProductName().contains("CUSTOMIZABLE")) {
            setPersonalisedText();
        }
        Waiter.wait(driver).until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ProductModel productToAdd = new ProductModel(getProductName(), getProductPrice(), getQuantity());
        order.addProductToList(productToAdd);
        addToCartButton.click();
        Waiter.wait(driver).until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
        return this;
    }

    @Step("Add product to cart")
    public ProductPage addProductToCart() {
        if (getProductName().contains("CUSTOMIZABLE")) {
            setPersonalisedText();
        }
        Waiter.wait(driver).until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        Waiter.wait(driver).until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
        return this;
    }

    public int getQuantity() {
        return Integer.parseInt(quantityBox.getAttribute("value"));
    }

    public void setPersonalisedText() {
        productMessage.sendKeys("Test Personalisation 123");
        saveCustomisation.click();
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
        categories.get(1)
                .click();
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}