package ui_tests.notifications_test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.pages.auth.LoginPage;
import ui.pages.notifications.History;
import ui.pages.notifications.NotificationsPage;
import ui.pages.notifications.OverviewPage;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static common.config_reader.ConfigurationManager.*;

public class NotificationsTest {
    public LoginPage loginPage = new LoginPage();
    public NotificationsPage notificationsPage = new NotificationsPage();
    public OverviewPage overviewPage = new OverviewPage();
    public History history = new History();

    @Test
    void testNotifications()  { // Notification

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        notificationsPage.notifications.shouldBe(enabled).click();
        notificationsPage.history.shouldBe(enabled).click();
        notificationsPage.pending.shouldBe(visible).click();
        notificationsPage.systemNot.shouldBe(visible).click();
        notificationsPage.overview.click();
        notificationsPage.filterClick2.click();

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