package ui_tests.groups;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.pages.admin_dashboard.AdminDashboardPage;
import ui.pages.auth.LoginPage;
import ui.pages.groups.AddGroupPage;

import static com.codeborne.selenide.Selenide.open;
import static common.config_reader.ConfigurationManager.getAppConfig;
import static common.config_reader.ConfigurationManager.getCredentials;

public class AddGroupTest {

    public LoginPage loginPage = new LoginPage();
    public AddGroupPage addGroupPage = new AddGroupPage();

    @Test
    void testAddGroup() {
        open(getAppConfig().base_url());
        loginPage.doLogin(getCredentials().adminUsername(), getCredentials().adminPassword());

        String groupName = "QA Automation";
        String description = "This is a group for Qa automation engineer";
        String price = "100";

        addGroupPage.addGroup(groupName, description, price);

        Assertions.assertEquals(groupName, addGroupPage.groupNameInput.getValue());
        Assertions.assertEquals(description, addGroupPage.descriptionInput.getValue());
        Assertions.assertEquals(price, addGroupPage.priceInput.getValue());

    }
}
