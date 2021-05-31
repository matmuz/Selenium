package data;

import com.github.javafaker.Faker;

import java.util.Random;

/**
 * Abstract User class that determines the core for subclasses
 */

public abstract class User {

    protected static final Faker faker = new Faker();
    protected static final Random random = new Random();
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    protected User() {
        firstName = faker.name()
                .firstName();
        lastName = faker.name()
                .lastName();
        email = firstName + lastName + "@gmail.com";
        password = faker.phoneNumber()
                .cellPhone();
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

    public String getFullName() {
        return firstName + " " + lastName;
    }
}