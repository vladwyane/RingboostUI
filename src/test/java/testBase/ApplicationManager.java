package testBase;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by bigdrop on 3/14/2019.
 */
public class ApplicationManager {

    private WebDriver driver;
    private String browser;

    ApplicationManager(String browser) {
        this.browser = browser;
    }

    public WebDriver getDriver() {
        return driver;
    }

    void setup() {
        switch (browser) {
            case BrowserType.FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
            case BrowserType.CHROME:
                WebDriverManager.chromedriver().version("2.46").setup();
                break;
            case BrowserType.IE:
                WebDriverManager.iedriver().setup();
                break;
            case BrowserType.EDGE:
                WebDriverManager.edgedriver().setup();
                break;
        }
    }

    void unit() {
        switch (browser) {
            case BrowserType.FIREFOX:
                driver = new FirefoxDriver();
                break;
            case BrowserType.CHROME:
                driver = new ChromeDriver();
                break;
            case BrowserType.IE:
                driver = new InternetExplorerDriver();
                break;
            case BrowserType.EDGE:
                driver = new EdgeDriver();
                break;
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("imp.wait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void startRemoteDriver() throws Exception {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("70.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        driver = new RemoteWebDriver(new URL("http://10.0.1.200:4444/wd/hub/"), capabilities);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void delleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
