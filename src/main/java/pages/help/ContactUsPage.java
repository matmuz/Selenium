package pages.help;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

/**
 * Contact us page class responsible for getting needed selectors form the page and providing methods for moving between the elements
 */
public final class ContactUsPage extends BasePage {

    public static final String HELP_RESPONSE_MESSAGE = "Your message has been successfully sent to our team.";
    public static final String ALERT_MESSAGE = "Invalid email address.";
    public static final String CUSTOMER_SERVICE = "Customer service";
    public static final String WEBMASTER = "Webmaster";

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "select[name='id_contact']")
    private WebElement subjectDropdown;

    @FindBy(css = "input[placeholder='your@email.com']")
    private WebElement emailAddressBox;

    @FindBy(css = "textarea[name='message']")
    private WebElement messageBox;

    @FindBy(css = "input[name='submitMessage']")
    private WebElement sendButton;

    @FindBy(css = "#content")
    private WebElement responseMessage;

    @FindBy(css = ".alert-danger")
    private WebElement alertMessage;

    @Step("Select subject")
    public void selectSubject(String subject) {
        new Select(subjectDropdown).selectByVisibleText(subject);
    }

    @Step("Set email")
    public void setEmail(String email) {
        emailAddressBox.sendKeys(email);
    }

    @Step("Set message")
    public void setMessage(String message) {
        messageBox.sendKeys(message);
    }

    @Step("Click send")
    public void clickSend() {
        sendButton.click();
    }

    @Step("Submit help request")
    public ContactUsPage submitHelpRequest(String subject, String email, String message) {
        selectSubject(subject);
        setEmail(email);
        setMessage(message);
        clickSend();
        return this;
    }

    @Step("Submit help request")
    public ContactUsPage submitHelpRequest(String subject, String message) {
        selectSubject(subject);
        setMessage(message);
        clickSend();
        return this;
    }

    @Step("Get response message")
    public String getResponseMessage() {
        return responseMessage.getText();
    }

    @Step("Get alert message")
    public String getAlertMessage() {
        return alertMessage.getText();
    }
}