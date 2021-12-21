package utils.issues;

import org.testng.SkipException;

import static io.restassured.RestAssured.get;
import static utils.issues.Constants.ISSUES_PATH;
import static utils.issues.Constants.DEFECT_MESSAGE;

/**
 * Class responsible for defect handling.
 */
public final class DefectHandler {

    private DefectHandler() {
    }

    private static boolean isOpen(Defects defect) {
        return get(ISSUES_PATH + "/" + defect.getDefectId()).then()
                                                            .extract()
                                                            .response()
                                                            .jsonPath()
                                                            .getString("state")
                                                            .equals("open");
    }

    public static void skipTestIfDefectIsOpen(Defects defect) {
        if (isOpen(defect)) {
            throw new SkipException(DEFECT_MESSAGE);
        }
    }
}