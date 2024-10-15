package ui.pages.instructor_dashboard;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ui.pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class InstructorDashboardPage extends BasePage {

    public SelenideElement instructorText = $x("//div[text()='Instructor']");
    public SelenideElement instructor = $x("//label[@for='role-instructor']");
    public String getInstructorText() {
        return elementActions.getText(instructorText);
    }

    @Step("Кликаем на вкладку инструктора на странице")
    public void clickInstructor() {
      instructor.shouldBe(visible).click();
    }

    @Step("Проверяем, что текст инструктора равен 'Instructor'")
    public void verifyInstructorText() {
        String expectedText = "Instructor";
        Assertions.assertEquals(expectedText, getInstructorText(),
                "Текст инструктора не совпадает");
    }
}
