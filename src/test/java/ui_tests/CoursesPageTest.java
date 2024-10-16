package ui_tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.admin_dashboard.CoursesPage;
import ui.pages.auth.LoginPage;

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
    }

    @Test
    public void testSearchCourse() throws InterruptedException {// для поиска
        open("https://kurmanaliev.talentlms.com/plus/courses");

        String courseName = "Bachelor";

        coursesPage.searchCourse(courseName);

        $x("//td[@data-testid='name-cell' and contains(., '" + courseName + "')]").shouldBe(visible);
        Thread.sleep(4000);
    }

    @Test
    public void testDeleteCourseByRowIndex() throws InterruptedException {

        open("https://kurmanaliev.talentlms.com/plus/courses");

        String courseName = "New course";

        $$x("//div[@id='scroll-container']//table/tbody/tr").shouldHave(sizeGreaterThan(0));

        ElementsCollection courseRows = $$x("//div[@id='scroll-container']//table/tbody/tr");
        if (courseRows.filterBy(text(courseName)).isEmpty()) {
            coursesPage.addNewCourse(courseName);
            $x("//table/tbody/tr[contains(., '" + courseName + "')]").shouldBe(visible);
        }

        coursesPage.deleteCourseByName(courseName);

        $x("//table/tbody/tr[contains(., '" + courseName + "')]").shouldNotBe(visible);

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

        String courseName = "Master of Business"; // Чтобы избежать дубликатов

        coursesPage.addNewCourse(courseName);

        SelenideElement addedCourse = $x("//div[@class='course-title' and text()='" + courseName + "']");
        addedCourse.shouldBe(visible);

        SelenideElement courseList = $x("//div[@class='courses-list']");
        courseList.shouldHave(text(courseName));
        Thread.sleep(5000);
    }
}

