package ui.pages;
import ui.helper.SelenideElementActions;
import lombok.Getter;

public abstract class BasePage {
    @Getter
    public SelenideElementActions elementActions = new SelenideElementActions();
}
