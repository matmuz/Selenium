package pages.help;

import pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.menu.TopMenuPage;

public class ContactUsPage extends BasePage {

    public static final String HELP_RESPONSE_MESSAGE = "Twoja wiadomość została pomyślnie wysłana do obsługi.";
    public static final String ALERT_MESSAGE = "Nieprawidłowy adres e-mail";

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "select[name='id_contact']")
    private WebElement subjectDropdown;

    @FindBy(css = "input[placeholder='twój@email.com']")
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
        Select select = new Select(subjectDropdown);
        select.selectByVisibleText(subject);
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

    public String getResponseMessage() {
        return responseMessage.getText();
    }

    public String getAlertMessage() {
        return alertMessage.getText();
    }

    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}