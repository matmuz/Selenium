import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "form-control")
    WebElement loginEmailBox;

    @FindBy(xpath = "//input[@name='password']")
    WebElement loginPasswordBox;

    @FindBy(xpath = "//button[@id='submit-login']")
    WebElement signInButton;

    @FindBy(xpath = "//a[contains(text(),'No account? Create one here')]")
    WebElement createAccountButton;

    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
    WebElement passwordRecoveryButton;

    @FindBy(xpath = "//input[@id='email']")
    WebElement passwordResetEmail;

    @FindBy(xpath = "//button[contains(text(),'Send reset link')]")
    WebElement sendResetLink;

    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstNameBox;

    @FindBy(xpath = "//input[@name='lastname']")
    WebElement lastNameBox;

    @FindBy(xpath = "//div[@class='col-md-6']//input[@name='email']")
    WebElement emailToSetBox;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordToSetBox;

    @FindBy(xpath = "//button[@class='btn btn-primary form-control-submit float-xs-right']")
    WebElement createAccountBox;

    @FindBy(xpath = "//li[@class='alert alert-danger']")
    WebElement alertBox;

    public void logIn(String email, String password) {
        loginEmailBox.sendKeys(email);
        loginPasswordBox.sendKeys(password);
        signInButton.click();
    }

    public void createAccount(String firstName, String lastName, String email, String password) {

        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        emailToSetBox.sendKeys(email);
        passwordToSetBox.sendKeys(password);
        createAccountBox.click();

    }

    public String getAlertMessage(){
        return alertBox.getText();
    }

}
