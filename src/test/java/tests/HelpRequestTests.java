package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelpRequestTests extends BaseTest {

    @Test
    public void sendContactMessageAsGuest() {

        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToContactUsPage()
                                    .submitHelpRequest(testData.getHelpSubject(), guestData.getEmail(), testData.getHelpMessage())
                                    .getResponseMessage(), testData.getHelpResponseMessage());
    }

    @Test
    public void sendContactMessageAsLoggedUser() {

        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
                                    .getTopMenuPage()
                                    .goToContactUsPage()
                                    .submitHelpRequest(testData.getHelpSubject(), testData.getHelpMessage())
                                    .getResponseMessage(), testData.getHelpResponseMessage());
    }
}