package ui.pages.course;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.Course;
import org.openqa.selenium.By;
import ui.helper.SelenideElementActions;
import ui.pages.BasePage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CoursesPage extends BasePage {

    SelenideElement searchInput = $("#table-search");


    public ElementsCollection courseRows = $$("tr.link");

    public SelenideElement coursesSidebar = $(By.id("courses"));

    public SelenideElement filterButton = $(By.xpath("//div[@class=\"dropdown css-1dpepst\"]"));
    public SelenideElement activeStatus = $(By.xpath("//li[@class=\"dropdown-list-item active css-tk2zxu\"]"));
    public SelenideElement inactiveStatus = $(By.xpath("//li[@class=\"dropdown-list-item inactive css-tk2zxu\"]"));
    SelenideElement addCourseButton = $x("//button[@class='start-button solid css-j2mmvp']");
    public SelenideElement editCourseButton = $(By.cssSelector("div[aria-label='Edit']"));

    public SelenideElement coursesList = $(By.xpath("//td[@class=\"selectable-cell autohide-cell\"]"));

    public List<SelenideElement> courseOne = new ArrayList<>();
    public Course courseEntity;
    public SelenideElementActions elementActions = new SelenideElementActions();

    public void getCoursesList() {
        ElementsCollection courseList = $$("tr.link");
        for (SelenideElement course : courseList) {
            System.out.println(elementActions.getText(course));
            System.out.println("===============");
        }
    }

    public String getCourseDetailsByText(String courseName) {
        ElementsCollection courseList = $$("tr.link");
        courseOne.clear();
        for (SelenideElement course : courseList) {
            courseOne.add(course);
        }

        for (SelenideElement course : courseOne) {
            String courseDetails = course.getText();
            if (courseDetails.contains(courseName)) {
                return courseDetails;
            }
        }
        return null;
    }

    public ArrayList<Course> getCourseFromTable() {
        ArrayList<Course> courseEntities = new ArrayList<>();

        for (SelenideElement courseRow : courseRows) {
            ElementsCollection courseCells = courseRow.$$("[data-testid='name-cell'], " +
                    "[data-testid='code-cell'], " +
                    "[data-testid='category-cell'], " +
                    "[data-testid='price-cell'], " +
                    "[data-testid='last_updated_on-cell']"
            );
            String courseName = courseCells.get(0).getText();
            String courseCode = courseCells.get(1).getText();
            String courseCategory = courseCells.get(2).getText();
            String coursePrice = courseCells.get(3).getText();
            String courseLastUpdate = courseCells.get(4).getText();

            Course courseEntity = Course.builder()
                    .courseName(courseName)
                    .courseCode(courseCode)
                    .courseCategory(courseCategory)
                    .coursePrice(coursePrice)
                    .courseLastUpdated(courseLastUpdate)
                    .build();

            courseEntities.add(courseEntity);

        }
        return courseEntities;
    }

    public Course getCourse(String courseName) {
        List<Course> courses = getCourseFromTable();
        for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }



    public void searchCourse(String searchText) {

        searchInput.setValue(searchText);
    }

    public void deleteCourseByName(String courseName) {
        SelenideElement courseRow = $$x("//table/tbody/tr")
                .findBy(Condition.text(courseName))
                .shouldBe(Condition.visible); // Ожидаем появления строки

        // Скроллим курс в зону видимости и наводим мышь
        courseRow.scrollIntoView(true).hover();

        // Находим кнопку удаления и кликаем на неё с проверкой
        SelenideElement deleteButton = courseRow.$("[data-testid='delete-action']");
        deleteButton.shouldBe(Condition.visible, Condition.enabled);
        elementActions.clickElementWithJsExecutor(deleteButton);

        // Ожидаем появления кнопки подтверждения и кликаем на неё
        SelenideElement confirmButton = $x("//button[contains(@class, 'solid') and contains(@class, 'css-11y7qqz')]");
        confirmButton.shouldBe(Condition.visible, Condition.enabled);
        elementActions.click(confirmButton);

        // Проверяем, что строка с курсом исчезла
        courseRow.should(Condition.disappear);

    }


    public void filterCoursesByCategory(String category) {


        SelenideElement filterDropdown = $(".dropdown");
        executeJavaScript("arguments[0].scrollIntoView(true);", filterDropdown);

        elementActions.click(filterDropdown);

        SelenideElement categoryOption = $$(".dropdown-list-item").findBy(Condition.text(category));
        elementActions.clickElementWithJsExecutor(categoryOption);
    }

    public void addNewCourse(String courseName, String codValue, String option, String priceValue) {
        String path = "src/main/java/ui/pages/images/Godzilla.jpg";

        elementActions.clickElementWithJsExecutor(addCourseButton);

        SelenideElement courseNameField = $("div.editable-container[contenteditable=\"true\"]");
        courseNameField.setValue(courseName);

        SelenideElement units = $x("//span[text()='All units must be completed']");
        elementActions.click(units);

        SelenideElement courseDetails = $x("//div[text()='Course details']");
        elementActions.clickElementWithJsExecutor(courseDetails);

        SelenideElement inputCourseCode = $x("//input[@data-testid='catalog-code-input']");
        inputCourseCode.setValue(codValue);

        // Открываем выпадающий список
        SelenideElement dropdown = $x("//div[@class='select-input-wrapper']"); // Замените на правильный XPath вашего выпадающего списка
        elementActions.click(dropdown);

        $x("//div[@class=' css-5v3ri6-menu']").shouldBe(visible);
        sleep(500);

        // Определить элемент для выбора на основе переданного параметра option
        SelenideElement optionToSelect;

        switch (option) {
            case "API":
                optionToSelect = $x("//div[@id='react-select-6-option-0']");
                break;
            case "Samples":
                optionToSelect = $x("//div[@id='react-select-6-option-1']");
                break;
            case "test":
                optionToSelect = $x("//div[@id='react-select-6-option-2']");
                break;
            default:
                throw new IllegalArgumentException("Неизвестный параметр option: " + option);
        }

        // Кликнуть на выбранный элемент
        optionToSelect.scrollIntoView(true).shouldBe(visible).click();

        // Ввод цены
        SelenideElement priceField = $x("//input[@id='course-price']");
        priceField.shouldBe(visible).setValue(priceValue);

        // Клик по кнопке сохранения
        SelenideElement saveBtn = $x("//button[@class='left-aligned solid css-j2mmvp']");
        saveBtn.shouldBe(visible).click();

        // Загрузка файла
        SelenideElement uploadFileInput = $("input[type='file']");
        uploadFileInput.uploadFile(new File(path));

        // Публикация курса
        SelenideElement publishButton = $x("//button[@class='solid css-1cv4mj0' and @aria-label='Publish course']");
        elementActions.click(publishButton.shouldBe(visible));
    }
//        String path = "src/main/java/ui/pages/images/Godzilla.jpg";
//
//        SelenideElement addCourseButton = $x("//button[@class='start-button solid css-j2mmvp']");
//        elementActions.clickElementWithJsExecutor(addCourseButton);
//
//        SelenideElement courseNameField = $("div.editable-container[contenteditable=\"true\"]");
//        courseNameField.setValue(courseName);
//
//        SelenideElement units = $x("//span[text()='All units must be completed']");
//        elementActions.click(units);
//
//        SelenideElement courseDetails = $x("//div[text()='Course details']");
//        elementActions.clickElementWithJsExecutor(courseDetails);
//
//        SelenideElement inputCourseCode = $x("//input[@data-testid='catalog-code-input']");
//        inputCourseCode.setValue(codValue);
//
//        // Выбор опции
//        SelenideElement select = $x("//div[@class=' css-19bb58m']");
//        elementActions.click(select);
//
//        // Ожидание появления опций
//        SelenideElement apiOption = $x("//div[@id='react-select-7-option-0']//div[@class='custom-option' and text()='API']").shouldBe(visible); ////div[@class='option-md css-bi1k7u-option']//div[@class='custom-option']
//        SelenideElement samplesOption = $x("//div[@class='custom-option' and text()='Samples']");
//        SelenideElement testOption = $x("//div[@id='react-select-7-option-2']"); ////div[@class='custom-option' and text()='test']
//
//        // Выбор нужной опции
//        if (option.equals("API")) {
//           elementActions.click(apiOption);
//        } else if (option.equals("Samples")) {
//            elementActions.click(samplesOption);
//        } else if (option.equals("test")) {
//            elementActions.click(testOption);
//        }
//
//        // Ввод цены
//        SelenideElement priceField = $x("//input[@id='course-price']");
//        priceField.shouldBe(visible).setValue(priceValue);
//
//        // Клик по кнопке сохранения
//        SelenideElement saveBtn = $x("//button[@class='left-aligned solid css-j2mmvp']");
//        saveBtn.shouldBe(visible).click();
//
//        // Загрузка файла
//        SelenideElement uploadFileInput = $("input[type='file']");
//        uploadFileInput.uploadFile(new File(path));
//
//        // Публикация курса
//        SelenideElement publishButton = $x("//button[@class='solid css-1cv4mj0' and @aria-label='Publish course']");
//        elementActions.click(publishButton.shouldBe(visible));
//    }

//        String path = "src/main/java/ui/pages/images/Godzilla.jpg";
//
//        SelenideElement addCourseButton = $x("//button[@class='start-button solid css-j2mmvp']");
//        elementActions.clickElementWithJsExecutor(addCourseButton);
//
//        SelenideElement courseNameField = $("div.editable-container[contenteditable=\"true\"]");
//        courseNameField.setValue(courseName);
//
//        SelenideElement units = $x("//span[text()='All units must be completed']");
//        elementActions.click(units);
//
//        SelenideElement courseDetails = $x("//div[text()='Course details']");
//        elementActions.clickElementWithJsExecutor(courseDetails);
//
//        SelenideElement inputCourseCode = $x("//input[@data-testid='catalog-code-input']");
//        elementActions.input(inputCourseCode, "2");
//
//        // Открыть выпадающий список
//        SelenideElement select = $x("//div[@class=' css-19bb58m']");
//        elementActions.click(select);
//
//        // Выбор опции
//        SelenideElement selectedOption;
//        switch (option) {
//            case "API":
//                selectedOption = $x("//div[@class='option-md css-bi1k7u-option']//div[@class='custom-option' and text()='API']");
//                break;
//            case "Samples":
//                selectedOption = $x("//div[@class='option-md css-iovxfg-option' and @id='react-select-8-option-1']//div[@class='custom-option' and text()='Samples']");
//                break;
//            case "test":
//                selectedOption = $x("//div[@class='option-md css-iovxfg-option' and @id='react-select-8-option-2']//div[@class='custom-option' and text()='test']");
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid option: " + option);
//        }
//        elementActions.click(selectedOption);
//
//        // Ввод цены
//        SelenideElement price = $x("//input[@id='course-price']");
//        price.setValue(priceValue);
//
//        SelenideElement saveBtn = $x("//button[@class='left-aligned solid css-j2mmvp']");
//
//        // Загрузка файла
//        SelenideElement uploadFileInput = $("input[type='file']");
//        uploadFileInput.uploadFile(new File(path));
//
//        // Публикация курса
//        SelenideElement publishButton = $x("//button[@class='solid css-1cv4mj0' and @aria-label='Publish course']");
//        elementActions.click(publishButton.shouldBe(visible));
//    }

}
