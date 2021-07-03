package issues;

/**
 * A collection of defects in PrestaShop that are reported on GitHub.
 * The defects were found after running this project's tests and analyzing the results from a generated report.
 */

public enum Defects {

    DEFECT21(21),
    DEFECT22(22),
    DEFECT23(23),
    DEFECT24(24);

    private final int defectId;

    Defects(int defectId) {
        this.defectId = defectId;
    }

    public int getDefectId() {
        return defectId;
    }
}