package driver.manager;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;

import java.io.File;

/**
 * Concrete class that is responsible for managing EdgeDriver's cycle of life
 */
public final class EdgeDriverManager extends DriverManager {

    private EdgeDriverService edgeDriverService;

    @Override
    public void startService() {
        if (edgeDriverService == null) {
            try {
                edgeDriverService = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/drivers/msedgedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                edgeDriverService.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (edgeDriverService != null && edgeDriverService.isRunning())
            edgeDriverService.stop();
    }

    @Override
    public void createDriver() {
        driver = new EdgeDriver(edgeDriverService);
        driver.manage().window().maximize();
    }
}
