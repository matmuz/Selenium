package data;

/**
 * Class responsible for test user creation
 */
public final class TestUser extends User {

    private static TestUser testUser;

    /**
     * Test User constructor that simply uses super class to get random data
     * Private constructor - do not allow to create another instance
     */
    private TestUser() {
        super();
    }

    /**
     * Creates or gets Test user (not registered, little details)
     *
     * @return Test User instance
     */
    public static TestUser getUser() {
        if (testUser == null) {
            testUser = new TestUser();
        }
        return testUser;
    }
}