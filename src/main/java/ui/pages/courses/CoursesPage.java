package ui.pages.courses;

import com.codeborne.selenide.SelenideElement;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CoursesPage extends BasePage {

    public SelenideElement courses = $x("//a[@aria-label='Courses']");
    public SelenideElement addCourse = $x("//button[@class='start-button solid css-j2mmvp']");
    public SelenideElement courseName = $x("//div[@data-text='Title']");
    public SelenideElement fileInput = $("input[type='file']");
    public SelenideElement publishCourse = $x("//button[@class='solid css-1cv4mj0']");

    public void selectCoursesOptionAndAddCourse(){
        elementActions.click(courses);
        elementActions.clickWithoutScroll(addCourse);
    }

    public void createCourse(){
        String filePath = "C:\\Users\\ASUS\\Pictures\\Изображение WhatsApp 2024-05-09 в 19.25.37_802a4977.jpg";
        elementActions.replaceText(courseName, "Demo Selenide Course");
        elementActions.addFile(fileInput, filePath);
        elementActions.click(publishCourse);
    }
}