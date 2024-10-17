package ui_tests.notifications_test;
import org.junit.jupiter.api.Test;
import ui.helper.SelenideElementActions;
import ui.pages.auth.LoginPage;
import ui.pages.notifications.NotificationsPage;
import ui.pages.notifications.OverviewPage;
import static com.codeborne.selenide.Selenide.*;
import static common.config_reader.ConfigurationManager.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationsTest {
    public LoginPage loginPage = new LoginPage();
    public NotificationsPage notificationsPage = new NotificationsPage();
    public OverviewPage overviewPage = new OverviewPage();
    SelenideElementActions selenideElementActions = new SelenideElementActions();

    @Test
    void testNotifications() {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        selenideElementActions.click(notificationsPage.notifications);
        selenideElementActions.click(notificationsPage.history);
        selenideElementActions.click(notificationsPage.pending);
        selenideElementActions.click(notificationsPage.systemNot);
        selenideElementActions.click(notificationsPage.overview);

        assertEquals("Notifications", notificationsPage.getNotificationsText());
        assertEquals("Overview", notificationsPage.getOverviewText());
        assertEquals("History", notificationsPage.getHistoryText());
        assertEquals("Pending", notificationsPage.getPendingText());
        assertEquals("System notifications", notificationsPage.getSystemNotificationsText());
    }

    @Test
    void testOverview() {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        selenideElementActions.click(overviewPage.notifications);
        selenideElementActions.clickElementWithJsExecutor(overviewPage.addNotification);
        selenideElementActions.input(overviewPage.name, "John");
        selenideElementActions.click(overviewPage.selectEvent);
        overviewPage.selectEvent.click();
        overviewPage.selectEvent.setValue("On user create").pressEnter();
    }
}
