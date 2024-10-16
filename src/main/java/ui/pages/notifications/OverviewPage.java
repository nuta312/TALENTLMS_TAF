package ui.pages.notifications;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class OverviewPage extends BasePage {

    public SelenideElement notifications = $x("//div[text()='Notifications']");
    public SelenideElement addNotification = $x("//span[normalize-space()='Add notification']");
    public SelenideElement name = $(By.id("name"));
    public SelenideElement selectEvent = $(By.id("react-select-2-input"));
}
