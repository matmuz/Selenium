package tests;

import base.BaseTest;
import base.TestMethods;
import data.ITestData;
import org.testng.annotations.Test;

public class HelpRequest extends BaseTest implements ITestData {

    @Test
    public void sendContactMessageAsGuest() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(TEST_HELP_SUBJECT, guestUser.getEmail(), TEST_HELP_MESSAGE)
                                         .getResponseMessage(), HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void sendContactMessageAsLoggedUser() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(existingUser.getEmail(), existingUser.getPassword())
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(TEST_HELP_SUBJECT, TEST_HELP_MESSAGE)
                                         .getResponseMessage(), HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void sendContactMessageNegativeCase() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(TEST_HELP_SUBJECT, TEST_HELP_MESSAGE)
                                         .getAlertMessage(), ALERT_MESSAGE);
    }
}