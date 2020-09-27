package base;

public class DriverManagerFactory {

    public enum DriverType {
        CHROME
    }

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager = null;

        if (type == DriverType.CHROME) {
            driverManager = new ChromeDriverManager();
        }
        return driverManager;
    }
}