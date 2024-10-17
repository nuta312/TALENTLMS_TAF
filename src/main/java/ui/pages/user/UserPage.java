package ui.pages.user;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.User;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$;

public class UserPage {
    public ElementsCollection rowsTable = $$("tr.link");

    public ArrayList<User> getUserFromTable() {
        ArrayList<User> userEntities = new ArrayList<>();
        for (SelenideElement row : rowsTable) {
            ElementsCollection courseCells = row.$$("[data-testid='formatted_name-cell'], " +
                    "[data-testid='email-cell'], " +
                    "[data-testid='type-cell'], " +
                    "[data-testid='registration-cell'], " +
                    "[data-testid='last_login-cell']"
            );

            String userName = courseCells.get(0).getText();
            String email = courseCells.get(1).getText();
            String type = courseCells.get(2).getText();
            String registration = courseCells.get(3).getText();
            String lastLogin = courseCells.get(4).getText();

            User userEntity = User.builder()
                    .userName(userName)
                    .email(email)
                    .type(type)
                    .registration(registration)
                    .lastLogin(lastLogin)
                    .build();

            userEntities.add(userEntity);
        }
        return userEntities;
    }
}
