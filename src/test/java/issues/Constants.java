package issues;

/**
 * Class responsible for providing constants regarding defects.
 */
public final class Constants {

    public static final String ISSUES_PATH = "https://api.github.com/repos/matmuz/Automation/issues";
    public static final String DEFECT_MESSAGE = "Defect regarding tested functionality in this test was reported and is open. This test will not be run.";

    /**
     * Private constructor - do not allow to create an instance
     */
    private Constants() {
    }
}