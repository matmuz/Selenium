import org.testng.Assert;
import org.testng.annotations.Test;


public class TestSuite extends BaseTest {

    @Test
    public void sendHelpRequest() {

        String expectedMessage = "Your message has been successfully sent to our team.";
        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToContactUsPage();
        contactUsPage = new ContactUsPage(driver);
        contactUsPage.submitHelpRequest("Webmaster", "test@test.com", "test message");
        String actualMessage = contactUsPage.getResponseMessage();
        Assert.assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void popularProducts() {

        int popularProductsCount = 8;
        homePage = new HomePage(driver);
        int actualCount = homePage.popularProducts.size();
        Assert.assertEquals(popularProductsCount, actualCount);

        String popularProductToFind = "good day poster";
        homePage.enterPopularProduct(popularProductToFind);
        productModel = new ProductModel(driver);
        Assert.assertEquals(popularProductToFind, productModel.getProductName().toLowerCase());

    }
}
