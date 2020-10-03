package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTests extends BaseTest {

//    @Test
//    public void createAccount() {
//
//        String createdUsername = newUserData.getFirstName() + " " + newUserData.getLastName();
//        Assert.assertEquals(prestaShop.openPrestaShop()
//                                    .getTopMenuPage()
//                                    .goToSignInSection()
//                                    .createAccount(newUserData.getFirstName(), newUserData.getLastName(), newUserData.getEmail(), newUserData.getPassword())
//                                    .getTopMenuPage()
//                                    .getLoggedUsername(), createdUsername);
//    }

    @Test
    public void logIn() {

        String username = userData.getFirstName() + " " + userData.getLastName();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
                                    .getTopMenuPage()
                                    .getLoggedUsername(), username);
    }

    @Test
    public void logOut() {

        String noUsername = "null";
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
                                    .getTopMenuPage()
                                    .signOut()
                                    .getTopMenuPage()
                                    .getLoggedUsername(), noUsername);
    }

    @Test
    public void logInAsGuest() {

        String noUsername = "null";
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(guestData.getEmail(), guestData.getPassword())
                                    .getTopMenuPage()
                                    .getLoggedUsername(), noUsername);
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