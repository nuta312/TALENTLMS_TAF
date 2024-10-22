package ui.demoqa;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.junit.jupiter.api.Test;


import java.io.File;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static java.nio.channels.Selector.open;


public class DynamicProperties {

    @Test
    public void demoQaColorChane() {

        Selenide.open("https://demoqa.com/upload-download");
        String path = "src/main/java/ui/pages/images/Godzilla.jpg";
        SelenideElement uploadFileInput = $("//*[@id=\"uploadFile\"]");
        uploadFileInput.uploadFile(new File(path));
    }

//    @Test
//    public void demoQaGetAllEmployees() {
//        Selenide.open("https://demoqa.com/webtables");
//        ElementsCollection row = $$x("//*[@id=\"app\"]/div/div/div/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]");
//        List<WebTablesDemoQa> rows = DriverManager.getDriver().findElements(By.cssSelector(".ReactTable .rt-tr-group")); //10
//
//        // Создаем пустой список для хранения объектов EmployeeEntity
//        ArrayList<EmployeeEntity> employeeEntities = new ArrayList<>();
//
//        // Перебираем каждую ячейку таблицы
//        for (WebElement row : rows) {
//            // Получаем все ячейки (cells) текущей строки с помощью селектора CSS
//            List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));
//
//    }
}

