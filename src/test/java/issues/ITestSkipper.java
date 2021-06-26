package issues;

import org.testng.SkipException;

import static issues.Constants.DEFECT_MESSAGE;

/**
 * Interface that provides default implementation of the method that skips test if the related defect is open.
 * Meant to be implemented and used by test classes.
 */

public interface ITestSkipper {

    default void skipTestIfDefectIsOpen(Defects defect){
        if (DefectChecker.isOpen(defect)) {
            throw new SkipException(DEFECT_MESSAGE);
        }
    }
}