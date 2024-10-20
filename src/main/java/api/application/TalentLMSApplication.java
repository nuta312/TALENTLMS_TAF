package api.application;

import api.controllers.UserController;
import lombok.Data;

@Data
public class TalentLMSApplication {
    private UserController userController;

    public TalentLMSApplication(String url) {
        this.userController = new UserController(url);
    }
}