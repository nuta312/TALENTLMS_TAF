package ui_tests.user_test;

import com.codeborne.selenide.Selenide;
import common.entities.User;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.info.InfoPage;
import ui.pages.user.UserPage;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;
import static io.qameta.allure.Allure.step;

public class UserTableTest {
    public UserPage userPage = new UserPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public LoginPage loginPage = new LoginPage();
    public InfoPage infoPage = new InfoPage();

    @BeforeEach
    void setUp(){
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
    }

    @Test
    @Owner("Akim")
    public void userTableTest(){
        adminDashboardPage.selectMenuItem("Users");
        Selenide.sleep(5000);
        List<User> allUsers = userPage.getUserFromTable();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }

    @Test
    @Owner("Akim")
    public void userEditAndSaveTest(){
        adminDashboardPage.selectMenuItem("Users");
        Selenide.sleep(5000);
        userPage.updateUser("L. Bechtelar", "Edit", 3);
        infoPage.editUserAndSave();
        step("Verify that user is edit successfully", () ->
                Assertions.assertEquals("Changes saved successfully", userPage.getChangeUserText())
        );
        Selenide.sleep(5000);
    }

    @Test
    @Owner("Akim")
    public void userEditAndCancelTest(){
        adminDashboardPage.selectMenuItem("Users");
        Selenide.sleep(5000);
        userPage.updateUser("E. Schiller", "Edit", 1);
        infoPage.editUserAndCancel();
        step("Verify that user edit was canceled successfully", () ->
                Assertions.assertEquals("Edit user", userPage.getAfterClickCancelText())
        );
    }

    @Test
    @Owner("Akim")
    public void deleteUserTest(){
        adminDashboardPage.selectMenuItem("Users");
        Selenide.sleep(5000);
        userPage.deleteUser("e. gergerg", "Delete", 2);
        step("Verify that user is delete get text", () ->
                Assertions.assertEquals("Delete user", userPage.getDeleteText())
        );
    }

    @Test
    @Owner("Akim")
    public void loginAsUserTest(){
        adminDashboardPage.selectMenuItem("Users");
        Selenide.sleep(5000);
        userPage.deleteUser("L. Volkman", "Login as", 3);
        step("Verify that user is login as successfully", () ->
                Assertions.assertEquals("Recent course activity", userPage.getLoginAsText())
        );
    }
}
