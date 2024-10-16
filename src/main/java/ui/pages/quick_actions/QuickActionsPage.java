package ui.pages.quick_actions;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class QuickActionsPage extends BasePage {

    public SelenideElement addUser = $x("(//div[@id='leaderboard-widget'])[1]");
    public SelenideElement addCourse = $x("(//div[@id='leaderboard-widget'])[2]");
    public SelenideElement addGroup = $x("(//div[@id='leaderboard-widget'])[3]");
    public SelenideElement addCategory = $x("(//div[@id='leaderboard-widget'])[4]");
    public SelenideElement portal = $x("(//div[@id='leaderboard-widget'])[5]");
    public SelenideElement inportExport = $x("(//div[@id='leaderboard-widget'])[6]");


    public QuickActionsPage selectOptionInQuickAction (String option){
        switch (option) {
            case "Add user":
                elementActions.clickElementWithJsExecutor(addUser);
                break;
            case "Add course":
                elementActions.clickElementWithJsExecutor(addCourse);
                break;
            case "Add group":
                elementActions.clickElementWithJsExecutor(addGroup);
                break;
            case "Add category":
                elementActions.clickElementWithJsExecutor(addCategory);
                break;
            case "Potal ":
                elementActions.clickElementWithJsExecutor(portal);
                break;
            case "Inport-export":
                elementActions.clickElementWithJsExecutor(inportExport);
                break;
            default:
                System.out.println("Invalid option selected, try again");
                break;
        }
        return this;
    }

}
