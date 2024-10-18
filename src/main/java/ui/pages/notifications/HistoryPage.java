package ui.pages.notifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.notifications.History;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class HistoryPage extends BasePage {

    public SelenideElement searchEmail = $(By.id("table-search"));
    public SelenideElement clearHistory = $x("//span[normalize-space()='Clear notification history']");
    public SelenideElement deleteHistory = $x("//span[normalize-space()='Delete']");
    public SelenideElement nextPageButton = $x("//button[@title='next page']");
    public ElementsCollection historyTable = $$("tbody tr:nth-child(1)");

    public String getNextPageButton() {
        return elementActions.getText(nextPageButton);
    }

    public ArrayList<History> getHistoryTable() {
        ArrayList<History> historyList = new ArrayList<>();
        for (SelenideElement row : historyTable) {
            ElementsCollection historyCells = row.$$("[data-testid='recipient-cell'], " +
                    "[data-testid='subject-cell'], " +
                    "[data-testid='created_at-cell']");

            System.out.println("Строка истории: " + row.getText());
            if (historyCells.size() < 3) {
                System.out.println("Недостаточно ячеек в строке.");
                continue;
            }

            String recipient = historyCells.get(0).getText();
            String subject = historyCells.get(1).getText();
            String date = historyCells.get(2).getText();

            History historyEntity = History.builder()
                    .recipient(recipient)
                    .subject(subject)
                    .date(date)
                    .build();
            historyList.add(historyEntity);
        }
        return historyList;
    }
}
