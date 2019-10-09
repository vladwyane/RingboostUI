package pages;

import blocks.HeaderBlock;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

/**
 * Created by bigdrop on 3/14/2019.
 */
public abstract class BasePage {

    protected static final double fixedPromocode = 5.0;
    protected static final double highFixedPromocode = 4000.0;
    protected static final double percentPromocode = 10.0;

    protected HeaderBlock headerBlock;
    protected WebDriver driver;

    protected SoftAssert softAssert = new SoftAssert();

    public BasePage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    public abstract void open();

    @FindBy(css = ".load-more")
    WebElement buttonMoreNumbers;

    @FindBy(css = "h1")
    WebElement titleH1;

    public void checkingCorrectlyHeadingH1(String headingH1) {
        waitUntilElementAppeared(titleH1);
        softAssert.assertEquals(titleH1.getText(), headingH1);
        softAssert.assertAll();
    }

    protected void type(TextInput webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected void sendKeysSlowly(final WebElement element, final String keys) throws InterruptedException {
        for (int i = 0; i < keys.length(); i++){
            element.sendKeys(Character.toString(keys.charAt(i)));
            Thread.sleep(50);
        }
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void waiting2seconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean isElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitUntilTextInElementAppear(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitUntilElementAppeared(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitUntilElementWillBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean scrollToElement(WebElement element) {

        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        try {
            ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
            Thread.sleep(500);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementContainsAttributeValue(WebElement element, String attribute, String attributeValue) {
        return element.getAttribute(attribute).contains(attributeValue);
    }

    protected void changeAttributeValueWithJS(WebElement element, String attribute, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", element);

    }

    protected String getNumbersFromString(String value) {
        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pat.matcher(value);
        while (matcher.find()) {
            return (matcher.group());
        }

        return (matcher.group());
    }

    public void clickSubNavItemTollFree (String nameOfItem) {
        hoverElementUsingJS(headerBlock.getTollFreeLinInMainNav());
        waitUntilElementWillBeClickable(headerBlock.getListSubMenuTollFree().get(0));
        headerBlock.chooseItemFromSubMenuTollFree(nameOfItem).click();
    }

    protected boolean hoverElementUsingJS(WebElement element) {
        String strJavaScript = "var element = arguments[0]; var mouseEventObj = document.createEvent('MouseEvents'); mouseEventObj.initEvent( 'mouseover', true, true ); element.dispatchEvent(mouseEventObj);";

        try {
            ((JavascriptExecutor) driver).executeScript(strJavaScript, element);
            Thread.sleep(500);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}