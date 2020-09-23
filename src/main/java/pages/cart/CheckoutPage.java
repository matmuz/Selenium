package pages.cart;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.menu.TopMenuPage;

import java.util.List;
import java.util.Random;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='firstname']")
    private WebElement firstNameBox;

    @FindBy(css = "input[name='lastname']")
    private WebElement lastNameBox;

    @FindBy(css = "form[id='customer-form'] input[name='email']")
    private WebElement emailBox;

    @FindBy(css = "button[data-link-action='register-new-customer']")
    private WebElement goToAddressButton;

    @FindBy(css = "input[name='address1']")
    private WebElement addressBox;

    @FindBy(css = "input[name='city']")
    private WebElement cityBox;

    @FindBy(css = "select[name='id_state']")
    private WebElement stateDropdown;

    @FindBy(css = "input[name='postcode']")
    private WebElement postalCodeBox;

    @FindBy(css = "select[name='id_country']")
    private WebElement countryDropdown;

    @FindBy(css = "button[name='confirm-addresses']")
    private WebElement goToShippingButton;

    @FindBy(css = "button[name='confirmDeliveryOption']")
    private WebElement confirmShippingButton;

    @FindBy(css = "input[name='payment-option']")
    private List<WebElement> paymentRadioButtons;

    @FindBy(css = "#conditions_to_approve\\[terms-and-conditions\\]")
    private WebElement agreeCheckbox;

    @FindBy(css = ".btn.btn-primary.center-block")
    private WebElement placeOrderButton;

    @FindBy(css = ".h1.card-title")
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
        return new TopMenuPage(driver);
    }
}
