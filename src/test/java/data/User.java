package data;

import com.github.javafaker.Faker;

import java.util.Random;

/**
 * Abstract User class that provides the core for subclasses
 */
public abstract class User {

    protected static final Faker faker = new Faker();
    protected static final Random random = new Random();
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    /**
     * User constructor creates base for other user classes (subclasses) by assigning random data to fields
     */
    protected User() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = firstName + lastName + "@gmail.com";
        password = faker.phoneNumber().cellPhone();
    }

    /**
     * Gets first name
     *
     * @return first name as String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name
     *
     * @return last name as String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets email
     *
     * @return email as String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password
     *
     * @return password as String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets full name by combining first and last name with space added between
     *
     * @return full name as String
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}