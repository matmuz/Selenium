package pages.account;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.products.HomePage;

import java.util.List;

/**
 * Account page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public final class AccountPage extends BasePage {

    public static final String RESET_RESPONSE = "If this email address has been registered in our shop, you will receive a link to reset your password at ";

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".form-control")
    private WebElement loginEmailBox;

    @FindBy(css = ".form-control.js-child-focus.js-visible-password")
    private WebElement loginPasswordBox;

    @FindBy(css = "#submit-login")
    private WebElement signInButton;

    @FindBy(css = "a[data-link-action='display-register-form']")
    private WebElement createAccountButton;

    @FindBy(css = "a[href*='controller=password']")
    private WebElement passwordRecoveryButton;

    @FindBy(css = "#email")
    private WebElement passwordResetEmail;

    @FindBy(css = ".form-control-submit.btn.btn-primary.hidden-xs-down")
    private WebElement sendResetLink;

    @FindBy(css = "#content")
    private WebElement resetResponseMessage;

    @FindBy(css = "input[name='firstname']")
    private WebElement firstNameBox;

    @FindBy(css = "input[name='lastname']")
    private WebElement lastNameBox;

    @FindBy(css = "input[class='form-control'][name='email']")
    private WebElement emailToSetBox;

    @FindBy(css = "input[name='password']")
    private WebElement passwordToSetBox;

    @FindBy(css = "input[name='psgdpr']")
    private WebElement privacyAcceptanceCheckbox;

    @FindBy(css = "input[name='customer_privacy']")
    private WebElement customerPrivacyCheckbox;

    @FindBy(css = ".btn.btn-primary.form-control-submit.float-xs-right")
    private WebElement createAccountBox;

    @FindBy(css = ".alert.alert-danger")
    private WebElement alertBox;

    @FindBy(css = "#addresses-link")
    private WebElement addresses;

    @FindBy(css = ".address-body")
    private WebElement myAddress;

    @FindBy(css = "#history-link")
    private WebElement orderHistory;

    @FindBy(css = "th[scope='row']")
    private List<WebElement> orderReferenceNumbersList;

    @Step("Log in")
    public AccountPage logIn(String email, String password) {
        loginEmailBox.sendKeys(email);
        loginPasswordBox.sendKeys(password);
        signInButton.click();
        return new AccountPage(driver);
    }

    @Step("Create account")
    public HomePage createAccount(String firstName, String lastName, String email, String password) {
        createAccountButton.click();
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        emailToSetBox.sendKeys(email);
        passwordToSetBox.sendKeys(password);
        customerPrivacyCheckbox.click();
        privacyAcceptanceCheckbox.click();
        createAccountBox.click();
        return new HomePage(driver);
    }

    @Step("Click reset password")
    public String resetPassword(String email) {
        passwordRecoveryButton.click();
        passwordResetEmail.sendKeys(email);
        sendResetLink.click();
        return resetResponseMessage.getText();
    }

    @Step("Find order number")
    public boolean findOrderNumber(String orderNumber) {
        return orderReferenceNumbersList.stream().anyMatch(WebElement -> WebElement.getText().equals(orderNumber));
    }

    @Step("Go to order history")
    public AccountPage goToOrderHistory() {
        orderHistory.click();
        return this;
    }

    @Step("Go to addresses")
    public AccountPage goToAddresses() {
        addresses.click();
        return this;
    }

    @Step("Get My Address contents")
    public String getMyAddressContents() {
        return myAddress.getText();
    }
}