package driver.manager;

/**
 * Driver factory class responsible for getting a particular driver manager
 */
public final class DriverManagerFactory {

    private static DriverManager driverManager;

    /**
     * Creates or returns a respective Driver Manager
     *
     * @param driverType driver type that is needed to determine the type of the manager
     * @return a particular driver manager instance
     */
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