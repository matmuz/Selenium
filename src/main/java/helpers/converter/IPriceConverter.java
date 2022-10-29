package helpers.converter;

/**
 * Interface that provides default implementation for converting price from WebElement text to double and is used
 * by a few Pages.
 */
public interface IPriceConverter {

    /**
     * Converts prices to numbers - deletes and modifies elements
     *
     * @param priceTextToConvert text to convert to price (product price text)
     * @return product price as a double
     */
    default double convertPriceTextToNumbers(String priceTextToConvert) {
        return Double.parseDouble(priceTextToConvert
                                          .replace(",", ".")
                                          .replace("$", ""));
    }
}