package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTests extends BaseTest {

//    @Test
//    public void createAccount() {
//
//        Assert.assertEquals(prestaShop.openPrestaShop()
//                                    .getTopMenuPage()
//                                    .goToSignInSection()
//                                    .createAccount(newUserData.getFirstName(), newUserData.getLastName(), newUserData.getEmail(), newUserData.getPassword())
//                                    .getTopMenuPage()
//                                    .getLoggedUsername(), newUserData.getFirstName() + " " + newUserData.getLastName());
//    }

    @Test
    public void logIn() {

        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
                                    .getTopMenuPage()
                                    .getLoggedUsername(), userData.getFirstName() + " " + userData.getLastName());
    }

    @Test
    public void logOut() {

        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
                                    .getTopMenuPage()
                                    .signOut()
                                    .getTopMenuPage()
                                    .getLoggedUsername(), "null");
    }

    @Test
    public void logInAsGuest() {

        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(guestData.getEmail(), guestData.getPassword())
                                    .getTopMenuPage()
                                    .getLoggedUsername(), "null");
    }

    @Test
    public void createAlreadyExitingAccount() {

        String usernameAfterCreateAccount = prestaShop.
                openPrestaShop()
                .getTopMenuPage()
                .goToSignInSection()
                .createAccount(userData.getFirstName(), userData.getLastName(), userData.getEmail(), userData.getPassword())
                .getTopMenuPage()
                .getLoggedUsername();
        if (usernameAfterCreateAccount.equals(userData.getFirstName() + " " + userData.getLastName())) {
            Assert.fail();
        }
    }
}