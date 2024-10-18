package ui_tests.notifications_test;
import com.codeborne.selenide.Selenide;
import common.entities.notifications.History;
import common.entities.notifications.Overview;
import common.entities.notifications.SystemNotifications;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationsTest extends BaseNotificationTest{

    @Test()
    void testNotifications() {
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
        selenideElementActions.clickElementWithJsExecutor(overviewPage.addNotification);
        selenideElementActions.input(overviewPage.name, "John");
        selenideElementActions.click(overviewPage.selectEvent);
        overviewPage.selectEvent.click();
        overviewPage.selectEvent.setValue("On user create").pressEnter();
    }

    @Test
    void testOverviewTable() {
        selenideElementActions.click(overviewPage.notifications);
        Selenide.sleep(3000);
        List<Overview> allTable = overviewPage.getOversNotificationsTable();
        IntStream.range(0, allTable.size())
                .forEach(i -> System.out.printf("%d. %s%n", i + 1, allTable.get(i)));
    }

    @Test()
    void testHistoryTable() {
        selenideElementActions.click(notificationsPage.history);
        List<History> allHistoryTable = historyPage.getHistoryTable();
        Selenide.sleep(4000);

        if (allHistoryTable.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            IntStream.range(0, allHistoryTable.size())
                    .forEach(i -> System.out.printf("%d. %s%n", i + 1, allHistoryTable.get(i)));
        }
    }

    @Test
    void testSystemNotifications() {
        selenideElementActions.click(notificationsPage.systemNot);
        Selenide.sleep(3000);
        List<SystemNotifications> systemList = systemPage.getSystemTable();
        IntStream.range(0, systemList.size())
                .forEach(i -> System.out.printf("%d. %s%n", i + 1, systemList.get(i)));

        assertEquals("Reset password", systemPage.getResetPassword());
        assertEquals("Create password", systemPage.getCreatePassword());
        assertEquals("Account confirmation", systemPage.getAccountConfirmation());
        assertEquals("Account activation", systemPage.getAccountActivation());
        assertEquals("Export reports in Excel", systemPage.getExportExcel());
        assertEquals("Export reports in Excel", systemPage.getExportExcel());
        assertEquals("Import data", systemPage.getImportData());
        assertEquals("Reply to discussion", systemPage.getReplyToDiscussion());

    }
}

