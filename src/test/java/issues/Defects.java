package issues;

/**
 * A collection of defects in PrestaShop. The defects are reported on GitHub.
 * The defects were found after running this project's tests and analyzing the results from a generated report.
 */

public enum Defects {

    DEFECT_1(21),
    DEFECT_2(22),
    DEFECT_3(23),
    DEFECT_4(24);

    private final int defectId;

    Defects(int defectId) {
        this.defectId = defectId;
    }

    public int getDefectId() {
        return defectId;
    }
}