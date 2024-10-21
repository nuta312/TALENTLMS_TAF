package ui.pages.admin_dashboard;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class AdminDashboardPage extends BasePage {

    public SelenideElement admin = $x("//div[text()='Administrator']");

    public SelenideElement leftSidebarMenu = $("#main-menu");
    public SelenideElement ikon = $x("//figure[@class='css-m3n88o']");
    @Step("Get admin text")
    public String getAdminText() {
        return elementActions.getText(admin);
    }


    @Step("Select element from left sidebar")
    public void selectMenuItem(String menuItem) {
        SelenideElement menu = leftSidebarMenu.$x(".//li//*[contains(text(), '" + menuItem + "')]").scrollTo();
        elementActions.click(menu);
    }

    @Step("Наводим мышку на иконку в админской панели")
    public void moveToIkon() {
        actions().moveToElement(ikon).perform();
    }
}
