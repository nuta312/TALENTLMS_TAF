package ui_tests.auth_tests;
import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import org.junit.jupiter.api.Test;
import ui.pages.auth.ForgotPasswordPage;

import java.io.IOException;
import static com.codeborne.selenide.Selenide.open;



public class ForgotPasswordTest {

    public ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();

    @Test
    void testForgotPassword() throws MailosaurException, IOException {
        open("https://testmailosour.talentlms.com/plus/login");

        MailosaurClient client = new MailosaurClient("ZfvbxF0hk6edqydWU0AkwsEhigud1IAa");
        MessageSearchParams params = new MessageSearchParams();
        params.withServer("r2igco7n");
        SearchCriteria criteria = new SearchCriteria();
        criteria.withSentTo("duty-explain@r2igco7n.mailosaur.net");

        forgotPasswordPage.doLoginWithIncorrectPassword("testmailosour", "abdse123124");

        Message message = client.messages().get(params, criteria);
        String resetLink = message.html().links().get(1).href();
        open(resetLink);

        forgotPasswordPage.inputNewPassword("Qwerty12!");

        forgotPasswordPage.doLoginWithNewPassword("testmailosour", "Qwerty12!");
    }
}