package data;

import com.github.javafaker.Faker;

import java.util.Random;

public class UserProvider {

    private static UserProvider userProvider = new UserProvider();
    private static UserProvider guestUserProvider = new UserProvider();
    private Faker faker = new Faker();
    private Random random = new Random();
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    private UserProvider() {
        this.firstName = faker.name()
                .firstName();
        this.lastName = faker.name()
                .lastName();
        this.email = firstName + lastName + "@gmail.com";
        this.password = faker.phoneNumber()
                .cellPhone();
        this.address = faker.address()
                .streetName();
        this.city = faker.address()
                .city();
        this.state = faker.address()
                .state();
        this.postalCode = "" + (random.nextInt(89999) + 10000);
        this.country = "United States";
    }

    public static UserProvider getUserProvider() {
        return userProvider;
    }

    public static UserProvider getGuestUserProvider() {
        return guestUserProvider;
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
}
