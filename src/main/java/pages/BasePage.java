package pages;

import blocks.front.HeaderBlock;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.List;
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
    protected WebElement buttonMoreNumbers;

    @FindBy(css = "h1")
    protected WebElement titleH1;

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void checkingCorrectlyHeadingH1(String headingH1) {
        waitUntilElementAppeared(titleH1);
        softAssert.assertEquals(titleH1.getText(), headingH1);
        softAssert.assertAll();
    }

    protected String getAttributeValue(WebElement element) {
        String value = null;
        try {
            return value = element.getAttribute("value");
        } catch (NoSuchElementException e) {
            return value;
        }
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

    protected void waiting5seconds() {
        try {
            Thread.sleep(5000);
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
        WebDriverWait wait = new WebDriverWait(driver, 15);
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

    protected void waitUntilPageLoadComplete() {
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
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

    @Step("Click SubNav Item TollFree: {0}")
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

    protected void chooseElementFromSelectInAdminPanel(WebElement select, List<WebElement> listElements, String value) {
        waitUntilElementAppeared(select);
        select.click();
        waiting2seconds();
        for(WebElement element : listElements) {
            if (element.getText().toLowerCase().equals(value.toLowerCase())) {
                element.click();
                return;
            }
        }
        listElements.get(0).click();
    }

    protected void choosePeriodFromDatePickerInAdminPanel(WebElement datepicker, List<WebElement> listOfDatesCurrentMonth, String dateStart, String dateFinish) {
        waitUntilElementAppeared(datepicker);
        datepicker.click();
        waiting2seconds();
        for(WebElement element : listOfDatesCurrentMonth) {
            if (element.getText().toLowerCase().equals(dateStart.toLowerCase())) {
                element.click();
                break;
            }
        }
        for(WebElement element : listOfDatesCurrentMonth) {
            if (element.getText().toLowerCase().equals(dateFinish.toLowerCase())) {
                element.click();
                break;
            }
        }
    }

}