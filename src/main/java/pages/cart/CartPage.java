package pages.cart;

import driver.utils.Waiter;
import io.qameta.allure.Step;
import models.OrderModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;
import pages.menu.TopMenuPage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Cart page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public final class CartPage extends BasePage {

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

    public int getItemsQuantity() {
        String[] split = (summaryLine.getText().split(" "));
        return Integer.parseInt(split[0]);
    }

    public Double getShippingCost() {
        String price = shippingCostBox.getText().replace(",", ".").replace("$", "");
        return Double.parseDouble(price);
    }

    public double getTotalPrice() {
        String price = totalPriceBox.getText().replace(",", ".").replace("$", "");
        return Double.parseDouble(price);
    }

    public double getItemsPrice() {
        String price = itemsPriceBox.getText().replace(",", ".").replace("$", "");
        return Double.parseDouble(price);
    }

    @Step("Delete item from cart")
    public CartPage deleteItemFromCart(String name) {
        WebElement cartItem = cartItems.stream()
                                       .filter(WebElement -> WebElement.getText()
                                                                       .toUpperCase()
                                                                       .contains(name.toUpperCase()))
                                       .collect(Collectors.toList())
                                       .get(0);
        cartItem.findElement(By.cssSelector(".material-icons.float-xs-left")).click();
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
        cartItem.findElement(By.cssSelector(".material-icons.float-xs-left")).click();
        order.deleteProductFromList(name);
        Waiter.wait(driver).until(ExpectedConditions.invisibilityOf(cartItem));
        return this;
    }

    public String getItemDetailsByName(String productName) {
        return cartItems.stream()
                        .filter(WebElement -> WebElement.getText().toUpperCase().contains(productName.toUpperCase()))
                        .collect(Collectors.toList())
                        .get(0)
                        .findElement(By.cssSelector(".product-line-info"))
                        .getText()
                        .toUpperCase();
    }

    @Step("Go to checkout")
    public CheckoutPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new CheckoutPage(driver);
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}