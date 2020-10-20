package base;

public class DriverManagerFactory {

    public enum DriverType {
        CHROME,
        EDGE
    }

    private static DriverManager driverManager;

    public static DriverManager getManager(DriverType type) {
        if (driverManager == null) {
            if (type == DriverType.CHROME) {
                driverManager = new ChromeDriverManager();
                return driverManager;
            } else if (type == DriverType.EDGE) {
                driverManager = new EdgeDriverManager();
                return driverManager;
            }
        }
        return driverManager;
    }
}