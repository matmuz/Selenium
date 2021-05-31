package data;

/**
 * Class responsible for test user creation
 */

public class TestUser extends User {

    private static final TestUser testUser = new TestUser();

    private TestUser() {
        super();
    }

    public static TestUser getUser() {
        return testUser;
    }
}