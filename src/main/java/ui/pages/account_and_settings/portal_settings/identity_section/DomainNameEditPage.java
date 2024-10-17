package ui.pages.account_and_settings.portal_settings.identity_section;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DomainNameEditPage extends BasePage {
    public SelenideElement domainEditButton = $x("//button[@data-testid='identity-domain-name']");
    public SelenideElement domainEditField = $(By.id("//input[@id='host']"));
    public SelenideElement domainEditSaveBtn = $x("//button[@type='submit']//span[@class='btn-text'][normalize-space()='Save']");
}
