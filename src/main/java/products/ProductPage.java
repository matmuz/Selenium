package products;

import base.BasePage;
import menu.TopMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='h1']")
    private WebElement productNameBox;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement priceBox;

    @FindBy(xpath = "//button[@class='btn btn-primary add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private WebElement quantityBox;

    @FindBy(xpath = "//i[@class='material-icons touchspin-up']")
    private WebElement increaseQuantitySpin;

    @FindBy(xpath = "//i[@class='material-icons touchspin-down']")
    private WebElement decreaseQuantitySpin;

    @FindBy(xpath = "//button[@class='btn btn-secondary']")
    private WebElement continueShoppingButton;

    public String getProductName() {
        return productNameBox.getText();
    }

    public double getProductPrice() {
        String[] split = (priceBox.getText().split("\\$"));
        return Double.parseDouble(split[1]);
    }

    public ProductPage addProductToCart() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        addToCartButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
        return this;
    }

    public String getQuantity() {
        return quantityBox.getAttribute("value");
    }

    public ProductPage increaseQuantityBy(int number) {
        for (int i = 0; i < number; i++) {
            increaseQuantitySpin.click();
        }
        return this;
    }

    public ProductPage decreaseQuantityBy(int number) {
        for (int i = 0; i < number; i++) {
            decreaseQuantitySpin.click();
        }
        return this;
    }
    public TopMenuPage getTopMenuPage(){
        return new TopMenuPage(getDriver());
    }

}
