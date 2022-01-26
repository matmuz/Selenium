package pages.products;

import driver.utils.Waiter;
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

/**
 * A particular product page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public final class ProductPage extends BasePage {

    private static final String PERSONALIZATION_TEXT = "Test Personalisation 123";
    private static final String CUSTOMIZABLE_PRODUCT_TEXT = "CUSTOMIZABLE";

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
        return Double.parseDouble(priceBox.getText()
                                          .replace(",", ".")
                                          .replace("$", ""));
    }

    public String getProductCurrency() {
        return priceBox.getText().substring(0, 1);
    }

    @Step("Add product to cart")
    public ProductPage addProductToCart(OrderModel order) {
        Waiter.wait(driver).until(ExpectedConditions.elementToBeClickable(productNameBox));
        if (getProductName().contains(CUSTOMIZABLE_PRODUCT_TEXT)) {
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
        Waiter.wait(driver).until(ExpectedConditions.elementToBeClickable(productNameBox));
        if (getProductName().contains(CUSTOMIZABLE_PRODUCT_TEXT)) {
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
        productMessage.sendKeys(PERSONALIZATION_TEXT);
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
        categories.get(1).click();
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}