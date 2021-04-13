package pages.cart;

import base.BasePage;
import io.qameta.allure.Step;
import models.OrderModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.menu.TopMenuPage;

import java.util.List;

public class CartPage extends BasePage {

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
        String[] split = (summaryLine.getText()
                .split(" "));
        return Integer.parseInt(split[0]);
    }

    public Double getShippingCost() {
        String price = shippingCostBox.getText()
                .replace(",", ".");
        String[] split = (price
                .split(" "));
        return Double.parseDouble(split[0]);
    }

    public double getTotalPrice() {
        String price = totalPriceBox.getText()
                .replace(",", ".");
        String[] split = (price
                .split(" "));
        return Double.parseDouble(split[0]);
    }

    public double getItemsPrice() {
        String price = itemsPriceBox.getText()
                .replace(",", ".");
        String[] split = (price.split(" "));
        return Double.parseDouble(split[0]);
    }

    @Step("Delete item from cart")
    public CartPage deleteItemFromCart(String name) {
        for (WebElement cartItem : cartItems) {
            if (cartItem.getText()
                    .contains(name.toUpperCase())) {
                cartItem.findElement(By.cssSelector(".material-icons.float-xs-left"))
                        .click();
            }
        }
        return this;
    }

    @Step("Delete item from cart")
    public CartPage deleteItemFromCart(OrderModel order, String name) {
        for (WebElement cartItem : cartItems) {
            if (cartItem.getText()
                    .toUpperCase()
                    .contains(name.toUpperCase())) {
                cartItem.findElement(By.cssSelector(".material-icons.float-xs-left"))
                        .click();
                order.deleteProductFromList(name);
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.invisibilityOf(cartItem));
            }
        }
        return this;
    }

    public String getItemDetailsByName(String productName) {
        String product;
        for (WebElement cartItem : cartItems) {
            if (cartItem
                    .getText()
                    .toUpperCase()
                    .contains(productName.toUpperCase())) {
                product = cartItem.findElement(By.cssSelector(".product-line-info"))
                        .getText();
                return product.toUpperCase();
            }
        }
        return "null";
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