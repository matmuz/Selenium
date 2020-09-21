import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(className = "cart-item")
    List<WebElement> cartItems;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//div[@id='cart-subtotal-products'] //span[@class='value']")
    WebElement itemsPriceBox;

    @FindBy(xpath = "//div[@id='cart-subtotal-shipping'] //span[@class='value']")
    WebElement shippingCostBox;

    @FindBy(xpath = "//div[@class='cart-summary-line cart-total'] //span[@class='value']")
    WebElement totalPriceBox;

    @FindBy(xpath = "//span[@class='label js-subtotal']")
    WebElement numberOfItemsBox;


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

    public void deleteItemFromCart(String name) {
        for (WebElement cartItem : cartItems) {
            if (cartItem.getText().contains(name.toUpperCase())) {
                cartItem.findElement(By.xpath("//i[@class='material-icons float-xs-left']")).click();
            }
        }

    }

    public void proceedToCheckout(){
        proceedToCheckoutButton.click();
    }
}
