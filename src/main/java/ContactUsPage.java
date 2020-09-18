import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {

    WebDriver driver;

    public ContactUsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@name='id_contact']")
    WebElement subjectDropdown;

    @FindBy(xpath = "//input[@placeholder='your@email.com']")
    WebElement emailAddressBox;

    @FindBy(xpath = "//div[@class='bootstrap-filestyle input-group']//input[contains(@class,'form-control')]")
    WebElement attachmentPath;

    @FindBy(xpath = "//textarea[@placeholder='How can we help?']")
    WebElement messageBox;

    @FindBy(xpath = "//input[@name='submitMessage']")
    WebElement sendButton;

    @FindBy(xpath = "//section[@id='content']")
    WebElement responseMessage;


    public void selectSubject(String subject){
        Select select = new Select(subjectDropdown);
        select.selectByVisibleText(subject);
    }

    public void setEmail(String email){
        emailAddressBox.sendKeys(email);
    }

    public void addAttachment(String pathToFile){
        attachmentPath.sendKeys(pathToFile);
    }

    public void setMessage(String message){
        messageBox.sendKeys(message);
    }

    public void clickSend(){
        sendButton.click();
    }

    public void submitHelpRequest(String subject, String email, String message){
        selectSubject(subject);
        setEmail(email);
        setMessage(message);
        clickSend();
    }

    public String getResponseMessage(){
        return responseMessage.getText();
    }
}
