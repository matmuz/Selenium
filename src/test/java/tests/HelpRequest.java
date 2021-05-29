package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static data.ExistingUser.EMAIL;
import static data.ExistingUser.PASSWORD;
import static pages.help.ContactUsPage.ALERT_MESSAGE;
import static pages.help.ContactUsPage.HELP_RESPONSE_MESSAGE;
import static tests.TestMethods.assertEquals;

public class HelpRequest extends BaseTest {

    private static final String TEST_HELP_SUBJECT = "Biuro Obsługi Klienta";
    private static final String TEST_HELP_MESSAGE = "Test message 123";

    @Test
    public void sendContactMessageAsGuest() {
        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToContactUsPage()
                             .submitHelpRequest(TEST_HELP_SUBJECT, guestUser.getEmail(), TEST_HELP_MESSAGE)
                             .getResponseMessage(), HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void sendContactMessageAsLoggedUser() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToSignInSection()
                             .logIn(EMAIL, PASSWORD)
                             .getTopMenuPage()
                             .goToContactUsPage()
                             .submitHelpRequest(TEST_HELP_SUBJECT, TEST_HELP_MESSAGE)
                             .getResponseMessage(), HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void sendContactMessageNegativeCase() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToContactUsPage()
                             .submitHelpRequest(TEST_HELP_SUBJECT, TEST_HELP_MESSAGE)
                             .getAlertMessage(), ALERT_MESSAGE);
    }
}