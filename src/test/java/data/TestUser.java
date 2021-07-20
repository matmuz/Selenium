package data;

/**
 * Class responsible for test user creation
 */
public final class TestUser extends User {

    private static TestUser testUser;

    private TestUser() {
        super();
    }

    public static TestUser getUser() {
        if (testUser == null) {
            testUser = new TestUser();
        }
        return testUser;
    }
}