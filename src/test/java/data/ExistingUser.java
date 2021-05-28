package data;

public class ExistingUser {

    public static final String FIRSTNAME = "Automated";
    public static final  String LASTNAME = "Test";
    public static final  String EMAIL = "QA_Automated@gmail.com";
    public static final  String PASSWORD = "QA_Automated";
    public static final  String ADDRESS = "Testowa 123";
    public static final  String POSTAL_CODE = "12-345";
    public static final  String CITY = "Warszawa";

    private ExistingUser () {
    }

    public static String getFullName() {
        return FIRSTNAME + " " + LASTNAME;
    }
}