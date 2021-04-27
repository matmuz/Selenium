package driver;

public class DriverManagerFactory {

    private static DriverManager driverManager;

    public static DriverManager getManager(String driverType) {
        if (driverManager == null) {
            if (driverType.equals("chrome")) {
                driverManager = new ChromeDriverManager();
                return driverManager;
            } else if (driverType.equals("edge")) {
                driverManager = new EdgeDriverManager();
                return driverManager;
            }
        }
        return driverManager;
    }
}