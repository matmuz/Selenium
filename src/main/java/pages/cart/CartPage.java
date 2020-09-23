package pages.cart;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public int getItemsQuantity() {
        String[] split = (summaryLine.getText().split(" "));
        return Integer.parseInt(split[0]);
    }

    public Double getShippingCost() {
        String[] split = (shippingCostBox.getText().split("\\$"));
        return Double.parseDouble(split[1]);
    }

    public double getTotalPrice() {
        String[] split = (totalPriceBox.getText().split("\\$"));
        return Double.parseDouble(split[1]);
    }

    public double getItemsPrice() {
        String[] split = (summaryLine.getText().split("\\$"));
        return Double.parseDouble(split[1]);
    }

    public CartPage deleteItemFromCart(String name) {
        for (WebElement cartItem : cartItems) {
            if (cartItem.getText().contains(name.toUpperCase())) {
                cartItem.findElement(By.xpath(".material-icons.float-xs-left")).click();
            }
        }
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new CheckoutPage(driver);
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}
