package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTests extends BaseTest {

    @Test(priority = 1)
    public void createAccount() {

        String createdUsername = newUserData.getFirstName() + " " + newUserData.getLastName();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .createAccount(newUserData.getFirstName(), newUserData.getLastName(), newUserData.getEmail(), newUserData.getPassword())
                                    .getTopMenuPage()
                                    .getLoggedUsername(), createdUsername);
    }

    @Test(priority = 2)
    public void logIn() {

        String username = userData.getFirstName() + " " + userData.getLastName();
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(userData.getEmail(), userData.getPassword())
                                    .getTopMenuPage()
                                    .getLoggedUsername(), username);
    }

    @Test(priority = 3)
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

    @Test(priority = 4)
    public void logInAsGuest() {

        String noUsername = "null";
        Assert.assertEquals(prestaShop.openPrestaShop()
                                    .getTopMenuPage()
                                    .goToSignInSection()
                                    .logIn(guestData.getEmail(), guestData.getPassword())
                                    .getTopMenuPage()
                                    .getLoggedUsername(), noUsername);
    }

    @Test(priority = 5)
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