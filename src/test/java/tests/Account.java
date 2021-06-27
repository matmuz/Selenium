package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static data.ExistingUser.*;
import static issues.DefectHandler.skipTestIfDefectIsOpen;
import static issues.Defects.DEFECT_1;
import static pages.account.SignInPage.RESET_RESPONSE;
import static pages.menu.TopMenuPage.LOGIN_TEXT;
import static tests.TestMethods.assertEquals;
import static tests.TestMethods.assertNotEquals;

/**
 * A collection of tests that check basic account functionalities
 */

public class Account extends BaseTest {

    @Test
    public void shouldNotLogIn() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .logIn(guestUser.getEmail(), guestUser.getPassword())
                             .getTopMenuPage()
                             .getLoggedUsername(), LOGIN_TEXT);
    }

    @Test
    public void shouldCreateAnAccount() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .createAccount(testUser.getFirstName(), testUser.getLastName(), testUser.getEmail(), testUser.getPassword())
                             .getTopMenuPage()
                             .getLoggedUsername(), testUser.getFullName());
    }

    @Test
    public void shouldNotCreateAnAccount() {

        assertNotEquals(prestaShop.openPrestaShop()
                                .getTopMenuPage()
                                .goToSignInSection()
                                .createAccount(FIRSTNAME, LASTNAME, EMAIL, PASSWORD)
                                .getTopMenuPage()
                                .getLoggedUsername(), getFullName());
    }

    @Test
    public void shouldLogIn() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .logIn(EMAIL, PASSWORD)
                             .getTopMenuPage()
                             .getLoggedUsername(), getFullName());
    }

    @Test
    public void shouldLogOut() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .logIn(EMAIL, PASSWORD)
                             .getTopMenuPage()
                             .signOut()
                             .getTopMenuPage()
                             .getLoggedUsername(), LOGIN_TEXT);
    }

    @Test
    public void shouldSendResetPasswordLink() {

        skipTestIfDefectIsOpen(DEFECT_1);

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .resetPassword(EMAIL), RESET_RESPONSE + EMAIL);
    }
}