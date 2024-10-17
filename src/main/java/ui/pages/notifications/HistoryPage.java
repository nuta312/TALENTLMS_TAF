package ui.pages.notifications;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HistoryPage extends BasePage {

    public SelenideElement searchEmail = $(By.id("table-search"));
    public SelenideElement clearHistory = $x("//span[normalize-space()='Clear notification history']");
    public SelenideElement deleteHistory = $x("//span[normalize-space()='Delete']");

}
