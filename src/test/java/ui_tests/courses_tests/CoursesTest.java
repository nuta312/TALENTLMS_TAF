package ui_tests.courses_tests;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.courses.CoursesPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class CoursesTest {

    public LoginPage loginPage = new LoginPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    CoursesPage coursesPage = new CoursesPage();

    @BeforeEach
    void testLoginPositive() {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        Assertions.assertEquals("Administrator", adminDashboardPage.getAdminText());
    }

    @Test
    public void createTheCourseTest() throws InterruptedException {
        coursesPage.selectCoursesOptionAndAddCourse();
        coursesPage.createCourse();
    }
}