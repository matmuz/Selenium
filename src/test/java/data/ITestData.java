package data;

public interface ITestData {

    String CHECK_MARK = "";
    String PRODUCT_NAME = "HUMMINGBIRD PRINTED T-SHIRT";
    String CUSTOMIZABLE_PRODUCT_NAME = "CUSTOMIZABLE MUG";
    String CONFIRMATION_MESSAGE = CHECK_MARK + "TWOJE ZAMÓWIENIE ZOSTAŁO POTWIERDZONE";
    String HELP_RESPONSE_MESSAGE = "Twoja wiadomość została pomyślnie wysłana do obsługi.";
    String ALERT_MESSAGE = "Nieprawidłowy adres e-mail";
    String HELP_MESSAGE = "Test message 123";
    String HELP_SUBJECT = "Biuro Obsługi Klienta";

    default String getProductName() {
        return PRODUCT_NAME;
    }

    default String getCustomizableProductName() {
        return CUSTOMIZABLE_PRODUCT_NAME;
    }

    default String getConfirmationMessage() {
        return CONFIRMATION_MESSAGE;
    }

    default String getHelpResponseMessage() {
        return HELP_RESPONSE_MESSAGE;
    }

    default String getAlertMessage() {
        return ALERT_MESSAGE;
    }

    default String getHelpMessage() {
        return HELP_MESSAGE;
    }

    default String getHelpSubject() {
        return HELP_SUBJECT;
    }
}
