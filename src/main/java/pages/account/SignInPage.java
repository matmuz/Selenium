package pages.account;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.menu.TopMenuPage;
import pages.products.HomePage;

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

    @FindBy(css = ".btn.btn-primary.form-control-submit.float-xs-right")
    private WebElement createAccountBox;

    @FindBy(css = ".alert.alert-danger")
    private WebElement alertBox;

    public HomePage logIn(String email, String password) {
        loginEmailBox.sendKeys(email);
        loginPasswordBox.sendKeys(password);
        signInButton.click();
        return new HomePage(driver);
    }

    public HomePage createAccount(String firstName, String lastName, String email, String password) {

        createAccountButton.click();
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        emailToSetBox.sendKeys(email);
        passwordToSetBox.sendKeys(password);
        createAccountBox.click();
        return new HomePage(driver);

    }

    public SignInPage resetPassword(){
        passwordRecoveryButton.click();
        return this;
    }

    public TopMenuPage getTopMenuPage(){
        return new TopMenuPage(driver);
    }
}
