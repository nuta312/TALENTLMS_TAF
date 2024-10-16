package ui.pages.admin_dashboard;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CoursesPage extends BasePage {

    public void openCoursesPage() {
        open("https://brusbeishe.talentlms.com/plus/courses");
    }

    // Метод для ввода текста в поле поиска
    public void searchCourse(String searchText) {
        SelenideElement searchInput = $("#table-search");  // Найти поле поиска по ID
        searchInput.setValue(searchText);  // Установить значение в поле поиска
    }

    public void deleteCourseByName(String courseName) {


        SelenideElement courseRow = $x("//table/tbody/tr[contains(., '" + courseName + "')]");

        // Убедимся, что строка с курсом существует и видима
        courseRow.shouldBe(visible);
        courseRow.hover();
        // Находим кнопку удаления внутри этой строки
        SelenideElement deleteButton = courseRow.$("[data-testid='delete-action']");

        // Убедимся, что кнопка удаления существует и видима
        deleteButton.shouldBe(visible);

        // Нажимаем кнопку удаления
        deleteButton.click();

        // Подтверждаем удаление, если появляется диалог подтверждения
        SelenideElement confirmButton = $x("//button[contains(@class, 'solid') and contains(@class, 'css-11y7qqz')]");
        if (confirmButton.exists() && confirmButton.isDisplayed()) {
            confirmButton.click();
        }

        // Убедимся, что строка больше не отображается в таблице
        courseRow.should(disappear);
//        // Получаем общее количество строк в таблице
//        ElementsCollection rows = $$(By.xpath("//*[@id='scroll-container']/div[4]/div/div[1]/table/tbody/tr")); // Указываем на строки
//        int totalRows = rows.size();
//
//        // Проверяем, что индекс в пределах допустимых значений
//        if (rowIndex < 1 || rowIndex > totalRows) {
//            throw new IllegalArgumentException("Invalid row index: " + rowIndex + ". Total rows: " + totalRows);
//        }
//
//        // Находим строку таблицы по индексу
//        SelenideElement courseRow = rows.get(rowIndex - 1);
//        courseRow.shouldBe(visible);
//
//        // Находим кнопку удаления внутри этой строки
//        SelenideElement deleteButton = courseRow.$("[data-testid='delete-action']");
//        deleteButton.shouldBe(visible);
//
//        // Нажимаем кнопку удаления
//        deleteButton.click();
//
//        // Подтверждаем удаление, если появляется диалог подтверждения
//        SelenideElement confirmButton = $x("//button[contains(@class, 'solid') and contains(@class, 'css-11y7qqz')]");
//        if (confirmButton.exists() && confirmButton.isDisplayed()) {
//            confirmButton.click();
//        }
//
//        // Ожидание, что строка будет удалена из таблицы
//        courseRow.should(disappear);
    }


    public void filterCoursesByCategory(String categoryName) {
        // Открыть выпадающий список категорий
        $(By.xpath("//div[@role='tooltip' and @aria-label='Filters' and @aria-expanded='false']")).click();

        // Ожидание, пока выпадающий список станет видимым
        $(By.xpath("//ul[@role='list' and @class='dropdown-list css-12vlx8x']")).shouldBe(visible);

        // Кликаем на нужный элемент
        $$(".dropdown-list-item span").findBy(text(categoryName)).click();
    }

    // Добавить новый курс
    public void addNewCourse(String courseName)  {
        // Путь к изображению для загрузки
        String path = "src/main/java/ui/pages/images/Godzilla.jpg";

        // Найти и нажать на кнопку "Add Course"
        SelenideElement addCourseButton = $x("//button[@class='start-button solid css-j2mmvp']");
        addCourseButton.click();



        // Заполнить поле "Название курса"
        SelenideElement courseNameField = $("div.editable-container[contenteditable=\"true\"]");

        courseNameField.setValue(courseName);

        // Загрузить файл изображения в курс
        SelenideElement uploadFileInput = $("input[type='file']"); // Предположительно input для загрузки файла

        uploadFileInput.uploadFile(new File(path));

        // Проверяем, что изображение загружено (опционально)
        // $(By.xpath("//*[contains(text(), 'Godzilla.jpg')]")).should(appear); // Пример проверки, что файл загружен

        // Нажать на кнопку сохранения
        SelenideElement publishButton = $x("//button[@class='solid css-1cv4mj0' and @aria-label='Publish course']");
        publishButton.shouldBe(visible).click();



    }

}
