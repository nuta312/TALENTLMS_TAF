package ui_tests.auth_tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests  {
    public static AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public  static LoginPage loginPage = new LoginPage();
    public final String EXPECTED_WRONG_MESG = "Your username or password is incorrect. Please try again.";
    @BeforeAll
    public static void beforeMethod() throws InterruptedException {

        open(getAppConfig().base_url());
    }

    @Test
    void testChooseLeftSideBarElements() {
        adminDashboardPage.selectMenuItem("Groups");
    }

    @Test
    void testLoginPositive() {
        assertEquals("Administrator", adminDashboardPage.getAdminText());
    }

    @Test
    @DisplayName("Негативный тест: вход с невалидным паролем")
    void testLoginWithInvalidPassword() {
        String invalidPassword = "wrongPassword"; // задаем невалидный пароль
        loginPage.doLoginWithInvalidPassword(getCredentials().adminUsername(), invalidPassword);
        assertEquals( EXPECTED_WRONG_MESG,loginPage.isErrorMessageDisplayed());
    }

    @Test
    @DisplayName("Негативный тест: вход с невалидным логином")
    void testLoginWithInvalidUsername() {
        String invalidUsername = "wrongUsername"; // задаем невалидный логин
        loginPage.doLoginWithInvalidUsername(invalidUsername, getCredentials().adminPassword());
       assertEquals(EXPECTED_WRONG_MESG,loginPage.isErrorMessageDisplayed());
    }
}
