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
                                         .submitHelpRequest(getHelpSubject(), guestUser.getEmail(), getHelpMessage())
                                         .getResponseMessage(), getHelpResponseMessage());
    }

    @Test
    public void sendContactMessageAsLoggedUser() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToSignInSection()
                                         .logIn(existingUser.getEmail(), existingUser.getPassword())
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(getHelpSubject(), getHelpMessage())
                                         .getResponseMessage(), getHelpResponseMessage());
    }

    @Test
    public void sendContactMessageNegativeCase() {

        TestMethods.assertEquals(prestaShop.openPrestaShop()
                                         .getTopMenuPage()
                                         .goToContactUsPage()
                                         .submitHelpRequest(getHelpSubject(), getHelpMessage())
                                         .getAlertMessage(), getAlertMessage());
    }
}