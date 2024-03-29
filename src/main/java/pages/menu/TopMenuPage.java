package pages.menu;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.account.AccountPage;
import pages.base.BasePage;
import pages.cart.CartPage;
import pages.help.ContactUsPage;
import pages.products.HomePage;
import pages.products.ProductsPage;

import java.util.List;

import static helpers.utils.Utils.getRandomElementFromList;

/**
 * Top menu page class responsible for getting needed selectors form the top menu and providing methods for moving between the elements
 */
public final class TopMenuPage extends BasePage {

    public static final String LOGIN_TEXT = "Sign in";

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href*='contact']")
    private WebElement contactUsLink;

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchBox;

    @FindBy(css = ".material-icons.search")
    private WebElement searchButton;

    @FindBy(css = "span[class='hidden-sm-down']")
    private WebElement signInButton;

    @FindBy(css = ".material-icons.shopping-cart")
    private WebElement cartIcon;

    @FindBy(css = ".cart-products-count")
    private WebElement cartProductsCount;

    @FindBy(css = "ul[id='top-menu'] a[class='dropdown-item']")
    private List<WebElement> categories;

    @FindBy(css = ".account")
    private WebElement loggedUserBox;

    @FindBy(css = ".logout.hidden-sm-down")
    private WebElement signOutButton;

    @FindBy(css = ".logo.img-responsive")
    private WebElement myStoreButton;

    @Step("Go to home page")
    public HomePage goToHomePage() {
        myStoreButton.click();
        return new HomePage(driver);
    }

    @Step("Go to contact us")
    public ContactUsPage goToContactUsPage() {
        contactUsLink.click();
        return new ContactUsPage(driver);
    }

    @Step("Go to sign in section")
    public AccountPage goToSignInSection() {
        signInButton.click();
        return new AccountPage(driver);
    }

    @Step("Go to clothes section")
    public ProductsPage goToClothesSection() {
        categories.get(0).click();
        return new ProductsPage(driver);
    }

    @Step("Go to accessories section")
    public ProductsPage goToAccessoriesSection() {
        categories.get(1).click();
        return new ProductsPage(driver);
    }

    @Step("Go to art section")
    public ProductsPage goToArtSection() {
        categories.get(2).click();
        return new ProductsPage(driver);
    }

    @Step("Search by text")
    public ProductsPage searchByText(String searchInput) {
        searchBox.sendKeys(searchInput);
        searchButton.click();
        return new ProductsPage(driver);
    }

    @Step("Go to random products section")
    public ProductsPage goToRandomProductsSection() {
        categories.get(getRandomElementFromList(categories)).click();
        return new ProductsPage(driver);
    }

    @Step("Go to cart")
    public CartPage goToCart() {
        cartIcon.click();
        return new CartPage(driver);
    }

    @Step("Get logged username")
    public String getLoggedUsername() {
        try {
            return loggedUserBox.getText();
        } catch (NoSuchElementException exception) {
            return signInButton.getText();
        }
    }

    @Step("Log out")
    public HomePage signOut() {
        signOutButton.click();
        return new HomePage(driver);
    }
}
