package tests;

import base.BaseTest;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.util.List;

import static assertions.CustomAssertions.*;
import static data.ExistingUser.*;
import static issues.DefectHandler.skipTestIfDefectIsOpen;
import static issues.Defects.DEFECT21;
import static pages.account.AccountPage.RESET_RESPONSE;
import static pages.menu.TopMenuPage.LOGIN_TEXT;

/**
 * A collection of tests that check basic account functionalities
 */
public final class AccountTests extends BaseTest {

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
                               .createAccount(testUser.getFirstName(), testUser.getLastName(), testUser.getEmail(),
                                              testUser.getPassword())
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
    public void shouldCheckSavedAddress() {

        assertTextContains(prestaShop.openPrestaShop()
                                     .getTopMenuPage()
                                     .goToSignInSection()
                                     .logIn(EMAIL, PASSWORD)
                                     .goToAddresses()
                                     .getMyAddressContents(), List.of(ADDRESS, POSTAL_CODE, CITY));
    }

    @Issue("https://github.com/matmuz/Automation/issues/21")
    @Test
    public void shouldSendResetPasswordLink() {

        skipTestIfDefectIsOpen(DEFECT21);

        assertEquals(prestaShop.openPrestaShop()
                               .getTopMenuPage()
                               .goToSignInSection()
                               .resetPassword(EMAIL), RESET_RESPONSE + EMAIL);
    }
}