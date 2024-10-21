package ui_tests.users_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.users.UsersPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class UsersTest {
    public LoginPage loginPage = new LoginPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    UsersPage usersPage = new UsersPage();
    String file = "Изображение WhatsApp 2024-05-09 в 19.25.37_802a4977.jpg";

    @BeforeEach
    void testLoginPositive() {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        Assertions.assertEquals("Administrator", adminDashboardPage.getAdminText());
    }

    @Test
    public void enrollUserToCourseTest() throws InterruptedException {
        usersPage.selectUser();
        usersPage.enrollUserToCourse();
        usersPage.loginActions();
        usersPage.getReports();
        Assertions.assertEquals(file, usersPage.getFileText());
        Assertions.assertEquals("Completed", usersPage.getCourseStatusText());
    }
}