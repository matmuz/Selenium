package data;

import com.github.javafaker.Faker;

import java.util.Random;

public class UserProvider {

    private static final UserProvider USER = new UserProvider();
    private static final UserProvider GUEST_USER = new UserProvider();
    private final String FIRSTNAME;
    private final String LASTNAME;
    private final String EMAIL;
    private final String PASSWORD;
    private final String ADDRESS;
    private final String CITY;
    private final String POSTAL_CODE;

    private UserProvider() {
        Faker faker = new Faker();
        FIRSTNAME = faker.name()
                .firstName();
        LASTNAME = faker.name()
                .lastName();
        EMAIL = FIRSTNAME + LASTNAME + "@gmail.com";
        PASSWORD = faker.phoneNumber()
                .cellPhone();
        ADDRESS = faker.address()
                .streetName();
        CITY = faker.address()
                .city();
        Random random = new Random();
        POSTAL_CODE = "" + (random.nextInt(89999) + 10000);
    }

    public static UserProvider getUser() {
        return USER;
    }

    public static UserProvider getGuestUser() {
        return GUEST_USER;
    }

    public String getFirstName() {
        return FIRSTNAME;
    }

    public String getLastName() {
        return LASTNAME;
    }

    public String getEmail() {
        return EMAIL;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public String getAddress() {
        return ADDRESS;
    }

    public String getCity() {
        return CITY;
    }

    public String getPostalCode() {
        StringBuilder stringBuilder = new StringBuilder(POSTAL_CODE);
        stringBuilder.insert(2, "-");
        return stringBuilder.toString();
    }
}
