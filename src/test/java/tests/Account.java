package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class Account extends BaseTest {

    @Test(priority = 1)
    public void logInNegativeCase() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(testUser.getEmail(), testUser.getPassword())
                                  .getTopMenuPage()
                                  .getLoggedUsername(), "null");
    }

    @Test(priority = 2)
    public void createAccount() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .createAccount(testUser.getFirstName(), testUser.getLastName(), testUser.getEmail(), testUser.getPassword())
                                  .getTopMenuPage()
                                  .getLoggedUsername(), testUser.getFirstName() + " " + testUser.getLastName());
    }

    @Test(priority = 3)
    public void createAccountNegativeCase() {

        test.assertNotEquals(prestaShop.openPrestaShop()
                                     .getTopMenuPage()
                                     .goToSignInSection()
                                     .createAccount(testUser.getFirstName(), testUser.getLastName(), testUser.getEmail(), testUser.getPassword())
                                     .getTopMenuPage()
                                     .getLoggedUsername(), testUser.getFirstName() + " " + testUser.getLastName());
    }

    @Test(priority = 4)
    public void logIn() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(testUser.getEmail(), testUser.getPassword())
                                  .getTopMenuPage()
                                  .getLoggedUsername(), testUser.getFirstName() + " " + testUser.getLastName());
    }

    @Test(priority = 5)
    public void logOut() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(testUser.getEmail(), testUser.getPassword())
                                  .getTopMenuPage()
                                  .signOut()
                                  .getTopMenuPage()
                                  .getLoggedUsername(), "null");
    }
}