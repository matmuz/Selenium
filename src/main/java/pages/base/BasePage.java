package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.menu.TopMenuPage;

/**
 * This class provides a core for all the subclassed pages of the app
 */
public abstract class BasePage {

    protected WebDriver driver;

    /**
     * Provides a common constructor for subclasses with driver and page initialization
     *
     * @param driver Web Driver instance used in subclasses (pages)
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets top menu page from any context of the app
     *
     * @return Top Menu Page instance
     */
    public TopMenuPage getTopMenuPage() {
        return new TopMenuPage(driver);
    }
}