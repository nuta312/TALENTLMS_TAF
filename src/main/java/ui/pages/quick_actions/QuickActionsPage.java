package ui.pages.quick_actions;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class QuickActionsPage extends BasePage {

    public SelenideElement addUser = $x("//div[text()='Add user']");
    public SelenideElement addCourse = $x("//div[text()='Add course']");
    public SelenideElement addGroup = $x("//div[text()='Add group']");
    public SelenideElement addCategory = $x("//div[text()='Add category']");
    public SelenideElement portal = $x("//div[text()='Portal']");
    public SelenideElement importExport = $x("//div[text()='Import-Export']");


    public QuickActionsPage selectOptionInQuickAction (String option){
        switch (option) {
            case "Add user":
                elementActions.click(addUser);
                break;
            case "Add course":
                elementActions.clickElementWithJsExecutor(addCourse);
                break;
            case "Add group":
                elementActions.click(addGroup);
                break;
            case "Add category":
                elementActions.click(addCategory);
                break;
            case "Potal ":
                elementActions.click(portal);
                break;
            case "Inport-export":
                elementActions.click(importExport);
                break;
            default:
                System.out.println("Invalid option selected, try again");
                break;
        }
        return this;
    }
}
