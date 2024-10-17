package ui.pages.account_and_settings.portal_settings.identity_section;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PortalPage extends BasePage {

    public SelenideElement siteName = $(By.id("site_name"));
    public SelenideElement siteDescription = $(By.id("site_description"));
    public SelenideElement customDomain = $x("//button[@data-testid='identity-custom-domain']");


}
