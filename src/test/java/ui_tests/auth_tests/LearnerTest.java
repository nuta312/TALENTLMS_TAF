package ui_tests.auth_tests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui_tests.BaseUiTest;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;
import static ui_tests.BaseUiTest.*;

public class LearnerTest  {
    @BeforeAll
    public static void beforeMethod() throws InterruptedException {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());

    }
    @Test
    @DisplayName("check learner text")
    void checkLearnerTest() {
        adminDashboardPage.moveToIkon();
        learnerDashboardPage.clickLearner();
        learnerDashboardPage.verifyLearnerText();
    }
}
