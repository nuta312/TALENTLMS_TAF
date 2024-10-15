package ui.pages.admin_dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import ui.pages.BasePage;


import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class AdminDashboardPage extends BasePage {

    public SelenideElement admin = $x("//div[text()='Administrator']");

    public String getAdminText() {
        return elementActions.getText(admin);
    }

    public void selectMenuItem(String menuItem) {
        SelenideElement menu = $("#main-menu").$x(".//li//*[contains(text(), '" + menuItem + "')]").scrollTo();
        menu.click();
    }
}
