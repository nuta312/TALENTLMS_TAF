package ui_tests;

import com.codeborne.selenide.ElementsCollection;
import common.entities.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.helper.SelenideElementActions;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.course.CoursesPage;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoursesPageTest {

    public CoursesPage coursePage = new CoursesPage();
    public LoginPage loginPage = new LoginPage();
    public AdminDashboardPage adminDashboardPage = new AdminDashboardPage();
    public SelenideElementActions elementActions = new SelenideElementActions();

    @BeforeEach
    public void setUp() {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        assertEquals("Administrator", adminDashboardPage.getAdminText());
        elementActions.click(coursePage.coursesSidebar);
        coursePage = new CoursesPage();
    }

    @Test
    void courseListTest() throws InterruptedException {
        Thread.sleep(4000);
        coursePage.getCoursesList();
    }

    @Test
    public void getCourse() throws InterruptedException {
        Thread.sleep(4000);
        String courseName = "New Course";
        String courseDetails = coursePage.getCourseDetailsByText(courseName);
        if (courseDetails != null) {
            System.out.println("Course details:  " + courseDetails);
        } else {
            System.out.println("Course '" + courseName + "' not found.");
        }
    }

    @Test
    void getCoursesListTest() throws InterruptedException {
        Thread.sleep(4000);
        List<Course> courses = coursePage.getCourseFromTable();

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    @Test
    void getCourseTest() throws InterruptedException {
        Thread.sleep(4000);
        coursePage.getCourse("New Course");
    }

    @Test
    public void testSearchCourse() throws InterruptedException {// для поиска
        String courseName = "Bachelor";

        coursePage.searchCourse(courseName);

        $x("//td[@data-testid='name-cell' and contains(., '" + courseName + "')]").shouldBe(visible);
        Thread.sleep(4000);
    }

    @Test
    public void testDeleteCourseByName() throws InterruptedException {
        String courseName = "New Course";

        $$x("//div[@id='scroll-container']//table/tbody/tr").shouldHave(sizeGreaterThan(0));

        coursePage.deleteCourseByName(courseName);

        $x("//table/tbody/tr[contains(., '" + courseName + "')]").shouldNotBe(visible);

        ElementsCollection rowsAfterDeletion = $$x("//div[@id='scroll-container']//table/tbody/tr");
        assertEquals(0, rowsAfterDeletion.filterBy(text(courseName)).size(),
                "The course should be removed from the table.");
    }

    @Test
    public void testFilterCoursesByCategory() throws InterruptedException {
        String categoryToFilter = "Inactive";
        coursePage.filterCoursesByCategory(categoryToFilter);
        Thread.sleep(5000);
    }

    @Test
    public void testAddNewCourse() throws InterruptedException {
        String courseName = "New Course";
        String codValue = "12";
        String option = "API"; // можно менять на Samples или test
        String priceValue = "1000";

        coursePage.addNewCourse(courseName, codValue, option, priceValue);
    }
}

