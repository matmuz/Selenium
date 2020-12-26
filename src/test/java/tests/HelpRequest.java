package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class HelpRequest extends BaseTest {

    @Test(priority = 1)
    public void sendContactMessageAsGuest() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToContactUsPage()
                                  .submitHelpRequest(testData.getHelpSubject(), guestUser.getEmail(), testData.getHelpMessage())
                                  .getResponseMessage(), testData.getHelpResponseMessage());
    }

    @Test(priority = 2)
    public void sendContactMessageAsLoggedUser() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToSignInSection()
                                  .logIn(testUser.getEmail(), testUser.getPassword())
                                  .getTopMenuPage()
                                  .goToContactUsPage()
                                  .submitHelpRequest(testData.getHelpSubject(), testData.getHelpMessage())
                                  .getResponseMessage(), testData.getHelpResponseMessage());
    }

    @Test(priority = 3)
    public void sendContactMessageNegativeCase() {

        test.assertEquals(prestaShop.openPrestaShop()
                                  .getTopMenuPage()
                                  .goToContactUsPage()
                                  .submitHelpRequest(testData.getHelpSubject(), testData.getHelpMessage())
                                  .getAlertMessage(), testData.getAlertMessage());
    }
}