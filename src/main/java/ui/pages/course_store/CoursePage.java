package ui.pages.course_store;
import com.codeborne.selenide.SelenideElement;
import ui.pages.BasePage;
import static com.codeborne.selenide.Selenide.$x;
import io.qameta.allure.Step;


public class CoursePage extends BasePage {
    public SelenideElement courseStore = $x("//div[text() = 'Course store']");
    public SelenideElement businessSkills = $x("//span[text() = 'Business Skills']");
    public SelenideElement coachingApplied = $x("//span[text() = 'Coaching Applied']");
    public SelenideElement tableSearch = $x("//input[@id = 'table-search']");
    public SelenideElement spanishLanguage = $x("//span[text()='Spanish']");

    @Step("Кликаем раздел Course store")
    public CoursePage clickCourse() {
        elementActions.clickElementWithJsExecutor(courseStore);
        return this;
    }


    public CoursePage clickBusinessSkills() {
        elementActions.clickElementWithJsExecutor(businessSkills);
        return this;
    }

    public CoursePage clickCoachingApplied() {
        elementActions.clickElementWithJsExecutor(coachingApplied);
        return this;
    }

    @Step("Ищем определенный элемент в поисковике")
    public CoursePage inputTableSearch(String search) {
        elementActions.input(tableSearch, search);
        return this;
    }

    @Step("Меняем язык в разделе Course store")
    public CoursePage clickToSpanish() {
        elementActions.clickElementWithJsExecutor(spanishLanguage);
        return this;
    }
}
