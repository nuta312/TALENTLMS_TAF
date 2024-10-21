package ui.pages.auth;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.pages.BasePage;
import ui.pages.admin_dashboard.AdminDashboardPage;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    public SelenideElement loginInput = $(By.id("username"));
    public SelenideElement passwordInput = $(By.id("password"));
    public SelenideElement loginButton = $x("//button[@type='submit']");

    public SelenideElement errorMessage = $(By.xpath("//p[text()='Your username or password is incorrect. Please try again.']"));
    @Step("Аутентификация")
    public AdminDashboardPage doLogin(String username, String password){
        elementActions.input(loginInput, username)
                .input(passwordInput, password)
                .click(loginButton);
        return new AdminDashboardPage();
    }

    @Step("Попытка входа с невалидным паролем: {username}")
    public void doLoginWithInvalidPassword(String username, String invalidPassword) {
        elementActions.input(loginInput, username)
                .input(passwordInput, invalidPassword)
                .click(loginButton);
    }


    @Step("Попытка входа с невалидным логином: {invalidUsername}")
    public void doLoginWithInvalidUsername(String invalidUsername, String password) {
        elementActions.input(loginInput, invalidUsername)
                .input(passwordInput, password)
                .click(loginButton);
    }

    @Step("Метод для проверки, что отображается сообщение об ошибке")
    public String isErrorMessageDisplayed() {
        return errorMessage.getText();
    }
    }

