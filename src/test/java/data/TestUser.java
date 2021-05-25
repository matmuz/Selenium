package data;

public class TestUser extends User {

    private static final TestUser TEST_USER = new TestUser();

    private TestUser() {
        super();
    }

    public static TestUser getUser() {
        return TEST_USER;
    }
}