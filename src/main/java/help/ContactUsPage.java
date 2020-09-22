package help;

import base.BasePage;
import menu.TopMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends BasePage {

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//select[@name='id_contact']")
    private WebElement subjectDropdown;

    @FindBy(xpath = "//input[@placeholder='your@email.com']")
    private WebElement emailAddressBox;

    @FindBy(xpath = "//div[@class='bootstrap-filestyle input-group']//input[contains(@class,'form-control')]")
    private WebElement attachmentPath;

    @FindBy(xpath = "//textarea[@placeholder='How can we help?']")
    private WebElement messageBox;

    @FindBy(xpath = "//input[@name='submitMessage']")
    private WebElement sendButton;

    @FindBy(xpath = "//section[@id='content']")
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
        return new TopMenuPage(getDriver());
    }
}
