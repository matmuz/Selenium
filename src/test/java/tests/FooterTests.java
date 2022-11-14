package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static assertions.CustomAssertions.assertTrue;

public final class FooterTests extends BaseTest {

    @Test
    public void shouldDisplayFooterFromLandingPage() {
        assertTrue(prestaShop.openPrestaShop().getFooterPage().isFooterDisplayed());
    }
}
