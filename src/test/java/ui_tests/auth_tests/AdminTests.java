package ui_tests.auth_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui_tests.BaseUiTest;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui_tests.BaseUiTest.adminDashboardPage;
import static ui_tests.BaseUiTest.loginPage;

public class AdminTests  {
    @BeforeAll
    public static void beforeMethod() throws InterruptedException {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());

    }
    @Test
    void testChooseLeftSideBarElements() {
        adminDashboardPage.selectMenuItem("Groups");
    }

    @Test
    void testLoginPositive() {

     assertEquals("Administrator", adminDashboardPage.getAdminText());
    }
}
