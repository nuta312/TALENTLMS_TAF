package ui_tests.auth_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui_tests.BaseUiTest;

public class AdminTests extends BaseUiTest {

    @Test
    void testChooseLeftSideBarElements() {
        adminDashboardPage.selectMenuItem("Groups");
    }

    @Test
    void testLoginPositive() {

        Assertions.assertEquals("Administrator", adminDashboardPage.getAdminText());
    }
}
