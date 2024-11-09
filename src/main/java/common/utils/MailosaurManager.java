package common.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import com.mailosaur.MailosaurClient;
import com.mailosaur.models.Message;
import com.mailosaur.models.SearchCriteria;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.helper.SelenideElementActions;

import static com.codeborne.selenide.Selenide.$;

public class MailosaurManager {


    SelenideElementActions elementActions = new SelenideElementActions();
    private String apiKey;
    private String serverId;
    private MailosaurClient client;


    public MailosaurManager() {
        this.client = new MailosaurClient(apiKey);
    }


    public MailosaurManager withCredentials(String apiKey, String serverId) {
        this.apiKey = apiKey;
        this.serverId = serverId;
        this.client = new MailosaurClient(apiKey);
        return this;
    }


    public SelenideElement forgotPasswordLink = $(By.xpath("//div[text()='Forgot your password?']"));
    public SelenideElement inputUserEmail = $(By.xpath("//input[@placeholder='Type your username or your email']"));
    public SelenideElement sendMeInstructionBtn = $(By.xpath("//span[text()='Send me instructions']"));
    public SelenideElement inputNewPassword = $(By.xpath("//input[@id='password']"));
    public SelenideElement repeatNewPassword = $(By.xpath("//input[@id='retypePassword']"));
    public SelenideElement resetPasswordBtn = $(By.xpath("//span[text()='Reset password']"));


    /**
     * Выполняет поиск письма с инструкциями по сбросу пароля, отправленного на указанный email.
     * Использует Mailosaur API для получения письма и извлекает ссылку для сброса пароля.
     * Автоматически открывает полученную ссылку для дальнейших действий.
     *
     * @param recipientEmail Email, на который было отправлено письмо для сброса пароля
     */
    @Step("Fetch password reset link from the email sent to {recipientEmail}")
    public MailosaurManager fetchPasswordResetLink(String recipientEmail) throws Exception {
        elementActions.click(forgotPasswordLink)
                .input(inputUserEmail, recipientEmail)
                .click(sendMeInstructionBtn);

        SearchCriteria criteria = new SearchCriteria().withSentTo(recipientEmail);
        Message message = client.messages().get(serverId, criteria);

        String resetLink = message.html().links().get(1).href();
        System.out.println(resetLink);
        Selenide.open(resetLink);
        return new MailosaurManager();
    }


    /**
     * Вводит новый пароль на странице сброса пароля и подтверждает его.
     * Выполняет действия по вводу нового пароля и подтверждению через соответствующие поля.
     * Завершает процесс сброса пароля через интерфейс приложения.
     *
     * @param newPassword Новый пароль, который будет установлен
     */
    @Step("Reset the password to a new one")
    public MailosaurManager resetPassword(String newPassword) {
        elementActions.input(inputNewPassword, newPassword)
                .input(repeatNewPassword, newPassword)
                .click(resetPasswordBtn);
        return new MailosaurManager();
    }
}
