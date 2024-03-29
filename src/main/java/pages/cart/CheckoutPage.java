package pages.cart;

import helpers.waiter.Waiter;
import io.qameta.allure.Step;
import models.OrderModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

import java.util.List;

import static helpers.utils.Utils.getRandomElementFromList;

/**
 * Checkout page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public final class CheckoutPage extends BasePage {

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

    @FindBy(css = "#order-details li:nth-of-type(1)")
    private WebElement orderReferenceNumberBox;

    @FindBy(css = "input[name='vat_number']")
    private WebElement vatNumberInput;

    @Step("Place order")
    public CheckoutPage placeOrderAsGuest(String firstName, String lastName, String email, String address, String city,
                                          String postalCode, String country) {
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        emailBox.sendKeys(email);
        customerPrivacyCheckbox.click();
        privacyAcceptanceCheckbox.click();
        goToAddressButton.click();
        new Select(countryDropdown).selectByVisibleText(country);
        Waiter.wait(driver).until(ExpectedConditions.visibilityOf(vatNumberInput));
        addressBox.sendKeys(address);
        cityBox.sendKeys(city);
        postalCodeBox.sendKeys(postalCode);
        goToShippingButton.click();
        confirmShippingButton.click();
        paymentRadioButtons.get(getRandomElementFromList(paymentRadioButtons)).click();
        agreeCheckbox.click();
        placeOrderButton.click();
        return this;
    }

    @Step("Place order")
    public CheckoutPage placeOrderAsLoggedUser(String address, String city, String postalCode) {
        addressBox.sendKeys(address);
        cityBox.sendKeys(city);
        postalCodeBox.sendKeys(postalCode);
        goToShippingButton.click();
        confirmShippingButton.click();
        paymentRadioButtons.get(getRandomElementFromList(paymentRadioButtons)).click();
        agreeCheckbox.click();
        placeOrderButton.click();
        return this;
    }

    @Step("Place order")
    public CheckoutPage placeOrderAsLoggedUser() {
        goToShippingButton.click();
        confirmShippingButton.click();
        paymentRadioButtons.get(getRandomElementFromList(paymentRadioButtons)).click();
        agreeCheckbox.click();
        placeOrderButton.click();
        return this;
    }

    @Step("Get confirmation message")
    public String getConfirmationMessage() {
        return confirmationMessageBox.getText();
    }

    @Step("Set order reference number of an order to a model")
    public CheckoutPage setOrderReferenceNumberOfAnOrderToAModel(OrderModel order) {
        order.setOrderReferenceNumber(orderReferenceNumberBox.getText().split("Order reference: ")[1]);
        return new CheckoutPage(driver);
    }
}