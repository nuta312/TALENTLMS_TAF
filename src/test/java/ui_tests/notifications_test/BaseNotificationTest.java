package ui_tests.notifications_test;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import ui.helper.SelenideElementActions;
import ui.pages.auth.LoginPage;
import ui.pages.notifications.HistoryPage;
import ui.pages.notifications.NotificationsPage;
import ui.pages.notifications.OverviewPage;
import ui.pages.notifications.SystemPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class BaseNotificationTest {
    public LoginPage loginPage = new LoginPage();
    public NotificationsPage notificationsPage = new NotificationsPage();
    public OverviewPage overviewPage = new OverviewPage();
    public HistoryPage historyPage = new HistoryPage();
    public SystemPage systemPage = new SystemPage();
    SelenideElementActions selenideElementActions = new SelenideElementActions();

    @BeforeEach
    void setUp(){
        open(getAppConfig().base_url());
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        selenideElementActions.click(overviewPage.notifications);
    }
}
