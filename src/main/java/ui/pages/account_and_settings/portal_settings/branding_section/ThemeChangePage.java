package ui.pages.account_and_settings.portal_settings.branding_section;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.pages.BasePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ThemeChangePage extends BasePage {
    public SelenideElement themeChangeButton = $x("//button[@data-testid='branding-theme']");
    public List <SelenideElement> themes = $$("article[data-testid='card']").shouldHave(CollectionCondition.sizeGreaterThan(0)).stream().toList();
    public SelenideElement addThemeButton = $("#primaryColor");
    public SelenideElement primaryColor = $("#name");
    public SelenideElement newThemeSaveBtn = $x("//span[normalize-space()='Save']");
    public SelenideElement newThemeCancelBtn = $x("//span[normalize-space()='Cancel']");
    public SelenideElement setAsActiveBtn = $x("//span[normalize-space()='Set as active']");
    public SelenideElement cancelBtn = $x("//span[normalize-space()='Cancel']");


    public void previewTheme(String desiredThemeName) {
        for (int i = 0; i < themes.size(); i++) {
            SelenideElement themeNameElement = themes.get(i).$("span[data-testid='text-component']");
            String themeName = themeNameElement.getText();

            if (themeName.equals(desiredThemeName)) {
                SelenideElement previewButton = $x(String.format("(//article[%d])/descendant::button[@data-testid='action-button']", i));

                elementActions.click(previewButton);
                break;
            }
        }
    }
}
