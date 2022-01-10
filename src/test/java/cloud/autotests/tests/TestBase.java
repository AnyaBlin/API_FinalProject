package cloud.autotests.tests;

import cloud.autotests.config.Project;
import cloud.autotests.helpers.AllureAttachments;
import cloud.autotests.helpers.DriverSettings;
import cloud.autotests.helpers.DriverUtils;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({AllureJunit5.class})
public class TestBase {
    String nameObject1 = "ВТБ Капитал";
    String nameObject2 = "ВТБ Страхование";
    String nameObject3 = "ВТБ Факторинг";
    String checkedElement1 = "Онлайн-сервисы";
    String checkedElement2 = "Отделения и Банкоматы";
    String checkedElement3 = "English";
    String checkedText1 = "ВТБ Капитал предлагает полный спектр инвестиционно-банковских продуктов и услуг как российским";
    String checkedText2 = "Почему именно ВТБ Страхование";
    String checkedText3 = "ВТБ Факторинг";
    String checkedText4 = "Careers";

    String URL = "https://www.vtb.ru/";

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }
}
