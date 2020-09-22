package tests;

import base.BaseTest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;


public class TestSuite extends BaseTest {

    @Test(priority = 1)
    public void sendHelpRequestTest() {

        String expectedMessage = "Your message has been successfully sent to our team.";
        Assert.assertEquals(prestaShop.
                                    openPrestaShop().
                                    getTopMenuPage().
                                    goToContactUsPage().
                                    submitHelpRequest("Webmaster", "test@test.com", "test message").
                                    getResponseMessage(),
                            expectedMessage);

    }

    @Test(priority = 2)
    public void singleProductTest() {

        System.out.println(prestaShop.
                openPrestaShop().
                getTopMenuPage().
                goToClothesSection().
                goToProduct("hummingbird").
                addProductToCart().
                getTopMenuPage().
                goToCart().
                proceedToCheckout().
                placeOrderAsGuest("test", "testowy", "test@testowy.com", "test",
                                  "test", "New York", "12345", "United States").
                getConfirmationMessage());

    }

    @Test(priority = 3)
    public void cartTest() {

        System.out.println(prestaShop.
                openPrestaShop().
                enterPopularProduct("good day poster").
                getTopMenuPage().
                goToAccessoriesSection().
                goToProduct("mountain fox notebook").
                addProductToCart().
                getTopMenuPage().
                goToCart().
                getTotalPrice());
    }

    @Test(priority = 4)
    public void createAlreadyExitingAccount() {

        String usernameAfterCreateAccount = prestaShop.
                openPrestaShop().
                getTopMenuPage().
                goToSignInSection().
                createAccount("Presta", "Shop", "prestashop@test.com", "test123").
                getTopMenuPage().
                getLoggedUsername();
        if (usernameAfterCreateAccount.equals("Presta Shop")) {
            Assert.fail();
        }

    }

    @Test(priority = 5)
    public void logIn() {

        System.out.println(prestaShop
                                   .openPrestaShop()
                                   .getTopMenuPage()
                                   .goToSignInSection()
                                   .logIn("prestashop@test.com", "test123").getTopMenuPage().getLoggedUsername());

    }

    @Test(priority = 6)
    public void fullTest() {

        System.out.println(prestaShop.
                openPrestaShop().
                getTopMenuPage().
                goToAccessoriesSection().
                goToProduct("mountain fox notebook").
                increaseQuantityBy(1).
                addProductToCart().
                getTopMenuPage().
                goToCart().
                proceedToCheckout().
                placeOrderAsGuest("Shop", "Presta", "shoppresta@test.com", "address",
                                  "city", "New York", "12345", "United States").
                getConfirmationMessage());

    }

    @Test(priority = 7)
    public void createAccountUsingJson() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("src\\main\\resources\\testdata\\AccountDetails.json"));
        JSONObject jsonObject = (JSONObject) object;
        String firstName = (String) jsonObject.get("firstName");
        String lastName = (String) jsonObject.get("lastName");
        String email = (String) jsonObject.get("email");
        String password = (String) jsonObject.get("password");


        String userAfterLogin = prestaShop.
                openPrestaShop().
                getTopMenuPage().
                goToSignInSection().
                createAccount(firstName, lastName, email, password).
                getTopMenuPage().
                getLoggedUsername();
        Assert.assertEquals(firstName + " " + lastName, userAfterLogin);

    }

    @Test
    public void dryRunTestOne(){


    }


}
