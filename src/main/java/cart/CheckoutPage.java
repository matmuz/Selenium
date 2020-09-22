package cart;

import base.BasePage;
import menu.TopMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstNameBox;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastNameBox;

    @FindBy(xpath = "//form[@id='customer-form']//input[@name='email']")
    private WebElement emailBox;

    @FindBy(xpath = "//footer[@class='form-footer clearfix']//button[@name='continue'][contains(text(),'Continue')]")
    private WebElement goToAddressButton;

    @FindBy(xpath = "//input[@name='address1']")
    private WebElement addressBox;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityBox;

    @FindBy(xpath = "//select[@name='id_state']")
    private WebElement stateDropdown;

    @FindBy(xpath = "//input[@name='postcode']")
    private WebElement postalCodeBox;

    @FindBy(xpath = "//select[@name='id_country']")
    private WebElement countryDropdown;

    @FindBy(xpath = "//button[@name='confirm-addresses']")
    private WebElement goToShippingButton;

    @FindBy(xpath = "//button[@name='confirmDeliveryOption']")
    private WebElement confirmShippingButton;

    @FindBy(name = "payment-option")
    private List<WebElement> paymentRadioButtons;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement agreeCheckbox;

    @FindBy(xpath = "//button[@class='btn btn-primary center-block']")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//h3[@class='h1 card-title']")
    private WebElement confirmationMessageBox;

    public List<WebElement> getPaymentRadioButtons() {
        return paymentRadioButtons;
    }

    public CheckoutPage placeOrderAsGuest(String firstName, String lastName, String email, String address, String city, String state, String postalCode, String country) {
        Random random = new Random();
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        emailBox.sendKeys(email);
        goToAddressButton.click();
        addressBox.sendKeys(address);
        cityBox.sendKeys(city);
        Select selectState = new Select(stateDropdown);
        selectState.selectByVisibleText(state);
        postalCodeBox.sendKeys(postalCode);
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(country);
        goToShippingButton.click();
        confirmShippingButton.click();
        paymentRadioButtons.get(random.nextInt(paymentRadioButtons.size())).click();
        agreeCheckbox.click();
        placeOrderButton.click();
        return this;
    }

    public String getConfirmationMessage() {
        return confirmationMessageBox.getText();
    }

    public TopMenuPage getTopMenuPage(){
        return new TopMenuPage(getDriver());
    }
}
