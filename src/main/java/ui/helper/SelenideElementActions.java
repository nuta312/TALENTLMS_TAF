package ui.helper;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideElementActions {

    public SelenideElementActions click(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(10))
                .scrollTo()
                .click();
        return this;
    }

    public SelenideElementActions input(SelenideElement element, String text) {
        element
                .shouldBe(visible)
                .scrollTo()
                .sendKeys(text);
        return this;
    }

    public String getText(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldNotBe(empty, Duration.ofSeconds(5));
        return element.getText();
    }

    public void refreshPage() {
        Selenide.refresh();
    }

    public SelenideElementActions clickElementWithJsExecutor(SelenideElement element) {
        Selenide.executeJavaScript("arguments[0].click();", element);
        return this;
    }

    public SelenideElementActions clickWithoutScroll(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(10))
                .click();
        return this;
    }

    public void replaceText(SelenideElement element, String newText) {
        element.setValue(newText);
    }

    public void addFile(SelenideElement element, String filePath) {
        File file = new File(filePath);
        element.uploadFile(file);
    }

    public void navigateToElement(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(10))
                .hover();
    }
}