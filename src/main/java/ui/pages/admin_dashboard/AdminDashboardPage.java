package ui.pages.admin_dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ui.pages.BasePage;


import static com.codeborne.selenide.Selenide.$x;

public class AdminDashboardPage extends BasePage {

    public SelenideElement admin = $x("//div[text()='Administrator']");


    public String getAdminText() {
        return elementActions.getText(admin);
    }

}
