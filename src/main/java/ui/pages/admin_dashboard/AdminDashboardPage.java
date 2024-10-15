package ui.pages.admin_dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class AdminDashboardPage extends BasePage {
    public void selectMenuItem(String menuItem) {
        SelenideElement menu = $("#main-menu").$x(".//li//*[contains(text(), '" + menuItem + "')]").scrollTo();
        menu.click();
    }
    public SelenideElement admin = $x("//div[text()='Administrator']");
    public SelenideElement ikon = $x("//figure[@class='css-m3n88o']");

    public String getAdminText() {
        return elementActions.getText(admin);
    }
    @Step("Наводим мышку на иконку в админской панели")
    public void moveToIkon() {
        actions().moveToElement(ikon).perform();
    }

}
