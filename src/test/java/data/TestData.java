package data;

public class TestData {

    private static final TestData TEST_DATA = new TestData();
    private final String productName;
    private final String customizableProductName;
    private final String confirmationMessage;
    private final String helpResponseMessage;
    private final String alertMessage;
    private final String helpMessage;
    private final String helpSubject;

    private TestData() {
        productName = "HUMMINGBIRD PRINTED T-SHIRT";
        customizableProductName = "CUSTOMIZABLE MUG";
        confirmationMessage = "\uE876TWOJE ZAMÓWIENIE ZOSTAŁO POTWIERDZONE";
        helpResponseMessage = "Twoja wiadomość została pomyślnie wysłana do obsługi.";
        alertMessage = "Nieprawidłowy adres e-mail";
        helpMessage = "Test message 123";
        helpSubject = "Biuro Obsługi Klienta";
    }

    public static TestData getTestData() {
        return TEST_DATA;
    }

    public String getProductName() {
        return productName;
    }

    public String getCustomizableProductName() {
        return customizableProductName;
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public String getHelpResponseMessage() {
        return helpResponseMessage;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public String getHelpSubject() {
        return helpSubject;
    }
}