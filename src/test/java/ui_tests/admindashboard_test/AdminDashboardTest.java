package ui_tests.admindashboard_test;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class AdminDashboardTest {
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public LoginPage loginPage = new LoginPage();

    @BeforeEach
    void setUp(){
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
    }

    @Test
    @Owner("Akim")
    void testChooseLeftSideBarElements(){
        adminDashboardPage.selectMenuItem("Groups");
    }
}
