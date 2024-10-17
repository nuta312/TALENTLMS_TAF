package ui.pages.auth;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ForgotPasswordPage extends BasePage {

    public SelenideElement loginInput = $(By.id("username"));
    public SelenideElement passwordInput = $(By.id("password"));
    public SelenideElement loginButton = $x("//button[@type='submit']");
    public SelenideElement forgotPasswordLink = $(By.xpath("//div[text()='Forgot your password?']"));
    public SelenideElement inputLoginForResetPassword = $(By.xpath("//input[@id='username']"));
    public SelenideElement sendMeInstructionBtn = $(By.xpath("//span[text()='Send me instructions']"));
    public SelenideElement inputNewPassword = $(By.xpath("//input[@id='password']"));
    public SelenideElement repeatNewPassword = $(By.xpath("//input[@id='retypePassword']"));
    public SelenideElement resetPasswordBtn = $(By.xpath("//span[text()='Reset password']"));

    @Step("Perform login with an incorrect password and request password reset")
    public ForgotPasswordPage doLoginWithIncorrectPassword(String login, String password) {
        elementActions.input(loginInput, login)
                .input(passwordInput, password)
                .click(loginButton)
                .click(forgotPasswordLink)
                .input(inputLoginForResetPassword, login)
                .click(sendMeInstructionBtn);
        return new ForgotPasswordPage();
    }

    @Step("Input a new password")
    public ForgotPasswordPage inputNewPassword(String newPassword) {
        elementActions.input(inputNewPassword, newPassword)
                .input(repeatNewPassword, newPassword)
                .click(resetPasswordBtn);
        return new ForgotPasswordPage();
    }

    @Step("Login with the new password")
    public ForgotPasswordPage doLoginWithNewPassword(String login, String newPassword) {
        elementActions.input(loginInput, login)
                .input(passwordInput, newPassword)
                .click(loginButton);
        return new ForgotPasswordPage();
    }
}
