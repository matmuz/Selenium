import org.testng.Assert;
import org.testng.annotations.Test;

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
//        cartPage.proceedToCheckout();
//        checkoutPage = new CheckoutPage(driver);
//        checkoutPage.placeOrder();
//        System.out.println(checkoutPage.getConfirmationMessage());

    }

}
