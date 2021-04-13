package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestData {

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("customizable_product_name")
    private String customizableProductName;

    @JsonProperty("confirmation_message")
    private String confirmationMessage;

    @JsonProperty("help_response_message")
    private String helpResponseMessage;

    @JsonProperty("alert_message")
    private String alertMessage;

    @JsonProperty("help_message")
    private String helpMessage;

    @JsonProperty("help_subject")
    private String helpSubject;

    public static TestData get(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), TestData.class);
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