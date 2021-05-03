package data;

import com.github.javafaker.Faker;

import java.util.Random;

public class UserProvider {

    private static final UserProvider USER = new UserProvider();
    private static final UserProvider GUEST_USER = new UserProvider();
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String address;
    private final String city;
    private final String postalCode;

    private UserProvider() {
        Faker faker = new Faker();
        firstName = faker.name()
                .firstName();
        lastName = faker.name()
                .lastName();
        email = firstName + lastName + "@gmail.com";
        password = faker.phoneNumber()
                .cellPhone();
        address = faker.address()
                .streetName();
        city = faker.address()
                .city();
        Random random = new Random();
        postalCode = "" + (random.nextInt(89999) + 10000);
    }

    public static UserProvider getUser() {
        return USER;
    }

    public static UserProvider getGuestUser() {
        return GUEST_USER;
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

    public String getPostalCode() {
        StringBuilder stringBuilder = new StringBuilder(postalCode);
        stringBuilder.insert(2, "-");
        return stringBuilder.toString();
    }
}
