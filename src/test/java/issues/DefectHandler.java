package issues;

import org.testng.SkipException;

import static io.restassured.RestAssured.get;

/**
 * Class responsible for defect handling.
 */
public final class DefectHandler {

    /**
     * Private constructor - do not allow to create an instance
     */
    private DefectHandler() {
    }

    /**
     * Checks if a GitHub defect is open
     *
     * @param defect GitHub defect enum that exists in repository in Defects
     * @return true if defect is open, false otherwise
     */
    private static boolean isOpen(Defects defect) {
        return get(Constants.ISSUES_PATH + "/" + defect.getDefectId()).then()
                                                                      .extract()
                                                                      .response()
                                                                      .jsonPath()
                                                                      .getString("state")
                                                                      .equals("open");
    }

    /**
     * Skips test if called within test if defect is open and prints a message
     *
     * @param defect GitHub defect enum that exists in repository in Defects
     */
    public static void skipTestIfDefectIsOpen(Defects defect) {
        if (isOpen(defect)) {
            throw new SkipException(Constants.DEFECT_MESSAGE);
        }
    }
}