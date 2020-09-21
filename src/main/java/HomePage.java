import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver driver;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product-description")
    List<WebElement> popularProducts;

    @FindBy(xpath = "//a[@class='all-product-link float-xs-left float-md-right h4']")
    WebElement allProductsButton;

    public void enterPopularProduct(String popularProductName) {
        for (WebElement popularProduct : popularProducts) {
            if (popularProduct.getText().toLowerCase().contains(popularProductName)) {
                popularProduct.click();
                break;
            }
        }
    }

    public void goToAllProducts() {
        allProductsButton.click();
    }
}
