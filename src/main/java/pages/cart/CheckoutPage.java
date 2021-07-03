package pages.cart;

import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;
import io.qameta.allure.Step;
import models.OrderModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.menu.TopMenuPage;

import java.util.List;
import java.util.Random;

/**
 * Checkout page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public class CheckoutPage extends BasePage {

    public static final String CHECK_MARK = "";
    public static final String CONFIRMATION_MESSAGE = CHECK_MARK + "YOUR ORDER IS CONFIRMED";

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='firstname']")
    private WebElement firstNameBox;

    @FindBy(css = "input[name='lastname']")
    private WebElement lastNameBox;

    @FindBy(css = "form[id='customer-form'] input[name='email']")
    private WebElement emailBox;

    @FindBy(css = "input[name='psgdpr']")
    private WebElement privacyAcceptanceCheckbox;

    @FindBy(css = "input[name='customer_privacy']")
    private WebElement customerPrivacyCheckbox;

    @FindBy(css = "button[data-link-action='register-new-customer']")
    private WebElement goToAddressButton;

    @FindBy(css = ".continue.btn.btn-primary.float-xs-right")
    private WebElement continueButton;

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

    @FindBy(css = "#order-details")
    private WebElement orderReferenceNumberBox;

    @Step("Place order")
    public CheckoutPage placeOrderAsGuest(String firstName, String lastName, String email, String address, String city, String postalCode, String country) {
        Random random = new Random();
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        emailBox.sendKeys(email);
        customerPrivacyCheckbox.click();
        privacyAcceptanceCheckbox.click();
        goToAddressButton.click();
        addressBox.sendKeys(address);
        cityBox.sendKeys(city);
        postalCodeBox.sendKeys(postalCode);
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(country);
        goToShippingButton.click();
        confirmShippingButton.click();
        paymentRadioButtons.get(random.nextInt(paymentRadioButtons.size()))
                .click();
        agreeCheckbox.click();
        placeOrderButton.click();
        return this;
    }

    @Step("Place order")
    public CheckoutPage placeOrderAsLoggedUser(String address, String city, String postalCode) {
        Random random = new Random();
        addressBox.sendKeys(address);
        cityBox.sendKeys(city);
        postalCodeBox.sendKeys(postalCode);
        goToShippingButton.click();
        confirmShippingButton.click();
        paymentRadioButtons.get(random.nextInt(paymentRadioButtons.size()))
                .click();
        agreeCheckbox.click();
        placeOrderButton.click();
        return this;
    }

    @Step("Place order")
    public CheckoutPage placeOrderAsLoggedUser() {
        Random random = new Random();
        goToShippingButton.click();
        confirmShippingButton.click();
        paymentRadioButtons.get(random.nextInt(paymentRadioButtons.size()))
                .click();
        agreeCheckbox.click();
        placeOrderButton.click();
        return this;
    }

    public String getConfirmationMessage() {
        return confirmationMessageBox.getText();
    }

    public CheckoutPage getOrderReference(OrderModel order) {
        String[] split = (orderReferenceNumberBox.getText()).split("Order reference:");
        String orderNumber = split[1].trim();
        String[] secondSplit = orderNumber.split("Payment method:");
        String secondOrderNumber = secondSplit[0].trim();
        order.setOrderReferenceNumber(secondOrderNumber);
        return new CheckoutPage(driver);
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}