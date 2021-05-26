package data;

import com.github.javafaker.Faker;

import java.util.Random;

public abstract class User {

    static final Faker FAKER = new Faker();
    static final Random RANDOM = new Random();
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    protected User() {
        firstName = FAKER.name()
                .firstName();
        lastName = FAKER.name()
                .lastName();
        email = firstName + lastName + "@gmail.com";
        password = FAKER.phoneNumber()
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