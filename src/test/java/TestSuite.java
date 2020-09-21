import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class TestSuite extends BaseTest {

    @Test(priority = 1)
    public void sendHelpRequest() {

        String expectedMessage = "Your message has been successfully sent to our team.";
        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToContactUsPage();
        contactUsPage = new ContactUsPage(driver);
        contactUsPage.submitHelpRequest("Webmaster", "test@test.com", "test message");
        String actualMessage = contactUsPage.getResponseMessage();
        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Test(priority = 2)
    public void popularProducts() {

        int popularProductsCount = 8;
        homePage = new HomePage(driver);
        int actualCount = homePage.popularProducts.size();
        Assert.assertEquals(popularProductsCount, actualCount);
        String popularProductToFind = "good day poster";
        homePage.enterPopularProduct(popularProductToFind);
        productPage = new ProductPage(driver);
        Assert.assertEquals(popularProductToFind, productPage.getProductName().toLowerCase());

    }

    @Test(priority = 3)
    public void standaloneProduct() {

        homePage = new HomePage(driver);
        homePage.enterPopularProduct("good day poster");
        productPage = new ProductPage(driver);
        System.out.println(productPage.getQuantity());
    }

    @Test(priority = 4)
    public void cart() {

        homePage = new HomePage(driver);
        homePage.enterPopularProduct("good day poster");
        productPage = new ProductPage(driver);
        productPage.addProductToCart();
        productPage.continueShoppingButton.click();
        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToClothesSection();
        productsPage = new ProductsPage(driver);
        productsPage.goToProduct("hummingbird tshirt");
        productPage.addProductToCart();
        topMenuPage.goToCart();
        cartPage = new CartPage(driver);
        System.out.println(cartPage.getTotalPrice());
        cartPage.proceedToCheckout();

    }

    @Test(priority = 5)
    public void addRandomProducts() {

        // To be changed - rounding double number to a better solution (not in test)

        int numberOfRandomProducts;
        double productsPrice = 0;
        Random random = new Random();
        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToRandomSection();
        productsPage = new ProductsPage(driver);
        productPage = new ProductPage(driver);
        if (productsPage.allProducts.size() < 3) {
            numberOfRandomProducts = 2;
        } else {
            numberOfRandomProducts = 4;
        }
        for (int i = 0; i < numberOfRandomProducts; i++) {
            productsPage.allProducts.get(random.nextInt(productsPage.allProducts.size())).click();
            productsPrice += productPage.getProductPrice();
            productPage.addProductToCart();
            driver.navigate().back();
        }
        productsPrice = (double) Math.round(productsPrice * 100) / 100;
        topMenuPage.goToCart();
        cartPage = new CartPage(driver);
        int numberOfProducts = cartPage.getNumberOfItems();
        Assert.assertEquals(numberOfRandomProducts, numberOfProducts);
        Assert.assertEquals(cartPage.getItemsPrice(), productsPrice);
        cartPage.proceedToCheckout();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.placeOrderAsGuest(
                "Shop", "Presta", "shoppresta@test.com",
                "address", "city", "New York", "12345", "United States"
        );
        System.out.println(checkoutPage.getConfirmationMessage());

    }

    @Test(priority = 6)
    public void createAlreadyExitingAccount() {

        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToSignInSection();
        signInPage = new SignInPage(driver);
        signInPage.createAccountButton.click();
        signInPage.createAccount("Presta", "Shop", "prestashop@test.com", "test123");
        System.out.println(signInPage.getAlertMessage());
        String usernameAfterCreateAccount = topMenuPage.getLoggedUsername();
        System.out.println(usernameAfterCreateAccount);
        if (usernameAfterCreateAccount.equals("Presta Shop")) {
            Assert.fail();
        }

    }

    @Test(priority = 7)
    public void logIn() {

        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToSignInSection();
        signInPage = new SignInPage(driver);
        signInPage.logIn("prestashop@test.com", "test123");
        String expectedName = "Presta Shop";
        String actualName = topMenuPage.getLoggedUsername();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test(priority = 8)
    public void fullTest() {

        Random random = new Random();
        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToRandomSection();
        productsPage = new ProductsPage(driver);
        productsPage.allProducts.get(random.nextInt(productsPage.allProducts.size())).click();
        productPage = new ProductPage(driver);
        productPage.increaseQuantityBy(2);
        productPage.addProductToCart();
        topMenuPage.goToCart();
        cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.placeOrderAsGuest(
                "Shop", "Presta", "shoppresta@test.com",
                "address", "city", "New York", "12345", "United States"
        );
        String expectedMessage = "\uE876YOUR ORDER IS CONFIRMED";
        String actualMessage = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test(priority = 9)
    public void createAccountUsingJson() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("src\\main\\resources\\testdata\\AccountDetails.json"));
        JSONObject jsonObject = (JSONObject) object;
        String firstName = (String) jsonObject.get("firstName");
        String lastName = (String) jsonObject.get("lastName");
        String email = (String) jsonObject.get("email");
        String password = (String) jsonObject.get("password");

        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToSignInSection();
        signInPage = new SignInPage(driver);
        signInPage.createAccountButton.click();
        signInPage.createAccount(firstName, lastName, email, password);
        String userAfterLogin = topMenuPage.getLoggedUsername();
        Assert.assertEquals(firstName + " " + lastName, userAfterLogin);

    }


}
