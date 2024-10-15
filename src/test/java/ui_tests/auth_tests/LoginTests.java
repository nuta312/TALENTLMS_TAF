package ui_tests.auth_tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class LoginTests   {
    public LoginPage loginPage = new LoginPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();


    @BeforeEach
    void setUp(){
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
    }

    @Test
    void testLoginPositive()  {
        Assertions.assertEquals("Administrator",adminDashboardPage.getAdminText());
    }

    @Test
    void testChooseLeftSideBarElements(){
        adminDashboardPage.selectMenuItem("Groups");
    }
}
