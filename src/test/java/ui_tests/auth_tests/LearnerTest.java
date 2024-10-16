package ui_tests.auth_tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui_tests.BaseUiTest;

public class LearnerTest extends BaseUiTest {

    @Test
    @DisplayName("check learner text")
    void checkLearnerTest() {
        adminDashboardPage.moveToIkon();
        learnerDashboardPage.clickLearner();
        learnerDashboardPage.verifyLearnerText();
    }
}
