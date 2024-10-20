package ui.helper;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import common.utils.WaitManager;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

@Slf4j
public class SelenideElementActions {


    public SelenideElementActions click(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(20))
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

    public String getValue(SelenideElement element) {
        element
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldNotBe(empty, Duration.ofSeconds(5));
        return element.getValue();
    }

    public SelenideElementActions selectOption(SelenideElement element, String text) {
        element
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldNotBe(empty, Duration.ofSeconds(5))
                .scrollTo()
                .selectOption(text);
        return this;
    }

    public SelenideElementActions pressEnter(SelenideElement element) {
        element.shouldBe(visible).pressEnter();
        return this;
    }


    public SelenideElementActions pressTab(SelenideElement element) {
        element.shouldBe(visible).pressTab();
        return this;
    }

    public SelenideElementActions pause(int milliSeconds) {
        WaitManager.pauseInSeconds(milliSeconds);
        return this;
    }

    public boolean isElementShouldBeVisible(SelenideElement element, int timeoutInSeconds) {
        try {
            element.shouldBe(visible, Duration.ofSeconds(timeoutInSeconds));
            return element.isDisplayed();
        } catch (AssertionError e) {
            log.error("Element {} should be visible in {} sec", element, timeoutInSeconds);
            return false;
        }
    }

    public boolean isElementShouldNotBeVisible(SelenideElement element, int timeoutInSeconds) {
        try {
            element.shouldBe(visible, Duration.ofSeconds(timeoutInSeconds));
            return element.isDisplayed();
        } catch (AssertionError e) {
            log.error("Element {} should be visible in {} sec", element, timeoutInSeconds);
            return false;
        }
    }
}
