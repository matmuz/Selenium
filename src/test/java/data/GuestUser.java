package data;

public class GuestUser extends User {

    private static final GuestUser GUEST_USER = new GuestUser();
    private final String address;
    private final String city;
    private final String postalCode;

    private GuestUser(){
        super();
        address = FAKER.address()
                .streetName();
        city = FAKER.address()
                .city();
        postalCode = "" + (RANDOM.nextInt(89999) + 10000);
    }

    public static GuestUser getUser() {
        return GUEST_USER;
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