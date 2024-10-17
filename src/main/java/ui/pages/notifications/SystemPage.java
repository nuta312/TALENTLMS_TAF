package ui.pages.notifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.notifications.SystemNotifications;
import ui.pages.BasePage;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class SystemPage extends BasePage {

    public ElementsCollection systemTable = $$("tr.link");
    public SelenideElement resetPassword = $x("//div[text()='Reset password']");
    public SelenideElement createPassword = $x("//div[text()='Create password']");
    public SelenideElement accountConfirmation = $x("//div[text()='Account confirmation']");
    public SelenideElement accountActivation = $x("//div[text()='Account activation']");
    public SelenideElement exportExcel = $x("//div[text()='Export reports in Excel']");
    public SelenideElement importData = $x("//div[text()='Import data']");
    public SelenideElement replyToDiscussion = $x("//div[text()='Reply to discussion']");

    public String getResetPassword() {
        return elementActions.getText(resetPassword);
    }

    public String getCreatePassword() {
        return elementActions.getText(createPassword);
    }

    public String getAccountConfirmation() {
        return elementActions.getText(accountConfirmation);
    }

    public String getAccountActivation() {
        return elementActions.getText(accountActivation);
    }

    public String getExportExcel() {
        return elementActions.getText(exportExcel);
    }

    public String getImportData() {
        return elementActions.getText(importData);
    }

    public String getReplyToDiscussion() {
        return elementActions.getText(replyToDiscussion);
    }

    public ArrayList<SystemNotifications> getSystemTable() {
        ArrayList<SystemNotifications> systemList = new ArrayList<>();
        for (SelenideElement row : systemTable) {
            ElementsCollection oversCells = row.$$("[data-testid='type-cell'], " +
                    "[data-testid='recipient-cell']"
            );

            String cellName = oversCells.get(0).getText();
            String recipient = oversCells.get(1).getText();

            SystemNotifications systemTable = SystemNotifications.builder()
                    .name(cellName)
                    .recipient(recipient)
                    .build();

            systemList.add(systemTable);
        }
        return systemList;
    }
}
