package ui_tests;

import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static common.config_reader.ConfigurationManager.getAppConfig;

public class BaseUiTest {

    @BeforeAll
    public static void beforeAll() {{
            browserSize = "1920x1080";
            headless = true;
            holdBrowserOpen = false;
            if (getAppConfig().remote()) {
                var currentPort = System.getProperty("CURRENT_PORT");
                remote = getAppConfig().dockerUrl().replace("{port}", currentPort);
                holdBrowserOpen = false;
            }
        }
    }
}
