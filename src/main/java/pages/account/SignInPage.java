package pages.account;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.menu.TopMenuPage;
import pages.products.HomePage;

import java.util.List;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
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

    @FindBy(css = "a[href*='password-recovery']")
    private WebElement passwordRecoveryButton;

    @FindBy(css = "#email")
    private WebElement passwordResetEmail;

    @FindBy(css = ".form-control-submit.btn.btn-primary.hidden-xs-down")
    private WebElement sendResetLink;

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

    @FindBy(css = "#history-link")
    private WebElement orderHistory;

    @FindBy(css = "th[scope='row']")
    private List<WebElement> orderReferenceNumbersList;

    @Step("Log in")
    public HomePage logIn(String email, String password) {
        loginEmailBox.sendKeys(email);
        loginPasswordBox.sendKeys(password);
        signInButton.click();
        return new HomePage(driver);
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
    public SignInPage resetPassword() {
        passwordRecoveryButton.click();
        return this;
    }

    @Step("Find order number")
    public String findOrderNumber(String orderNumber) {
        for (WebElement webElement : orderReferenceNumbersList) {
            if (webElement
                    .getText()
                    .equals(orderNumber)) {
                return webElement
                        .getText();
            }
        }
        return "null";
    }

    @Step("Go to order history")
    public SignInPage goToOrderHistory() {
        orderHistory.click();
        return new SignInPage(driver);
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}