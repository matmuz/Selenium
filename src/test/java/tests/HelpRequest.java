package tests;

import base.BaseTest;
import base.TestMethods;
import org.testng.annotations.Test;

public class HelpRequest extends BaseTest {

    @Test
    public void sendContactMessageAsGuest() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(testData.getHelpSubject(), guestUser.getEmail(), testData.getHelpMessage())
                                         .getResponseMessage(), testData.getHelpResponseMessage());
    }

    @Test
    public void sendContactMessageAsLoggedUser() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(existingUser.getEmail(), existingUser.getPassword())
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(testData.getHelpSubject(), testData.getHelpMessage())
                                         .getResponseMessage(), testData.getHelpResponseMessage());
    }

    @Test
    public void sendContactMessageNegativeCase() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(testData.getHelpSubject(), testData.getHelpMessage())
                                         .getAlertMessage(), testData.getAlertMessage());
    }
}