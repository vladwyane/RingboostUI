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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by bigdrop on 3/14/2019.
 */
public class ApplicationManager {

    private WebDriver driver;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setup() {
        if (browser.equals(BrowserType.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
        } else if (browser.equals(BrowserType.GOOGLECHROME)) {
            WebDriverManager.chromedriver();
        } else if (browser.equals(BrowserType.IE)) {
            WebDriverManager.iedriver().setup();
        } else if (browser.equals(BrowserType.EDGE)) {
            WebDriverManager.edgedriver().setup();
        }
    }

    public WebDriver unit() throws MalformedURLException {
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.GOOGLECHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        } else if (browser.equals(BrowserType.EDGE)) {
            driver = new EdgeDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("imp.wait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        return driver;
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

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void delleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
