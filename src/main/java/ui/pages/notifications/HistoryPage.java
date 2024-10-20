package ui.pages.notifications;

import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.notifications.History;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

public class HistoryPage extends BasePage {

    public SelenideElement searchEmail = $(By.id("table-search"));
    public SelenideElement clearHistory = $x("//span[normalize-space()='Clear notification history']");
    public SelenideElement recipient = $x("//span[text()='Recipient']");
    public SelenideElement subject = $x("//span[text()='Subject']");
    public SelenideElement date = $x("//span[text()='Date']");
    public SelenideElement deleteHistory = $x("//span[normalize-space()='Delete']");
    public SelenideElement getClearHistory = $x("//span[text()='Clear notification history']");
    public SelenideElement nextPageButton = $x("//button[@title='next page']");
    public SelenideElement nextPage = $(By.xpath("//button[@title='next page']//span[@class='btn-text']//*[name()='svg']"));

    public boolean isNextPageButtonPresent() {
        return nextPage.exists() && nextPage.isDisplayed();
    }

    public String getNextPageButton() {
        return elementActions.getText(nextPageButton);
    }

    public String getClearHistory() {
        return elementActions.getText(clearHistory);
    }

    public String getRecipient() {
        return elementActions.getText(recipient);
    }

    public String getSubject() {
        return elementActions.getText(subject);
    }

    public String getDate() {
        return elementActions.getText(date);
    }

    public ArrayList<History> getHistoryTable() {
        ArrayList<History> historyList = new ArrayList<>();

        ElementsCollection rows = $$("div[data-testid='table'] tbody tr");
        rows.shouldHave(sizeGreaterThan(0));

        for (SelenideElement row : rows) {
            ElementsCollection historyCells = row.$$("[data-testid='recipient-cell'], " +
                    "[data-testid='subject-cell'], " +
                    "[data-testid='created_at-cell']");

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
