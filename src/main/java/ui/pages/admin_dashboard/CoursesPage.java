package ui.pages.admin_dashboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CoursesPage extends BasePage {
    SelenideElement searchInput = $("#table-search");

    public void searchCourse(String searchText) {
        searchInput.setValue(searchText);
    }

    public void deleteCourseByName(String courseName) {
        SelenideElement courseRow = $x("//table/tbody/tr[contains(., '" + courseName + "')]");

        executeJavaScript("arguments[0].scrollIntoView(true);", courseRow);
        executeJavaScript("var event = new MouseEvent('mouseover', { bubbles: true }); arguments[0].dispatchEvent(event);", courseRow);

        SelenideElement deleteButton = courseRow.$("[data-testid='delete-action']");
        deleteButton.shouldBe(visible);
        elementActions.click(deleteButton);

        SelenideElement confirmButton = $x("//button[contains(@class, 'solid') and contains(@class, 'css-11y7qqz')]");
        if (confirmButton.exists() && confirmButton.isDisplayed()) {
            elementActions.click(confirmButton);
        }
        courseRow.should(disappear);
    }


    public void filterCoursesByCategory(String category) {
        open("https://kurmanaliev.talentlms.com/plus/courses");

        SelenideElement filterDropdown = $(".dropdown");
        elementActions.click(filterDropdown);

        SelenideElement categoryOption = $$(".dropdown-list-item").findBy(Condition.text(category));
        elementActions.click(categoryOption);
    }

    public void addNewCourse(String courseName)  {

        String path = "src/main/java/ui/pages/images/Godzilla.jpg";

        SelenideElement addCourseButton = $x("//button[@class='start-button solid css-j2mmvp']");
        elementActions.click(addCourseButton);

        SelenideElement courseNameField = $("div.editable-container[contenteditable=\"true\"]");
        courseNameField.setValue(courseName);

        SelenideElement units = $x("//span[text()='All units must be completed']");
        elementActions.click(units);

        SelenideElement courseDetails = $x("//div[text()='Course details']");
        elementActions.clickElementWithJsExecutor(courseDetails);

        SelenideElement inputCourseCode = $x("//input[@data-testid='catalog-code-input']");
        elementActions.input(inputCourseCode, "2");

        SelenideElement uploadFileInput = $("input[type='file']");

        uploadFileInput.uploadFile(new File(path));


        SelenideElement publishButton = $x("//button[@class='solid css-1cv4mj0' and @aria-label='Publish course']");
        elementActions.click(publishButton.shouldBe(visible));
    }

}
