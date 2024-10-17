package ui.pages.account_and_settings.portal_settings.locale_section;

import com.codeborne.selenide.SelenideElement;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class SetDateFormatField extends BasePage {
    public SelenideElement dateFormatButton = $x("//div[@data-testid='locale-date-format-select']");
    public SelenideElement dayFirstFormat = $x("//div[@class='custom-option' and text()='DD/MM/YYYY']");
    public SelenideElement monthFirstFormat = $x("//div[@class='custom-option' and text()='MM/DD/YYYY']");
    public SelenideElement yearFirstFormat = $x("//div[@class='custom-option' and text()='YYYY/MM/DD']");

    public void chooseDateFormat() {
        
    }
}
