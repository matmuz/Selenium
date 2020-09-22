package account;

import base.BasePage;
import menu.TopMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import products.HomePage;

public class SignInPage extends BasePage {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".form-control")
    private WebElement loginEmailBox;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement loginPasswordBox;

    @FindBy(xpath = "//button[@id='submit-login']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[contains(text(),'No account? Create one here')]")
    private WebElement createAccountButton;

    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
    private WebElement passwordRecoveryButton;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement passwordResetEmail;

    @FindBy(xpath = "//button[contains(text(),'Send reset link')]")
    private WebElement sendResetLink;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstNameBox;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastNameBox;

    @FindBy(xpath = "//div[@class='col-md-6']//input[@name='email']")
    private WebElement emailToSetBox;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordToSetBox;

    @FindBy(xpath = "//button[@class='btn btn-primary form-control-submit float-xs-right']")
    private WebElement createAccountBox;

    @FindBy(xpath = "//li[@class='alert alert-danger']")
    private WebElement alertBox;

    public HomePage logIn(String email, String password) {
        loginEmailBox.sendKeys(email);
        loginPasswordBox.sendKeys(password);
        signInButton.click();
        return new HomePage(getDriver());
    }

    public HomePage createAccount(String firstName, String lastName, String email, String password) {

        createAccountButton.click();
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        emailToSetBox.sendKeys(email);
        passwordToSetBox.sendKeys(password);
        createAccountBox.click();
        return new HomePage(getDriver());

    }

    public TopMenuPage getTopMenuPage(){
        return new TopMenuPage(getDriver());
    }
}
