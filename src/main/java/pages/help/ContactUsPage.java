package pages.help;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.menu.TopMenuPage;

public class ContactUsPage extends BasePage {

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "select[name='id_contact']")
    private WebElement subjectDropdown;

    @FindBy(css = "input[placeholder='your@email.com']")
    private WebElement emailAddressBox;

    @FindBy(css = ".form-control")
    private WebElement attachmentPath;

    @FindBy(css = "textarea[name='message']")
    private WebElement messageBox;

    @FindBy(css = "input[name='submitMessage']")
    private WebElement sendButton;

    @FindBy(css = "#content")
    private WebElement responseMessage;


    public void selectSubject(String subject) {
        Select select = new Select(subjectDropdown);
        select.selectByVisibleText(subject);
    }

    public void setEmail(String email) {
        emailAddressBox.sendKeys(email);
    }

    public void addAttachment(String pathToFile) {
        attachmentPath.sendKeys(pathToFile);
    }

    public void setMessage(String message) {
        messageBox.sendKeys(message);
    }

    public void clickSend() {
        sendButton.click();
    }

    public ContactUsPage submitHelpRequest(String subject, String email, String message) {
        selectSubject(subject);
        setEmail(email);
        setMessage(message);
        clickSend();
        return this;
    }

    public String getResponseMessage() {
        return responseMessage.getText();
    }

    public TopMenuPage getTopMenuPage(){
        return new TopMenuPage(driver);
    }
}
