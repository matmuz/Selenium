package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static data.ExistingUser.EMAIL;
import static data.ExistingUser.PASSWORD;
import static pages.help.ContactUsPage.ALERT_MESSAGE;
import static pages.help.ContactUsPage.HELP_RESPONSE_MESSAGE;
import static tests.TestMethods.assertEquals;

/**
 * A collection of tests that check sending help requests functionality
 */

public class HelpRequest extends BaseTest {

    /**
     * Test constants (data) that are used in tests
     */

    private static final String TEST_HELP_SUBJECT = "Customer service";
    private static final String TEST_HELP_MESSAGE = "Test message 123";

    @Test
    public void shouldSendContactMessageAsGuest() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToContactUsPage()
                             .submitHelpRequest(TEST_HELP_SUBJECT, guestUser.getEmail(), TEST_HELP_MESSAGE)
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
                             .submitHelpRequest(TEST_HELP_SUBJECT, TEST_HELP_MESSAGE)
                             .getResponseMessage(), HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void shouldNotSendContactMessageAsGuest() {

        assertEquals(prestaShop.openPrestaShop()
                             .getTopMenuPage()
                             .goToContactUsPage()
                             .submitHelpRequest(TEST_HELP_SUBJECT, TEST_HELP_MESSAGE)
                             .getAlertMessage(), ALERT_MESSAGE);
    }
}