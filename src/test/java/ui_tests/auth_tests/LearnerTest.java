package ui_tests.auth_tests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.lerner_dashboard.LearnerDashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class LearnerTest  {
    public  static AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public static LearnerDashboardPage learnerDashboardPage = new LearnerDashboardPage();
    public  static LoginPage loginPage = new LoginPage();
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
