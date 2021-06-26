package issues;

import static io.restassured.RestAssured.get;
import static issues.Constants.BASE_URI_ISSUES;

/**
 * Class responsible for defect handling. Checks statuses via GitHub API using RestAssured library.
 */

public class DefectChecker {

    public static boolean isOpen(Defects defect) {
        return get(BASE_URI_ISSUES + "/" + defect.getDefectId()).then()
                .extract()
                .response()
                .jsonPath()
                .getString("state")
                .equals("open");
    }
}