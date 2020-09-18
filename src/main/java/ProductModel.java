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

    public String getProductName() {
        return productNameBox.getText();
    }

    public String getProductPrice() {
        return priceBox.getText();
    }

}
