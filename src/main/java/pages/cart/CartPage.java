package pages.cart;

import helpers.converter.IPriceConverter;
import helpers.waiter.Waiter;
import io.qameta.allure.Step;
import models.OrderModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Cart page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public final class CartPage extends BasePage implements IPriceConverter {

    private final By deleteButtonOfAnItemLocator = By.cssSelector(".material-icons.float-xs-left");
    private final By itemDetailsLocator = By.cssSelector(".product-line-info");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-item")
    private List<WebElement> cartItems;

    @FindBy(css = ".btn.btn-primary")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = "#cart-subtotal-shipping")
    private WebElement shippingCostBox;

    @FindBy(css = ".cart-summary-line.cart-total")
    private WebElement totalPriceBox;

    @FindBy(css = "#cart-subtotal-products")
    private WebElement summaryLine;

    @FindBy(css = "#cart-subtotal-products .value")
    private WebElement itemsPriceBox;

    /**
     * Gets items quantity
     *
     * @return items quantity as an int
     */
    public int getItemsQuantity() {
        return Integer.parseInt(summaryLine.getText().split(" ")[0]);
    }

    /**
     * Gets shipping cost
     *
     * @return shipping cost as double
     */
    public double getShippingCost() {
        return convertPriceTextToNumbers(shippingCostBox.getText());
    }

    /**
     * Gets total price
     *
     * @return total price as double
     */
    public double getTotalPrice() {
        return convertPriceTextToNumbers(totalPriceBox.getText());
    }

    /**
     * Gets items price
     *
     * @return items price as double
     */
    public double getItemsPrice() {
        return convertPriceTextToNumbers(itemsPriceBox.getText());
    }

    @Step("Delete item from cart")
    public CartPage deleteItemFromCart(String name) {
        WebElement cartItem = cartItems.stream()
                                       .filter(WebElement -> WebElement.getText()
                                                                       .toUpperCase()
                                                                       .contains(name.toUpperCase()))
                                       .collect(Collectors.toList())
                                       .get(0);
        cartItem.findElement(deleteButtonOfAnItemLocator).click();
        Waiter.wait(driver).until(ExpectedConditions.invisibilityOf(cartItem));
        return this;
    }

    @Step("Delete item from cart")
    public CartPage deleteItemFromCart(OrderModel order, String name) {
        WebElement cartItem = cartItems.stream()
                                       .filter(WebElement -> WebElement.getText()
                                                                       .toUpperCase()
                                                                       .contains(name.toUpperCase()))
                                       .collect(Collectors.toList())
                                       .get(0);
        cartItem.findElement(deleteButtonOfAnItemLocator).click();
        order.deleteProductFromList(name);
        Waiter.wait(driver).until(ExpectedConditions.invisibilityOf(cartItem));
        return this;
    }

    /**
     * Gets items details filtering by products name
     *
     * @param productName product name of a product to find
     * @return product details as String
     */
    public String getItemDetailsByName(String productName) {
        return cartItems.stream()
                        .filter(WebElement -> WebElement.getText().toUpperCase().contains(productName.toUpperCase()))
                        .collect(Collectors.toList())
                        .get(0)
                        .findElement(itemDetailsLocator)
                        .getText()
                        .toUpperCase();
    }

    @Step("Go to checkout")
    public CheckoutPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new CheckoutPage(driver);
    }
}