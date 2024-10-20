package ui_tests;

import common.entities.MyProfile.FakerNyProfile;
import common.entities.MyProfile.MyProfile;
import org.junit.jupiter.api.Test;
import ui.pages.MyProfile.MyProfilePage;
import ui.pages.auth.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;
import static io.qameta.allure.Allure.step;

public class MyProfile_Tests {

    public LoginPage loginPage = new LoginPage();
    public MyProfilePage myProfilePage = new MyProfilePage();
    public MyProfile myProfile = FakerNyProfile.randomField();

    @Test
    void testLoginPositive() throws InterruptedException {
        step("Open login page", () ->
                open(getAppConfig().base_url())
        );
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());
        myProfilePage.clickMenu().clickMyProfile()
                .fillField(myProfile)
                .clickTimeZone()
                .clickTime1()
                .clickFieldLanguage()
                .clickLanguage();
        Thread.sleep(7000);
    }
}