package ui.learner;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pages.BasePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class CheckLearnerMenuItems extends BasePage {
    public SelenideElement learner = $x("//div[text()='Learner']");

    public SelenideElement usersList = $x("//div[text()='Users']");
    public SelenideElement courses = $x("//div[text()='Courses']");
    public SelenideElement courseStore = $x("//div[text()='Course store']");
    public SelenideElement groups = $x("//div[text()='Groups']");
    public SelenideElement branches = $x("//div[text()='Branches']");
    public SelenideElement automations = $x("//div[text()='Automations']");
    public SelenideElement notifications = $x("//div[text()='Notifications']");
    public SelenideElement reports = $x("//div[text()='Reports']");
    public SelenideElement accountAndSettings = $x("//div[text()='Account & Settings']");
    public SelenideElement subscription = $x("//div[text()='Subscription']");

    @Step("Get learner text")
    public String getLearnerText() {
        return elementActions.getText(learner);
    }

    @Step("Get menu items")
    public List<SelenideElement> getMenuElements() {
        return List.of(usersList, courses, courseStore, groups, branches, automations, notifications, reports, accountAndSettings, subscription);
    }
}
