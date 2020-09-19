import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductModel {

    WebDriver driver;

    public ProductModel(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//h1[@class='h1']")
    WebElement productNameBox;

    @FindBy(xpath = "//span[@itemprop='price']")
    WebElement priceBox;

    @FindBy(xpath = "//button[@class='btn btn-primary add-to-cart']")
    WebElement addToCartButton;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    WebElement quantityBox;

    @FindBy(xpath = "//i[@class='material-icons touchspin-up']")
    WebElement increaseQuantitySpin;

    @FindBy(xpath = "//i[@class='material-icons touchspin-down']")
    WebElement decreaseQuantitySpin;

    @FindBy(xpath = "//button[@class='btn btn-secondary']")
    WebElement continueShoppingButton;

    public String getProductName() {
        return productNameBox.getText();
    }

    public String getProductPrice() {
        return priceBox.getText();
    }

    public void addProductToCart() {
        addToCartButton.click();
        continueShoppingButton.click();
    }

    public String getQuantity() {
        return quantityBox.getAttribute("value");
    }

    public void increaseQuantityBy(int number) {
        for (int i = 0; i < number; i++) {
            increaseQuantitySpin.click();
        }
    }

    public void decreaseQuantityBy(int number) {
        for (int i = 0; i < number; i++) {
            decreaseQuantitySpin.click();
        }
    }

}
