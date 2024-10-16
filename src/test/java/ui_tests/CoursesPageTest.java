package ui_tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.admin_dashboard.CoursesPage;
import ui.pages.auth.LoginPage;
import ui_tests.auth_tests.LoginTests;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class CoursesPageTest {

    private CoursesPage coursesPage;
    public LoginPage loginPage = new LoginPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();


    @BeforeEach
    public void setUp() {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        Assertions.assertEquals("Administrator", adminDashboardPage.getAdminText());

        coursesPage = new CoursesPage();
        coursesPage.openCoursesPage(); // Открываем страницу курсов перед каждым тестом
    }

    @Test
    public void testSearchCourse() throws InterruptedException {// для поиска


        open("https://kurmanaliev.talentlms.com/plus/courses");
        // Даем тестовому курсу имя
        String courseName = "Bachelor";

        // Вводим текст в поле поиска
        coursesPage.searchCourse(courseName);


        $x("//td[@data-testid='name-cell' and contains(., '" + courseName + "')]").shouldBe(visible);
        Thread.sleep(4000);
    }

    @Test
    public void testDeleteCourseByRowIndex() throws InterruptedException {
        // Открываем страницу с курсами
        open("https://kurmanaliev.talentlms.com/plus/courses");

        String courseName = "Master of Teaching";

        // Ожидание загрузки таблицы курсов
        $$x("//div[@id='scroll-container']//table/tbody/tr").shouldHave(sizeGreaterThan(0));

        // Проверяем, существует ли курс
        ElementsCollection courseRows = $$x("//div[@id='scroll-container']//table/tbody/tr");
        if (courseRows.filterBy(text(courseName)).isEmpty()) {
            // Если курс не существует, добавляем его
            coursesPage.addNewCourse(courseName);

            // Убедимся, что курс добавлен
            $x("//table/tbody/tr[contains(., '" + courseName + "')]").shouldBe(visible);
        }

        // Удаляем курс по имени
        coursesPage.deleteCourseByName(courseName);

        // Проверяем, что курс больше не отображается в таблице
        $x("//table/tbody/tr[contains(., '" + courseName + "')]").shouldNotBe(visible);

        // Дополнительно можно проверить, что общее количество строк уменьшилось
        ElementsCollection rowsAfterDeletion = $$x("//div[@id='scroll-container']//table/tbody/tr");
        Assertions.assertEquals(0, rowsAfterDeletion.filterBy(text(courseName)).size(),
                "The course should be removed from the table.");
    }

    @Test
    public void testFilterCoursesByCategory() throws InterruptedException {
        open("https://kurmanaliev.talentlms.com/plus/courses");
        String categoryName = "Inactive";
        coursesPage.filterCoursesByCategory(categoryName);

        $$(".dropdown-list-item span").findBy(text(categoryName)).shouldBe(visible);
        // Дополнительные проверки для курсов в таблице
        Thread.sleep(4000);
    }

    @Test
    public void testAddNewCourse() throws InterruptedException {
        open("https://kurmanaliev.talentlms.com/plus/courses");


        // Название курса для теста
        String courseName = "Master of Business"; // Чтобы избежать дубликатов

        // Добавляем новый курс
        coursesPage.addNewCourse(courseName);

        // Проверка, что курс был добавлен и отображается на странице курсов
        SelenideElement addedCourse = $x("//div[@class='course-title' and text()='" + courseName + "']");
        addedCourse.shouldBe(visible);

        // Опционально: Проверка загрузки файла изображения
        // $(By.xpath("//*[contains(text(), 'Godzilla.jpg')]")).should(appear); // Пример, если проверка файла важна

        // Дополнительные проверки, если есть (например, если курс появляется в списке курсов)
        SelenideElement courseList = $x("//div[@class='courses-list']");
        courseList.shouldHave(text(courseName));
        Thread.sleep(5000);
    }
}

