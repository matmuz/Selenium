package pages.menu;

import pages.base.BasePage;
import io.qameta.allure.Step;
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

    @Step("Go to contact us")
    public ContactUsPage goToContactUsPage() {
        contactUsLink.click();
        return new ContactUsPage(driver);
    }

    @Step("Go to sign in section")
    public SignInPage goToSignInSection() {
        signInButton.click();
        return new SignInPage(driver);
    }

    @Step("Go to clothes section")
    public ProductsPage goToClothesSection() {
        for (WebElement category: categories){
            if (category.getText().contains("Clothes")){
                category.click();
            }
        }
        return new ProductsPage(driver);
    }

    @Step("Go to accessories section")
    public ProductsPage goToAccessoriesSection() {
        for (WebElement category: categories){
            if (category.getText().contains("Produkty powiązane")){
                category.click();
            }
        }
        return new ProductsPage(driver);
    }

    @Step("Go to art section")
    public ProductsPage goToArtSection() {
        for (WebElement category: categories){
            if (category.getText().contains("Art")){
                category.click();
            }
        }
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
        Random random = new Random();
        categories.get(random.nextInt(categories.size()))
                .click();
        return new ProductsPage(driver);
    }

    @Step("Go to cart")
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

    @Step("Log out")
    public HomePage signOut() {
        signOutButton.click();
        return new HomePage(driver);
    }
}
