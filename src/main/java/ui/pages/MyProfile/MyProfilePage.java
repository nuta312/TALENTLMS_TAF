package ui.pages.MyProfile;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.entities.MyProfile.MyProfile;
import io.qameta.allure.Step;
import ui.pages.BasePage;
import static com.codeborne.selenide.Selenide.*;

public class MyProfilePage extends BasePage {

    public SelenideElement profileMenu = $x("//div[@class='profile-menu-button']");
    public SelenideElement myProfile = $x("//p[text()='My profile']");
    public SelenideElement firstName = $x("//input[@id='name']");
    public SelenideElement lastName = $x("//input[@id='surname']");
    public SelenideElement email = $x("//input[@id='email']");
    public SelenideElement bio = $x("//textarea[@id='description']");
    public SelenideElement userName = $x("//input[@id='username']");
    public SelenideElement timeZone = $x("//div[contains(@class,'css-ybqy74-singleValue')]");
    public SelenideElement elementByIndex = $$(".css-7zpd2c-option").get(30); // Получение элемента по индексу
    public SelenideElement FieldLanguage = $("#react-select-2-input");
    public ElementsCollection languages = $$(".css-hdhzfk .css-iovxfg-option");
    public SelenideElement choiceLanguage = languages.get(1);
    public SelenideElement iAgree = $x("//span[contains(text(),'I agree to receive product information and announc')]");

    public MyProfilePage clickMenu() {
        elementActions.click(profileMenu);
        return this;
    }

    public MyProfilePage clickMyProfile() {
        elementActions.click(myProfile);
        return this;
    }

    public MyProfilePage inputFirstname(String text) {
        elementActions.input(firstName, text);
        return this;
    }

    public MyProfilePage inputLastName(String text) {
        elementActions.input(lastName, text);
        return this;
    }

    public MyProfilePage inputEmail(String text) {
        elementActions.input(email, text);
        return this;
    }

    public MyProfilePage inputBio(String text) {
        elementActions.input(bio, text);
        return this;
    }

    public MyProfilePage inputUserName(String text) {
        elementActions.input(userName, text);
        return this;
    }

    public MyProfilePage clickTimeZone() {
        elementActions.click(timeZone);
        return this;
    }

    public MyProfilePage clickTime1() {
        elementActions.click(elementByIndex);
        return this;
    }

    public MyProfilePage clickFieldLanguage() {
        elementActions.click(FieldLanguage);
        return this;
    }

    public MyProfilePage clickLanguage() {
        elementActions.click(choiceLanguage);
        return this;
    }

    public MyProfilePage clickAgree() {
        elementActions.click(iAgree);
        return this;
    }
@Step("Создаем рандомного пользователя")
    public MyProfilePage fillField(MyProfile myProfile) {
        inputFirstname(myProfile.getFirstName())
                .inputLastName(myProfile.getLastName())
                .inputEmail(myProfile.getEmail())
                .inputBio(myProfile.getBio())
                .inputUserName(myProfile.getUserName());
        return this;
    }
}
