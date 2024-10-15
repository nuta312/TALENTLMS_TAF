package ui.pages.groups;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;


import static com.codeborne.selenide.Selenide.$;

public class AddGroupPage extends BasePage {


    public SelenideElement groupsTab = $(By.xpath("//*[@id=\"groups\"]/div/div/a/div/div[2]"));
    public SelenideElement addGroupBtn = $(By.xpath("//span[text()='Add group']"));
    public SelenideElement groupNameInput = $(By.xpath("//input[@data-testid='name-input']"));
    public SelenideElement descriptionInput = $(By.xpath("//textarea[@id='description']"));
    public SelenideElement priceInput = $(By.xpath("//input[@id='price']"));
    public SelenideElement saveBtn = $(By.xpath("//span[@class='btn-text' and text()='Save']"));

    public AddGroupPage addGroup(String groupName, String description, String price) {
        elementActions.click(groupsTab)
                .click(addGroupBtn)
                .input(groupNameInput, groupName)
                .input(descriptionInput, description)
                .input(priceInput, price)
                .click(saveBtn);
        return new AddGroupPage();
    }
}
