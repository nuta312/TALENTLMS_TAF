package ui_tests.learnerdashboard_test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.learner.CheckLearnerMenuItems;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.user.UserTableActions;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class CheckLearnerMenuItemsTest {
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public LoginPage loginPage = new LoginPage();
    public UserTableActions userTableActions = new UserTableActions();
    public CheckLearnerMenuItems checkLearnerMenu = new CheckLearnerMenuItems();

    @BeforeEach
    void setUp() {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
    }

    @Test
    void testChooseLeftSideBarElements() {
        adminDashboardPage.selectMenuItem("Users");
        userTableActions.selectLoginButton();
        checkLearnerMenu.getLearnerText();
        List<SelenideElement> elementsToCheck = checkLearnerMenu.getMenuElements();
        for (SelenideElement element : elementsToCheck) {
            element.shouldNotBe(Condition.exist);
        }
    }
}
