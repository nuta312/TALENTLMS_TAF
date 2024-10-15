package ui_tests.auth_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.course_store.CoursePage;
import ui.pages.course_store.MainStore;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.*;

public class CourseStoreTest {

    public LoginPage loginPage = new LoginPage();
    public CoursePage coursePage = new CoursePage();
    public MainStore mainStore = new MainStore();

    @Test
    void storeTest1() {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword()); //Ввели логин и пароль
        coursePage
                .clickCourse()  // заходим  в Course store
                .clickBusinessSkills() // кликаем раздел Business Skills
                .clickCoachingApplied(); // в разделе Business Skills кликаем Coaching Applied
        // в разделе Coaching Applied с названием первого элемента сравниваем ожидаемый результат
        Assertions.assertEquals("Introduction to Business Emergency Preparedness Planning", mainStore.getCartoonText());
    }

    @Test
    void storeTest2() throws InterruptedException {

        //В этом тесте мы ищем в поисковике определенный элемент и сравниваем actual и expected res.

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        Thread.sleep(5000);
        coursePage
                .clickCourse()
                .inputTableSearch("Introduction to Business Emergency Preparedness Planning");

        Thread.sleep(5000);

        Assertions.assertEquals("Introduction to Business Emergency Preparedness Planning", mainStore.getCartoonText());

    }

    @Test
    void storeTest3() throws InterruptedException {

//В этом тесте переключаем язык на испанский и сравниваем испанское название первого элемента с ожидаемым результатом
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        coursePage
                .clickCourse()
                .clickToSpanish();

        Assertions.assertEquals("Los valores clave de igualdad, diversidad e inclusión", mainStore.getCartoonSpanishText());
        Thread.sleep(5000);

    }
}

