import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopMenuPage {

    WebDriver driver;

    public TopMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[@id='contact-link']//a[contains(text(),'Contact us')]")
    WebElement contactUsLink;

    @FindBy(xpath = "(//a[@class='dropdown-item'])[1]")
    WebElement clothesDropdown;

    @FindBy(xpath = "(//a[@class='dropdown-item'])[2]")
    WebElement accessoriesDropdown;

    @FindBy(xpath = "(//a[@class='dropdown-item'])[3]")
    WebElement artLink;

    @FindBy(xpath = "//input[@placeholder='Search our catalog']")
    WebElement searchBox;

    @FindBy(xpath = "//div[@id='search_widget']//i[@class='material-icons search']")
    WebElement searchButton;

    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    WebElement signInButton;

    @FindBy(xpath = "//span[contains(text(),'Cart')]")
    WebElement cartIcon;

    @FindBy(xpath = "//span[@class='cart-products-count']")
    WebElement cartProductsCount;

    public void goToContactUsPage() {
        contactUsLink.click();
    }

    public void goToClothesSection(String gender) {
        Actions action = new Actions(driver);
        action.moveToElement(clothesDropdown).build().perform();
        if (gender.equals("men")) {
            driver.findElement(By.xpath("//li[@id='category-4']//a[@class='dropdown-item dropdown-submenu'][contains(text(),'MEN')]")).click();
        } else if (gender.equals("women")) {
            driver.findElement(By.xpath("//a[contains(text(),'WOMEN')]")).click();
        } else {
            System.out.println("Invalid gender input. Put either 'men' or 'women'.");
        }
    }

    public void goToAccessoriesSection(String type) {
        Actions action = new Actions(driver);
        action.moveToElement(accessoriesDropdown).build().perform();
        if (type.equals("stationary")) {
            driver.findElement(By.xpath("//a[contains(text(),'STATIONERY')]")).click();
        } else if (type.equals("home")) {
            driver.findElement(By.xpath("//a[contains(text(),'HOME')]")).click();
        } else {
            System.out.println("Invalid type input. Put either 'stationary' or 'home'.");
        }
    }

    public void goToArtSection() {
        artLink.click();
    }

    public void searchByText(String searchInput) {
        searchBox.sendKeys(searchInput);
        searchButton.click();
    }

    public void goToSignInSection() {
        signInButton.click();
    }

    public void goToCart() {
        cartIcon.click();
    }

    public void getNumberOfItemsInCart() {
        cartProductsCount.getText();
    }

}
