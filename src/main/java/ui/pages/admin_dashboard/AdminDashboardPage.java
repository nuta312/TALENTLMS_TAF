package ui.pages.admin_dashboard;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pages.BasePage;


import static com.codeborne.selenide.Selenide.$x;

public class AdminDashboardPage extends BasePage {

    public SelenideElement admin = $x("//div[text()='Administrator']");

    @Step("Get admin text")
    public String getAdminText() {
        return elementActions.getText(admin);
    }
}
