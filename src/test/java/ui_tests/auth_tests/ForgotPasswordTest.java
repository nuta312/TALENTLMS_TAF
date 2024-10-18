package ui_tests.auth_tests;

import common.utils.MailosaurManager;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class ForgotPasswordTest {

    MailosaurManager mailosaurManager = new MailosaurManager();

    @Test
    void testForgotPassword() throws Exception {
        open("https://testmailosour.talentlms.com/plus/login");

        String apikey = "ZfvbxF0hk6edqydWU0AkwsEhigud1IAa";
        String serverId = "r2igco7n";
        String email = "duty-explain@r2igco7n.mailosaur.net";

        mailosaurManager
                .withCredentials(apikey, serverId)
                .fetchPasswordResetLink(email)
                .resetPassword("newStrongPassword1234");
    }
}