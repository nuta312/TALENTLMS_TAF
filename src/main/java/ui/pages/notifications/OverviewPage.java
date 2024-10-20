package ui.pages.notifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.notifications.Overview;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class OverviewPage extends BasePage {

    public SelenideElement notifications = $x("//div[text()='Notifications']");
    public SelenideElement addNotification = $x("//span[normalize-space()='Add notification']");
    public SelenideElement name = $(By.id("name"));
    public SelenideElement selectEvent = $(By.id("react-select-2-input"));
    public ElementsCollection overviewTable = $$("tr.link");

    public ArrayList<Overview> getOversNotificationsTable() {
        ArrayList<Overview> overviewList = new ArrayList<>();
        for (SelenideElement row : overviewTable) {
            ElementsCollection oversCells = row.$$("[data-testid='name-cell'], " +
                            "[data-testid='event_name-cell'], " +
                            "[data-testid='recipient_type-cell']"
            );

            String cellName = oversCells.get(0).getText();
            String eventName = oversCells.get(1).getText();
            String recipient = oversCells.get(2).getText();

            Overview overviewTable = Overview.builder()
                    .name(cellName)
                    .event(eventName)
                    .recipient(recipient)
                    .build();

            overviewList.add(overviewTable);
        }
        return overviewList;
    }
}
