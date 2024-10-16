package ui_tests.notifications_test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.helper.SelenideElementActions;
import ui.pages.auth.LoginPage;
import ui.pages.notifications.NotificationsPage;
import ui.pages.notifications.OverviewPage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static common.config_reader.ConfigurationManager.*;

public class NotificationsTest {
    public LoginPage loginPage = new LoginPage();
    public NotificationsPage notificationsPage = new NotificationsPage();
    public OverviewPage overviewPage = new OverviewPage();
    SelenideElementActions selenideElementActions = new SelenideElementActions();

    @Test
    void testNotifications()  {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        selenideElementActions.click(notificationsPage.notifications);
        selenideElementActions.click(notificationsPage.history);
        selenideElementActions.click(notificationsPage.pending);
        selenideElementActions.click(notificationsPage.systemNot);
        selenideElementActions.click(notificationsPage.overview);

        Assertions.assertEquals("Notifications", notificationsPage.getNotificationsText());
        Assertions.assertEquals("Overview", notificationsPage.getOverviewText());
        Assertions.assertEquals("History", notificationsPage.getHistoryText());
        Assertions.assertEquals("Pending", notificationsPage.getPendingText());
        Assertions.assertEquals("System notifications", notificationsPage.getSystemNotificationsText());
    }

    @Test
    void testOverview()  {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());

        overviewPage.notifications.click();
        overviewPage.addNotification.click();
        overviewPage.name.setValue("John");
        overviewPage.selectEvent.shouldBe(visible).click();

    }
}