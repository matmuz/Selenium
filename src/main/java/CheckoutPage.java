import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "id_gender")
    List<WebElement> genderRadioButtons;

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstNameBox;

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement lastNameBox;

    @FindBy(xpath = "//form[@id='customer-form']//input[@name='email']")
    WebElement emailBox;

    @FindBy(xpath = "//footer[@class='form-footer clearfix']//button[@name='continue'][contains(text(),'Continue')]")
    WebElement goToAddressButton;

    @FindBy(xpath = "//input[@name='address1']")
    WebElement addressBox;

    @FindBy(xpath = "//input[@name='city']")
    WebElement cityBox;

    @FindBy(xpath = "//select[@name='id_state']")
    WebElement stateDropdown;

    @FindBy(xpath = "//input[@name='postcode']")
    WebElement postalCodeBox;

    @FindBy(xpath = "//select[@name='id_country']")
    WebElement countryDropdown;

    @FindBy(xpath = "//button[@name='confirm-addresses']")
    WebElement goToShippingButton;

    @FindBy(xpath = "//button[@name='confirmDeliveryOption']")
    WebElement confirmShippingButton;

    @FindBy(name = "payment-option")
    List<WebElement> paymentRadioButtons;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    WebElement agreeCheckbox;

    @FindBy(xpath = "//button[@class='btn btn-primary center-block']")
    WebElement placeOrderButton;

    @FindBy(xpath = "//h3[@class='h1 card-title']")
    WebElement confirmationMessageBox;

    public void placeOrder() {
        Random random = new Random();
        genderRadioButtons.get(random.nextInt(genderRadioButtons.size())).click();
        firstNameBox.sendKeys("test");
        lastNameBox.sendKeys("test");
        emailBox.sendKeys("test@test.com");
        goToAddressButton.click();
        addressBox.sendKeys("test");
        cityBox.sendKeys("City");
        Select selectState = new Select(stateDropdown);
        selectState.selectByVisibleText("New York");
        postalCodeBox.sendKeys("12345");
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText("United States");
        goToShippingButton.click();
        confirmShippingButton.click();
        paymentRadioButtons.get(random.nextInt(paymentRadioButtons.size())).click();
        agreeCheckbox.click();
        placeOrderButton.click();
    }

    public String getConfirmationMessage() {
        return confirmationMessageBox.getText();
    }
}
