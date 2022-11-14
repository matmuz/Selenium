package assertions;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;

/**
 * A collection of methods that add @Step to Allure report when performing an assertion at the end of a test
 */
public final class CustomAssertions {

    /**
     * Private constructor - do not allow to create an instance
     */
    private CustomAssertions() {
    }

    @Step("Check if text is the same")
    public static void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Check if values are the same")
    public static void assertEquals(int actual, int expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Check if values are the same")
    public static void assertEquals(double actual, double expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Check if text is different")
    public static void assertNotEquals(String actual, String expected) {
        Assert.assertNotEquals(actual, expected);
    }

    @Step("Check if values are different")
    public static void assertNotEquals(double actual, double expected) {
        Assert.assertNotEquals(actual, expected);
    }

    @Step("Check if values are different")
    public static void assertNotEquals(int actual, int expected) {
        Assert.assertNotEquals(actual, expected);
    }

    @Step("Check if value is true")
    public static void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    @Step("Check if text contains a few values")
    public static void assertTextContains(String actual, List<String> toContain) {
        toContain.forEach(string -> Assert.assertTrue(actual.contains(string)));
    }
}
