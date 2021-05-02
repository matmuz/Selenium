package base;

import io.qameta.allure.Step;
import org.testng.Assert;

public class TestMethods {

    @Step("Check if text is the same")
    public static void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Check if values are the same")
    public static void assertEquals(int actual, int expected){
        Assert.assertEquals(actual, expected);
    }

    @Step("Check if values are the same")
    public static void assertEquals(double actual, double expected){
        Assert.assertEquals(actual, expected);
    }

    @Step("Check if text is different")
    public static void assertNotEquals(String actual, String expected){
        Assert.assertNotEquals(actual, expected);
    }

    @Step("Check if values are different")
    public static void assertNotEquals(double actual, double expected){
        Assert.assertNotEquals(actual, expected);
    }

    @Step("Check if values are different")
    public static void assertNotEquals(int actual, int expected){
        Assert.assertNotEquals(actual, expected);
    }
}
