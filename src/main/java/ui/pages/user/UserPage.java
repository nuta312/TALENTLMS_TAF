package ui.pages.user;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.User;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class UserPage {
    public ElementsCollection rowsTable = $$x("//div[@data-testid='table']/table/tbody/tr");
  //  public ElementsCollection cellsTable = $$x("//div[@data-testid='table']/table/tbody/tr/td[2]//div[@data-testid='text-component']");

    public List<User> getAllUsersFromTable(){
        List<User> allusers = new ArrayList<>();
        for (SelenideElement row : rowsTable){
            //ElementsCollection cells =  row.$$x("//div[@data-testid='text-component']");
            String userName = row.$("//td[2]/div/div/div/span/div").getText();
            allusers.add(new User(userName));
        }
        return allusers;
    }
}
