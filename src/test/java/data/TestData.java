package data;

public class TestData {

    private String productName;
    private String customizableProductName;
    private String confirmationMessage;
    private String helpResponseMessage;
    private String alertMessage;
    private String helpMessage;
    private String helpSubject;

    public TestData() {
        productName = "HUMMINGBIRD PRINTED T-SHIRT";
        customizableProductName = "CUSTOMIZABLE MUG";
        confirmationMessage = "\uE876TWOJE ZAMÓWIENIE ZOSTAŁO POTWIERDZONE";
        helpResponseMessage = "Twoja wiadomość została pomyślnie wysłana do obsługi.";
        alertMessage = "Nieprawidłowy adres e-mail";
        helpMessage = "Test message 123";
        helpSubject = "Biuro Obsługi Klienta";
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