package ui.pages.user;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.User;
import io.qameta.allure.Step;
import ui.pages.BasePage;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class UserPage extends BasePage {
    private SelenideElement tableUsers = $("[data-testid='table']");
    private SelenideElement deleteUser = $x("//h2[text()='Delete user']");
    private SelenideElement changeUserSuccessfully = $x("//p[normalize-space()='Changes saved successfully']");
    private SelenideElement editUserBtn = $x("//span[contains(text(), 'Edit user')]");
    private SelenideElement loginAsBtn = $x("//h2[text()='Recent course activity']");

    @Step("Get all users from table")
    public List<User> getUserFromTable(){
        List<User> allUsers = new ArrayList<>();
        ElementsCollection rowsTable = tableUsers.$$("tr");
        for (SelenideElement row : rowsTable){
            ElementsCollection cells = row.$$("td");
            if (cells.size() >= 6) {
                User user = User.builder()
                        .userName(cells.get(1).getText())
                        .email(cells.get(2).getText())
                        .type(cells.get(3).getText())
                        .registration(cells.get(4).getText())
                        .lastLogin(cells.get(5).getText())
                        .build();
                allUsers.add(user);
            } else {
                System.out.println("Неверное количество ячеек в строке: " + cells.size());
            }
        }
        return allUsers;
    }

    @Step("Get delete text")
    public String getDeleteText(){
        return elementActions.getText(deleteUser);
    }

    @Step("Get change user successfully text")
    public String getChangeUserText(){
        return elementActions.getText(changeUserSuccessfully);
    }

    @Step("Get after click btn cancel text")
    public String getAfterClickCancelText(){
        return elementActions.getText(editUserBtn);
    }

    @Step("Get text after click login as")
    public String getLoginAsText(){
        return elementActions.getText(loginAsBtn);
    }

    @Step("Update user {0} {1} {2}")
    public void updateUser(String value, String action, int index){
        SelenideElement userName = $x("//div[@data-testid='text-component' and contains(text(), '" + value + "')]");
        SelenideElement hoverElementAction = $x("(//div[@role='tooltip' and contains(@aria-label, '" + action + "')])["+index+"]");
        elementActions
                .navigateElement(userName)
                .click(hoverElementAction);
    }

    @Step("Update user {0} {1} {2}")
    public void deleteUser(String value, String action, int index){
        SelenideElement hoverElementAction = $x("(//div[@role='tooltip' and contains(@aria-label, '" + action + "')])[2]");
        ElementsCollection row = tableUsers.$$("tr");
        for (SelenideElement element : row) {
            ElementsCollection cells = element.$$("td");
            if (cells.get(1).getText().equals(value)) {
                elementActions
                        .navigateElement(cells.get(1))
                        .click(hoverElementAction);
            }
        }
    }
}


