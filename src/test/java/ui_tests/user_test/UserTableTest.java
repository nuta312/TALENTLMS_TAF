package ui_tests.user_test;

import com.codeborne.selenide.Selenide;
import common.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.user.UserPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class UserTableTest {
    public UserPage userPage = new UserPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public LoginPage loginPage = new LoginPage();

    @BeforeEach
    void setUp(){
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
    }

    @Test
    public void userTableTest(){
        adminDashboardPage.selectMenuItem("Users");
        Selenide.sleep(5000);
        List<User> allUsers = userPage.getUserFromTable();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }
}
