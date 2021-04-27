package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class Account extends BaseTest {

    @Test
    public void logInNegativeCase() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(guestUser.getEmail(), guestUser.getPassword())
                                  .getTopMenuPage()
                                  .getLoggedUsername(), "null");
    }

    @Test
    public void createAccount() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .createAccount(testUser.getFirstName(), testUser.getLastName(), testUser.getEmail(), testUser.getPassword())
                                  .getTopMenuPage()
                                  .getLoggedUsername(), testUser.getFirstName() + " " + testUser.getLastName());
    }

    @Test
    public void createAccountNegativeCase() {

        test.assertNotEquals(prestaShop.openPrestaShop()
                                     .getTopMenuPage()
                                     .goToSignInSection()
                                     .createAccount(existingUser.getFirstName(), existingUser.getLastName(), existingUser.getEmail(), existingUser.getPassword())
                                     .getTopMenuPage()
                                     .getLoggedUsername(), existingUser.getFirstName() + " " + existingUser.getLastName());
    }

    @Test
    public void logIn() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(existingUser.getEmail(), existingUser.getPassword())
                                  .getTopMenuPage()
                                  .getLoggedUsername(), existingUser.getFirstName() + " " + existingUser.getLastName());
    }

    @Test
    public void logOut() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(existingUser.getEmail(), existingUser.getPassword())
                                  .getTopMenuPage()
                                  .signOut()
                                  .getTopMenuPage()
                                  .getLoggedUsername(), "null");
    }
}