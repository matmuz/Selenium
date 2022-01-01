package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static data.ExistingUser.EMAIL;
import static data.ExistingUser.PASSWORD;
import static pages.help.ContactUsPage.*;
import static utils.data.TestData.TEST_HELP_MESSAGE;
import static utils.methods.TestMethods.assertEquals;

/**
 * A collection of tests that check sending help requests functionality
 */
public final class ContactUsTests extends BaseTest {

    @Test
    public void shouldSendContactMessageAsGuest() {

        assertEquals(prestaShop.openPrestaShop()
                               .getTopMenuPage()
                               .goToContactUsPage()
                               .submitHelpRequest(CUSTOMER_SERVICE, guestUser.getEmail(), TEST_HELP_MESSAGE)
                               .getResponseMessage(), HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void shouldSendContactMessageAsLoggedUser() {

        assertEquals(prestaShop.openPrestaShop()
                               .getTopMenuPage()
                               .goToSignInSection()
                               .logIn(EMAIL, PASSWORD)
                               .getTopMenuPage()
                               .goToContactUsPage()
                               .submitHelpRequest(WEBMASTER, TEST_HELP_MESSAGE)
                               .getResponseMessage(), HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void shouldNotSendContactMessageAsGuest() {

        assertEquals(prestaShop.openPrestaShop()
                               .getTopMenuPage()
                               .goToContactUsPage()
                               .submitHelpRequest(CUSTOMER_SERVICE, TEST_HELP_MESSAGE)
                               .getAlertMessage(), ALERT_MESSAGE);
    }
}