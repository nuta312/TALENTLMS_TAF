package ui.pages.user;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class UserTableActions extends BasePage {

    @Step("Select element from Users list")
    public void selectLoginButton() {
        SelenideElement hoverActionsElement = $x("//div[@class='hover-actions']");
        hoverActionsElement.hover();
        SelenideElement loginAsElement = $x("//div[@aria-label='Login as']");
        loginAsElement.shouldBe(Condition.visible);
        elementActions.click(loginAsElement);
    }
}
