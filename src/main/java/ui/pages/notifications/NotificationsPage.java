package ui.pages.notifications;

import com.codeborne.selenide.SelenideElement;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class NotificationsPage extends BasePage {

    public SelenideElement notifications = $x("//div[text()='Notifications']");
    public SelenideElement overview = $x("//div[text()='Overview']");
    public SelenideElement history = $x("//div[text()='History']");
    public SelenideElement pending = $x("//div[text()='Pending']");
    public SelenideElement systemNot = $x("//div[text()='System notifications']");
    public SelenideElement filterClick = $x("//div[@class='dropdown css-1dpepst']");
    public SelenideElement addNotification = $x("//div[text()='Add notification']");

    public String getNotificationsText() {
        return elementActions.getText(notifications);
    }

    public String getOverviewText() {
        return elementActions.getText(overview);
    }

    public String getHistoryText() {
        return elementActions.getText(history);
    }

    public String getPendingText() {
        return elementActions.getText(pending);
    }

    public String getSystemNotificationsText() {
        return elementActions.getText(systemNot);
    }
}
