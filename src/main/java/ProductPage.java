import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    WebDriver driver;

    public ProductPage(WebDriver driver) {

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

    public double getProductPrice() {
        String[] split = (priceBox.getText().split("\\$"));
        return Double.parseDouble(split[1]);
    }

    public void addProductToCart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        addToCartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
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
