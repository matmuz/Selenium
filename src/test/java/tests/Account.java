package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static data.TestData.LOGIN_TEXT;
import static data.TestData.RESET_RESPONSE;
import static tests.TestMethods.assertEquals;
import static tests.TestMethods.assertNotEquals;

public class Account extends BaseTest {

    @Test
    public void logInNegativeCase() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .logIn(guestUser.getEmail(), guestUser.getPassword())
                             .getTopMenuPage()
                             .getLoggedUsername(), LOGIN_TEXT);
    }

    @Test
    public void createAccount() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .createAccount(testUser.getFirstName(), testUser.getLastName(), testUser.getEmail(), testUser.getPassword())
                             .getTopMenuPage()
                             .getLoggedUsername(), testUser.getFullName());
    }

    @Test
    public void createAccountNegativeCase() {

        assertNotEquals(prestaShop.openPrestaShop()
                                .getTopMenuPage()
                                .goToSignInSection()
                                .createAccount(existingUser.getFirstName(), existingUser.getLastName(), existingUser.getEmail(), existingUser.getPassword())
                                .getTopMenuPage()
                                .getLoggedUsername(), existingUser.getFullName());
    }

    @Test
    public void logIn() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .logIn(existingUser.getEmail(), existingUser.getPassword())
                             .getTopMenuPage()
                             .getLoggedUsername(), existingUser.getFullName());
    }

    @Test
    public void logOut() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .logIn(existingUser.getEmail(), existingUser.getPassword())
                             .getTopMenuPage()
                             .signOut()
                             .getTopMenuPage()
                             .getLoggedUsername(), LOGIN_TEXT);
    }

    @Test
    public void resetPassword() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .resetPassword(existingUser.getEmail()), RESET_RESPONSE + existingUser.getEmail());
    }
}