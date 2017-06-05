import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestBase {

    private static final String BASE_URL = "https://google.com/";
    private static final Logger LOGGER = Logger.getLogger(TestBase.class.getName());
    private static final Properties ENV = new Properties();

    @BeforeSuite
    public static void setVariables() {
        Configuration.timeout = 15000L;
        Configuration.baseUrl = BASE_URL;
        Configuration.startMaximized = true;
        Configuration.fastSetValue = true;
        Configuration.savePageSource = false;
        Configuration.browser = "marionette";
        HasCapabilities remote = (HasCapabilities) getWebDriver();
        ENV.setProperty("Url", Configuration.baseUrl);
        ENV.setProperty("Browser", remote.getCapabilities().getBrowserName() + " v." + remote.getCapabilities().getVersion());
        ENV.setProperty("Platform", remote.getCapabilities().getPlatform().name());
        ENV.setProperty("Platform version", remote.getCapabilities().getPlatform().getMajorVersion() + "." +
                remote.getCapabilities().getPlatform().getMinorVersion());
        LOGGER.setLevel(Level.CONFIG);
    }

    @BeforeMethod(alwaysRun = true)
    public static void setup() {
        RemoteWebDriver remote = (RemoteWebDriver) getWebDriver();
        WebDriverRunner.setWebDriver(remote);
        remote.manage().window().maximize();
        open(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public static void tearDown() {
        WebDriverRunner.closeWebDriver();
    }

    @AfterSuite
    public static void saveEnvironment() {

    }
}