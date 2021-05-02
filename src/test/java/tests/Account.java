package tests;

import base.BaseTest;
import base.TestMethods;
import org.testng.annotations.Test;

public class Account extends BaseTest {

    @Test
    public void logInNegativeCase() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(guestUser.getEmail(), guestUser.getPassword())
                                         .getTopMenuPage()
                                         .getLoggedUsername(), "null");
    }

    @Test
    public void createAccount() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .createAccount(testUser.getFirstName(), testUser.getLastName(), testUser.getEmail(), testUser.getPassword())
                                         .getTopMenuPage()
                                         .getLoggedUsername(), testUser.getFirstName() + " " + testUser.getLastName());
    }

    @Test
    public void createAccountNegativeCase() {

        TestMethods.assertNotEquals(prestaShop.openPrestaShop()
                                            .getTopMenuPage()
                                            .goToSignInSection()
                                            .createAccount(existingUser.getFirstName(), existingUser.getLastName(), existingUser.getEmail(), existingUser.getPassword())
                                            .getTopMenuPage()
                                            .getLoggedUsername(), existingUser.getFirstName() + " " + existingUser.getLastName());
    }

    @Test
    public void logIn() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(existingUser.getEmail(), existingUser.getPassword())
                                         .getTopMenuPage()
                                         .getLoggedUsername(), existingUser.getFirstName() + " " + existingUser.getLastName());
    }

    @Test
    public void logOut() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(existingUser.getEmail(), existingUser.getPassword())
                                         .getTopMenuPage()
                                         .signOut()
                                         .getTopMenuPage()
                                         .getLoggedUsername(), "null");
    }
}