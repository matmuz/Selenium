package issues;

import static io.restassured.RestAssured.given;

/**
 * Class responsible for defect handling. Checks statuses via GitHub API using RestAssured library.
 */

public class DefectChecker {

    private static final String BASE_URI_ISSUES = "https://api.github.com/repos/matmuz/Automation/issues";
    public static final String DEFECT_MESSAGE = "Defect regarding tested functionality in this test was reported and is open. This test will not be run.";

    private static String checkDefectStatus(int defectId) {
        return given().when()
                .get(BASE_URI_ISSUES + "/" + defectId)
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("state");
    }

    public static boolean isOpen(Defects defect) {
        return checkDefectStatus(defect.getDefectId()).equals("open");
    }
}