package ui.demoqa;


import com.codeborne.selenide.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

//import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class DynamicProperties {

    @Test
    public void demoQaColorChane() {
        Selenide.open("https://demoqa.com/dynamic-properties");
        SelenideElement btnEnableAfter = $(By.id("colorChange"));
        String expectedTExt = "mt-4 text-danger btn btn-primary";
        String text = btnEnableAfter
                .scrollTo()
                .shouldHave(Condition.attribute("class", expectedTExt), Duration.ofSeconds(10))
                .getAttribute("class");
        Assertions.assertEquals(expectedTExt, text);
    }

    @Test
    public void demoQaVisibleAfter() {
        Selenide.open("https://demoqa.com/dynamic-properties");
        SelenideElement btnEnableAfter = $(By.id("visibleAfter"));
        btnEnableAfter
                .scrollTo()
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .click();
    }

    @Test
    public void demoQaUploadFile() {
        Selenide.open("https://demoqa.com/upload-download");
        SelenideElement btnEnableAfter = $(By.id("uploadFile"));
        btnEnableAfter
                .scrollTo()
                .shouldBe(Condition.id("uploadFile"), Duration.ofSeconds(30))
                .sendKeys("D:\\Spring2024\\TALENTLMS_TAF(Selenide)\\src\\main\\java\\ui\\pages\\images\\Godzilla.jpg");
    }

    @Test
    public void demoQaDownloadFile(){
        Configuration.downloadsFolder = "src/main/resources/downloads";
        Configuration.timeout = 10000;
        open("https://demoqa.com/upload-download");
        SelenideElement btnEnableAfter = $(By.id("downloadButton"));
        btnEnableAfter.shouldBe(Condition.id("downloadButton"), Duration.ofSeconds(30));
        btnEnableAfter.click();
    }

    @Test
    public void demoQaGetAllEmployfees(){
        open("https://demoqa.com/webtables");
        ElementsCollection row = $$(".ReactTable .rt-tr-group");
        List<WebTablesDemoQa> allEmployees = new ArrayList<>();
        for (SelenideElement element : row){
            ElementsCollection cells = element.$$(".rt-td");
            if (cells.size() >= 6){

                        String firstName = (cells.get(0).getText());
                        String lastName = (cells.get(1).getText());
                        String ageText = (cells.get(2).getText()).replaceAll("[^0-9]", "");
                        String email = (cells.get(3).getText());
                        String salaryText = (cells.get(4).getText()).replaceAll("[^0-9]", "");
                        String department = (cells.get(5).getText());
                        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || email.isEmpty()
                        || salaryText.isEmpty() || department.isEmpty()){
                            continue;
                        }
                        int age = Integer.parseInt(ageText.trim());
                        long salary = Long.parseLong(salaryText.trim());
                        WebTablesDemoQa employees = WebTablesDemoQa.builder()
                                        .firstName(firstName)
                                                .lastName(lastName)
                                                        .age(age)
                                                                .email(email)
                                                                        .salary(salary)
                                                                                .department(department)
                                                                                        .build();
                allEmployees.add(employees);
            }
        }
        allEmployees.forEach(System.out::println);
    }

}

