package ui.pages.account_and_settings.portal_settings.branding_section;

import com.codeborne.selenide.SelenideElement;
import ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class BrandingFields extends BasePage {

    public SelenideElement logoUploadButton = $x("//div[@class='buttons-wrapper']");
    public SelenideElement logoUploadField = $x("//div[@data-testid='branding-logo']");
    public SelenideElement faviconUploadField = $x("//div[@data-testid='branding-favicon']");
    public SelenideElement faviconUploadButton = $x("//div[@class='buttons-wrapper']");
    public SelenideElement customHomepageButton = $x("//button[@aria-label='Toggle sidebar']");
}
