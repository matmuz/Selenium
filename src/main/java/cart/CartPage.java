package cart;

import base.BasePage;
import menu.TopMenuPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "cart-item")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//div[@id='cart-subtotal-products'] //span[@class='value']")
    private WebElement itemsPriceBox;

    @FindBy(xpath = "//div[@id='cart-subtotal-shipping'] //span[@class='value']")
    private WebElement shippingCostBox;

    @FindBy(xpath = "//div[@class='cart-summary-line cart-total'] //span[@class='value']")
    private WebElement totalPriceBox;

    @FindBy(xpath = "//span[@class='label js-subtotal']")
    private WebElement numberOfItemsBox;

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public Double getItemsPrice() {
        String[] split = (itemsPriceBox.getText().split("\\$"));
        return Double.parseDouble(split[1]);
    }

    public Double getShippingCost() {
        String[] split = (shippingCostBox.getText().split("\\$"));
        return Double.parseDouble(split[1]);
    }

    public double getTotalPrice() {
        String[] split = (totalPriceBox.getText().split("\\$"));
        return Double.parseDouble(split[1]);
    }

    public int getNumberOfItems(){
        String[] split = (numberOfItemsBox.getText().split(" "));
        return Integer.parseInt(split[0]);
    }

    public CartPage deleteItemFromCart(String name) {
        for (WebElement cartItem : cartItems) {
            if (cartItem.getText().contains(name.toUpperCase())) {
                cartItem.findElement(By.xpath("//i[@class='material-icons float-xs-left']")).click();
            }
        }
        return this;

    }

    public CheckoutPage proceedToCheckout(){
        proceedToCheckoutButton.click();
        return new CheckoutPage(getDriver());
    }
    public TopMenuPage getTopMenuPage(){
        return new TopMenuPage(getDriver());
    }
}
