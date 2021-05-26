package tests;

import base.BaseTest;
import data.TestData;
import org.testng.annotations.Test;

public class HelpRequest extends BaseTest {

    @Test
    public void sendContactMessageAsGuest() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(TestData.TEST_HELP_SUBJECT, guestUser.getEmail(), TestData.TEST_HELP_MESSAGE)
                                         .getResponseMessage(), TestData.HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void sendContactMessageAsLoggedUser() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(existingUser.getEmail(), existingUser.getPassword())
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(TestData.TEST_HELP_SUBJECT, TestData.TEST_HELP_MESSAGE)
                                         .getResponseMessage(), TestData.HELP_RESPONSE_MESSAGE);
    }

    @Test
    public void sendContactMessageNegativeCase() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(TestData.TEST_HELP_SUBJECT, TestData.TEST_HELP_MESSAGE)
                                         .getAlertMessage(), TestData.ALERT_MESSAGE);
    }
}