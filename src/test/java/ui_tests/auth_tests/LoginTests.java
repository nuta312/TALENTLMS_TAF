package ui_tests.auth_tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;
import static io.qameta.allure.Allure.step;


public class LoginTests {

    public LoginPage loginPage = new LoginPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();

    @Test
    void testLoginPositive() {
        step("Open login page", () ->
                open(getAppConfig().base_url())
        );
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        step("Verify that user is on Admin Dashboard", () ->
                Assertions.assertEquals("Administrator", adminDashboardPage.getAdminText())
        );
    }
}
