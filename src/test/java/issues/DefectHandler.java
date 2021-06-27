package issues;

import org.testng.SkipException;

import static io.restassured.RestAssured.get;
import static issues.Constants.BASE_URI_ISSUES;
import static issues.Constants.DEFECT_MESSAGE;

/**
 * Class responsible for defect handling.
 */

public class DefectHandler {

    private static boolean isOpen(Defects defect) {
        return get(BASE_URI_ISSUES + "/" + defect.getDefectId()).then()
                .extract()
                .response()
                .jsonPath()
                .getString("state")
                .equals("open");
    }

    public static void skipTestIfDefectIsOpen(Defects defect){
        if (isOpen(defect)) {
            throw new SkipException(DEFECT_MESSAGE);
        }
    }
}