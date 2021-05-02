package data;

public class TestData {

    private static final TestData TEST_DATA = new TestData();
    private final String PRODUCT_NAME;
    private final String CUSTOMIZABLE_PRODUCT_NAME;
    private final String CONFIRMATION_MESSAGE;
    private final String HELP_RESPONSE_MESSAGE;
    private final String ALERT_MESSAGE;
    private final String HELP_MESSAGE;
    private final String HELP_SUBJECT;

    private TestData() {
        PRODUCT_NAME = "HUMMINGBIRD PRINTED T-SHIRT";
        CUSTOMIZABLE_PRODUCT_NAME = "CUSTOMIZABLE MUG";
        CONFIRMATION_MESSAGE = "\uE876TWOJE ZAMÓWIENIE ZOSTAŁO POTWIERDZONE";
        HELP_RESPONSE_MESSAGE = "Twoja wiadomość została pomyślnie wysłana do obsługi.";
        ALERT_MESSAGE = "Nieprawidłowy adres e-mail";
        HELP_MESSAGE = "Test message 123";
        HELP_SUBJECT = "Biuro Obsługi Klienta";
    }

    public static TestData getTestData() {
        return TEST_DATA;
    }

    public String getProductName() {
        return PRODUCT_NAME;
    }

    public String getCustomizableProductName() {
        return CUSTOMIZABLE_PRODUCT_NAME;
    }

    public String getConfirmationMessage() {
        return CONFIRMATION_MESSAGE;
    }

    public String getHelpResponseMessage() {
        return HELP_RESPONSE_MESSAGE;
    }

    public String getAlertMessage() {
        return ALERT_MESSAGE;
    }

    public String getHelpMessage() {
        return HELP_MESSAGE;
    }

    public String getHelpSubject() {
        return HELP_SUBJECT;
    }
}