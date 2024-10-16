package ui_tests.auth_tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui_tests.BaseUiTest;



public class InstructorTest extends BaseUiTest {

    @Test
    @DisplayName("check instructor text")
    void checkInstructorTest() {
        adminDashboardPage.moveToIkon();
        instructorDashboardPage.clickInstructor();
        instructorDashboardPage.verifyInstructorText();
    }

}
