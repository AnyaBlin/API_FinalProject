package cloud.autotests.helpers;

import cloud.autotests.config.Project;
import cloud.autotests.config.ProjectConfig;
import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class DriverSettings {

    public static ProjectConfig config =
            ConfigFactory.create(ProjectConfig.class);

    private static String login = config.login();
    private static String password = config.password();
    private static String selenoidURL = System.getProperty("remoteBrowser");

   // private static String remoteURL = format("https://%s:%s@%s", login, password, selenoidURL);

    public static void configure() {
        Configuration.browser = Project.config.browser();
        Configuration.browserVersion = Project.config.browserVersion();
        Configuration.browserSize = Project.config.browserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--lang=en-en");

        if (Project.isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            //Configuration.remote = format("https://%s:%s@%s", login, password, selenoidURL);
            Configuration.remote = Project.config.remoteBrowser();
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;
    }
}
