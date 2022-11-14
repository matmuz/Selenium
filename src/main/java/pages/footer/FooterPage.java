package pages.footer;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public final class FooterPage extends BasePage {

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#footer")
    private WebElement footer;

    @Step("Get Footer's visibility")
    public boolean isFooterDisplayed() {
        return footer.isDisplayed();
    }
}
