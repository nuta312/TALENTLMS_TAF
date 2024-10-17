package ui.pages.course_store;
import com.codeborne.selenide.SelenideElement;
import ui.pages.BasePage;
import static com.codeborne.selenide.Selenide.$x;

public class MainStore extends BasePage {
    public SelenideElement cartoonName = $x("//h2[text() = 'Introduction to Business Emergency Preparedness Planning']");
    public SelenideElement cartoonSpanishName = $x("//h2[contains(text(), 'Los valores clave de igualdad, diversidad e inclusión')]");

    public String getCartoonText() {
        return elementActions.getText(cartoonName);
    }

    public String getCartoonSpanishText() {
        return elementActions.getText(cartoonSpanishName);
    }
}
