package ui.pages.users;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class UsersPage extends BasePage {
    public SelenideElement users = $(By.id("users"));
    public SelenideElement enrollToCourse = $x("//button[@class='right-actions-item linkButton icon-before css-j2mmvp']");
    public SelenideElement selectOneCourse = $x("(//div[@aria-label='Enroll to course'])[4]");
    public SelenideElement loginAsUser = $x("(//button[@aria-label='Login as'])[3]");
    public SelenideElement selectOneUser = $x("//div[text()='R. Cremin']");
    public SelenideElement resumeCourse = $x("//h2[@class='card-title css-h8408c']");
    public SelenideElement completeCourse = $x("//button[@class='link-button solid css-1cv4mj0']");
    public SelenideElement backToCourse = $x("//button[@class='solid css-13i1ii0']");
    public SelenideElement backToAdmin = $x("//button[@data-testid='topbar-impersonation-back']");
    public SelenideElement reports = $(By.id("reports"));
    public SelenideElement userReports = $(By.id("userReports"));
    public SelenideElement selectCourses = $x("//div[@id='tab-1' and text()='Courses']");
    public SelenideElement statusOfCourse = $x("//span[text()='Completed']");
    public SelenideElement fileTitle = $x("//div[text()='Изображение WhatsApp 2024-05-09 в 19.25.37_802a4977.jpg']");

    public void selectUser(){
        elementActions.clickWithoutScroll(users);
        elementActions.click(selectOneUser);
    }

    public void enrollUserToCourse(){
        elementActions.clickWithoutScroll(enrollToCourse);
        elementActions.click(selectOneCourse);
        elementActions.clickWithoutScroll(users);
    }

    public void loginActions(){
        elementActions.navigateToElement(selectOneUser);
        elementActions.click(loginAsUser);
        elementActions.clickWithoutScroll(resumeCourse);
        elementActions.click(completeCourse);
        elementActions.click(backToCourse);
        elementActions.click(backToAdmin);
    }

    public void getReports(){
        elementActions.click(reports);
        elementActions.clickWithoutScroll(userReports);
        elementActions.click(selectOneUser);
        elementActions.clickWithoutScroll(selectCourses);
    }

    @Step("Get file text")
    public String getFileText() {
        return elementActions.getText(fileTitle);
    }

    @Step("Get course status text")
    public String getCourseStatusText() {
        return elementActions.getText(statusOfCourse);
    }
}