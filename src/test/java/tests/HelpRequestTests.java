package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelpRequestTests extends BaseTest {

    @Test
    public void sendContactMessageAsGuest() {

        String expectedMessage = testData.getHelpResponseMessage();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToContactUsPage()
                                    .submitHelpRequest(testData.getHelpSubject(), guestData.getEmail(), testData.getHelpMessage())
                                    .getResponseMessage(), expectedMessage);
    }

    @Test
    public void sendContactMessageAsLoggedUser() {

        String expectedMessage = testData.getHelpResponseMessage();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
                                    .getTopMenuPage()
                                    .goToContactUsPage()
                                    .submitHelpRequest(testData.getHelpSubject(), testData.getHelpMessage())
                                    .getResponseMessage(), expectedMessage);
    }
}