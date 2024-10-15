package ui.pages.course_store;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CoursePage extends BasePage {

    public SelenideElement courseStore = $x("//div[text() = 'Course store']");
    public SelenideElement businessSkills = $x("//span[text() = 'Business Skills']");
    public SelenideElement coachingApplied = $x("//span[text() = 'Coaching Applied']");
    public SelenideElement tableSearch = $x("//input[@id = 'table-search']");
    public SelenideElement spanishLanguage = $x("//span[text()='Spanish']");


    //    public MainStore clickCourse() {
//        elementActions.click(courseStore);
//        return new MainStore();
//
//    }
    public CoursePage clickCourse() {
        elementActions.click(courseStore);
        return this;

    }

    public CoursePage clickBusinessSkills() {
        elementActions.click(businessSkills);
        return this;
    }

    public CoursePage clickCoachingApplied() {
        elementActions.click(coachingApplied);
        return this;

    }

    public CoursePage inputTableSearch(String search) {
        elementActions.input(tableSearch, search);
        return this;
    }

    public CoursePage clickToSpanish() {
        elementActions.click(spanishLanguage);
        return this;

    }
}
