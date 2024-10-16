package ui_tests.quick_actions_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.quick_actions.QuickActionsPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class QuickActionsTests {


    public LoginPage loginPage = new LoginPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public QuickActionsPage quickActionsPage = new QuickActionsPage();

    @Test
    void clickQuickActionsTest() throws InterruptedException {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        Assertions.assertEquals("Administrator", adminDashboardPage.getAdminText());
        quickActionsPage.selectOptionInQuickAction("Add user");
    }

}
