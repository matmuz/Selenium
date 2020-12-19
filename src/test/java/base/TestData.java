package base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestData {

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("confirmation_message")
    private String confirmationMessage;

    @JsonProperty("help_response_message")
    private String helpResponseMessage;

    @JsonProperty("help_message")
    private String helpMessage;

    @JsonProperty("help_subject")
    private String helpSubject;

    public static TestData get(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), TestData.class);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getProductName() {
        return productName;
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public String getHelpResponseMessage() {
        return helpResponseMessage;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public String getHelpSubject() {
        return helpSubject;
    }
}