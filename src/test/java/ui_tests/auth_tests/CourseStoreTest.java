package ui_tests.auth_tests;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.pages.auth.LoginPage;
import ui.pages.course_store.CoursePage;
import ui.pages.course_store.MainStore;
import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.*;

public class CourseStoreTest {
    public LoginPage loginPage = new LoginPage();
    public CoursePage coursePage = new CoursePage();
    public MainStore mainStore = new MainStore();

@Step("Кликаем раздел Course store")
    @Test
    void storeTest1() {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        coursePage
                .clickCourse()
                .clickBusinessSkills()
                .clickCoachingApplied();
        Assertions.assertEquals("Introduction to Business Emergency Preparedness Planning", mainStore.getCartoonText());
    }

    @Test
    void storeTest2() {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        coursePage
                .clickCourse()
                .inputTableSearch("Introduction to Business Emergency Preparedness Planning");
        Assertions.assertEquals("Introduction to Business Emergency Preparedness Planning", mainStore.getCartoonText());
    }

    @Test
    void storeTest3() throws InterruptedException {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        coursePage
                .clickCourse()
                .clickToSpanish();
        Assertions.assertEquals("Los valores clave de igualdad, diversidad e inclusión", mainStore.getCartoonSpanishText());
        Thread.sleep(5000);
    }
}

