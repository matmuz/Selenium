package pages.menu;

import base.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.account.SignInPage;
import pages.cart.CartPage;
import pages.help.ContactUsPage;
import pages.products.HomePage;
import pages.products.ProductsPage;

import java.util.List;
import java.util.Random;

public class TopMenuPage extends BasePage {

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href*='contact-us']")
    private WebElement contactUsLink;

    @FindBy(css = "a[href*='clothes']")
    private WebElement clothesDropdown;

    @FindBy(css = "a[href*='accessories']")
    private WebElement accessoriesDropdown;

    @FindBy(css = "a[href*='9-art']")
    private WebElement artLink;

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

    @FindBy(css = "a[class='dropdown-item']")
    private List<WebElement> categories;

    @FindBy(css = ".account")
    private WebElement loggedUserBox;

    @FindBy(css = ".logout.hidden-sm-down")
    private WebElement signOutButton;

    @FindBy(css = ".logo.img-responsive")
    private WebElement myStoreButton;

    public HomePage goToHomePage() {
        myStoreButton.click();
        return new HomePage(driver);
    }

    public ContactUsPage goToContactUsPage() {
        contactUsLink.click();
        return new ContactUsPage(driver);
    }

    public SignInPage goToSignInSection() {
        signInButton.click();
        return new SignInPage(driver);
    }

    public ProductsPage goToClothesSection() {
        clothesDropdown.click();
        return new ProductsPage(driver);
    }

    public ProductsPage goToAccessoriesSection() {
        accessoriesDropdown.click();
        return new ProductsPage(driver);
    }

    public ProductsPage goToArtSection() {
        artLink.click();
        return new ProductsPage(driver);
    }

    public ProductsPage searchByText(String searchInput) {
        searchBox.sendKeys(searchInput);
        searchButton.click();
        return new ProductsPage(driver);
    }

    public ProductsPage goToRandomProductsSection() {
        Random random = new Random();
        categories.get(random.nextInt(categories.size()))
                .click();
        return new ProductsPage(driver);
    }

    public CartPage goToCart() {
        cartIcon.click();
        return new CartPage(driver);
    }

    public String getLoggedUsername() {
        try {
            return loggedUserBox.getText();
        } catch (NoSuchElementException exception) {
            return "null";
        }
    }

    public HomePage signOut() {
        signOutButton.click();
        return new HomePage(driver);
    }
}
