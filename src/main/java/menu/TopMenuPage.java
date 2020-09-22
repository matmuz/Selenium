package menu;

import account.SignInPage;
import base.BasePage;
import cart.CartPage;
import help.ContactUsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import products.HomePage;
import products.ProductsPage;

import java.util.List;
import java.util.Random;

public class TopMenuPage extends BasePage {

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='contact-link']//a[contains(text(),'Contact us')]")
    private WebElement contactUsLink;

    @FindBy(xpath = "(//a[@class='dropdown-item'])[1]")
    private WebElement clothesDropdown;

    @FindBy(xpath = "(//a[@class='dropdown-item'])[2]")
    private WebElement accessoriesDropdown;

    @FindBy(xpath = "(//a[@class='dropdown-item'])[3]")
    private WebElement artLink;

    @FindBy(xpath = "//input[@placeholder='Search our catalog']")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@id='search_widget']//i[@class='material-icons search']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    private WebElement signInButton;

    @FindBy(xpath = "//span[contains(text(),'Cart')]")
    private WebElement cartIcon;

    @FindBy(xpath = "//span[@class='cart-products-count']")
    private WebElement cartProductsCount;

    @FindBy(xpath = "//a[@class='dropdown-item']")
    private List<WebElement> categories;

    @FindBy(xpath = "(//span[@class='hidden-sm-down'])[1]")
    private WebElement loggedUserBox;

    @FindBy(xpath = "//a[@class='logout hidden-sm-down']")
    private WebElement signOutButton;

    public List<WebElement> getCategories() {
        return categories;
    }

    public ContactUsPage goToContactUsPage() {
        contactUsLink.click();
        return new ContactUsPage(getDriver());
    }

    public SignInPage goToSignInSection() {
        signInButton.click();
        return new SignInPage(getDriver());
    }

    public ProductsPage goToClothesSection() {
        clothesDropdown.click();
        return new ProductsPage(getDriver());
    }

    public ProductsPage goToAccessoriesSection() {
        accessoriesDropdown.click();
        return new ProductsPage(getDriver());
    }

    public ProductsPage goToArtSection() {
        artLink.click();
        return new ProductsPage(getDriver());
    }

    public ProductsPage searchByText(String searchInput) {
        searchBox.sendKeys(searchInput);
        searchButton.click();
        return new ProductsPage(getDriver());
    }

    public ProductsPage goToRandomProductsSection() {
        Random random = new Random();
        categories.get(random.nextInt(categories.size())).click();
        return new ProductsPage(getDriver());
    }

    public CartPage goToCart() {
        cartIcon.click();
        return new CartPage(getDriver());
    }

    public String getLoggedUsername() {
        return loggedUserBox.getText();
    }

    public HomePage signOut() {
        signOutButton.click();
        return new HomePage(getDriver());
    }

}
