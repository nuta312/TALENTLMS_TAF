package ui_tests.auth_tests;


import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.instructor_dashboard.InstructorDashboardPage;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class LoginTests {
    public static AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public static LoginPage loginPage = new LoginPage();
    public static InstructorDashboardPage instructorDashboardPage = new InstructorDashboardPage();

    @BeforeAll
    public static void beforeMethod() {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());

    }
    @Test
    void testChooseLeftSideBarElements(){
        adminDashboardPage.selectMenuItem("Groups");
    }

    @Test
    void testLoginPositive() {

        Assertions.assertEquals("Administrator", adminDashboardPage.getAdminText());
    }

    @Test
    void checkInstructorTest() {
        adminDashboardPage.moveToIkon();
        instructorDashboardPage.clickInstructor();
        instructorDashboardPage.verifyInstructorText();
    }
}
