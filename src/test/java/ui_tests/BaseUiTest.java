package ui_tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.instructor_dashboard.InstructorDashboardPage;
import ui.pages.lerner_dashboard.LearnerDashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class BaseUiTest {
    public static AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public static LoginPage loginPage = new LoginPage();
    public static LearnerDashboardPage learnerDashboardPage = new LearnerDashboardPage();
    public  static InstructorDashboardPage instructorDashboardPage = new InstructorDashboardPage();

    @BeforeAll
    public static void beforeMethod() {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());

    }


}
