package pages;

import blocks.Header;
import blocks.SearchBlock;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by bigdrop on 3/14/2019.
 */
public abstract class BasePage {

    protected Header header;
    protected WebDriver driver;

    protected SoftAssert softAssert = new SoftAssert();

    public BasePage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    public abstract void open();

    protected void type(TextInput webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
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
        WebDriverWait wait = new WebDriverWait(driver, 0);
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

    protected boolean scrollToElement(WebElement element) throws InterruptedException {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementContainsAttributeValue(WebElement element, String attribute, String attributeValue) {
        if (element.getAttribute(attribute).contains(attributeValue) == true)
            return true;
        else return false;
    }

    protected void changeAttributeValueWithJS(String elementID, String attribute, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + elementID + "').setAttribute('" + attribute + "', '" + value + "')");
    }

    public void clickSubNavItemTollFree (String nameOfItem) {
        Actions action = new Actions(driver);
        waitUntilElementWillBeClickable(header.getTollFreeLinInMainNav());
        action.moveToElement(header.getTollFreeLinInMainNav()).build().perform();
        waitUntilElementWillBeClickable(header.getListSubMenuTollFree().get(0));
        header.chooseItemFromSubMenuTollFree(nameOfItem).click();
    }

}