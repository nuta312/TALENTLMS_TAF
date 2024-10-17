package ui_tests.auth_tests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;
import static ui_tests.BaseUiTest.*;

public class InstructorTest {
    @BeforeAll
    public static void beforeMethod() throws InterruptedException {

        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
    }

    @Test
    @DisplayName("check instructor text")
    void checkInstructorTest() {
        adminDashboardPage.moveToIkon();
        instructorDashboardPage.clickInstructor();
        instructorDashboardPage.verifyInstructorText();
    }
}
