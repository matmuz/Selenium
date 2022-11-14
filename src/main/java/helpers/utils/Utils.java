package helpers.utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class Utils {

    /**
     * Converts prices to numbers - deletes and modifies elements
     *
     * @param priceTextToConvert text to convert to price (product price text)
     * @return product price as a double
     */
    public static double convertPriceTextToNumbers(String priceTextToConvert) {
        return Double.parseDouble(priceTextToConvert.replace(",", ".")
                                                    .replace("$", ""));
    }

    public static int getRandomElementFromList(List<WebElement> webElements) {
        return new Random().nextInt(webElements.size());
    }

    public static WebElement getWebElementFromListByName(List<WebElement> webElements, String name) {
        return webElements.stream()
                          .filter(WebElement -> WebElement.getText().toUpperCase().contains(name.toUpperCase()))
                          .collect(Collectors.toList())
                          .get(0);
    }
}
