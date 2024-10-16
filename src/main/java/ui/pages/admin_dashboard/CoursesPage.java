package ui.pages.admin_dashboard;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import java.io.File;

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


    public void filterCoursesByCategory(String categoryName) {
        $(By.xpath("//div[@role='tooltip' and @aria-label='Filters' and @aria-expanded='false']")).click();

        $(By.xpath("//ul[@role='list' and @class='dropdown-list css-12vlx8x']")).shouldBe(visible);

        $$(".dropdown-list-item span").findBy(text(categoryName)).click();
    }

    public void addNewCourse(String courseName)  {

        String path = "src/main/java/ui/pages/images/Godzilla.jpg";

        SelenideElement addCourseButton = $x("//button[@class='start-button solid css-j2mmvp']");
        elementActions.click(addCourseButton);

        SelenideElement courseNameField = $("div.editable-container[contenteditable=\"true\"]");

        courseNameField.setValue(courseName);

        SelenideElement uploadFileInput = $("input[type='file']"); // Предположительно input для загрузки файла

        uploadFileInput.uploadFile(new File(path));


        SelenideElement publishButton = $x("//button[@class='solid css-1cv4mj0' and @aria-label='Publish course']");
        elementActions.click(publishButton.shouldBe(visible));
    }

}
