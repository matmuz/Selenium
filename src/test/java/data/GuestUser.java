package data;

/**
 * Class responsible for guest user creation
 */
public final class GuestUser extends User {

    private static GuestUser guestUser;
    private final String address;
    private final String city;
    private final String postalCode;
    public final String country;

    /**
     * Guest User constructor, creates some data from User class and then adds needed details using faker
     * Private constructor - do not allow to create another instance
     */
    private GuestUser() {
        super();
        address = faker.address().streetName();
        city = faker.address().city();
        postalCode = "" + (random.nextInt(89999) + 10000);
        country = "Poland";
    }

    /**
     * Creates or gets Guest user (not registered)
     *
     * @return Guest User instance
     */
    public static GuestUser getUser() {
        if (guestUser == null) {
            guestUser = new GuestUser();
        }
        return guestUser;
    }

    /**
     * Gets Guest User's address
     *
     * @return Guest User's address as String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets Guest User's city
     *
     * @return Guest User's city as String
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets postal code as String by modifying random int from constructor
     *
     * @return Guest User's postal code as String
     */
    public String getPostalCode() {
        StringBuilder stringBuilder = new StringBuilder(postalCode);
        stringBuilder.insert(2, "-");
        return stringBuilder.toString();
    }

    /**
     * Gets Guest User's country
     *
     * @return Guest User's country as String
     */
    public String getCountry() {
        return country;
    }
}