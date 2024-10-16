package ui.pages.lerner_dashboard;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ui.pages.BasePage;
import static com.codeborne.selenide.Selenide.$x;

public class LearnerDashboardPage extends BasePage {
    public SelenideElement learnerText = $x("//div[text()='Learner']");
    public SelenideElement learner = $x("//label[@for='role-learner']");

    public String getLearnerText() {
        return elementActions.getText(learnerText);
    }

    @Step("Кликаем на вкладку ученика на странице")
    public void clickLearner() {
        elementActions.click(learner);

    }

    @Step("Проверяем, что текст инструктора равен 'Learner'")
    public void verifyLearnerText() {
        String expectedText = "Learner";
        Assertions.assertEquals(expectedText, getLearnerText(),
                "Текст ученика не совпадает");

    }

}
