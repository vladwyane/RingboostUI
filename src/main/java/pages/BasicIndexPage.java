package pages;

import blocks.Basic800NumbersBlock;
import org.openqa.selenium.WebDriver;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 6/19/2019.
 */
public class BasicIndexPage extends BasePage {

    public BasicIndexPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("basicIndex.url"));
    }

    private Basic800NumbersBlock basic800NumbersBlock;

    public void chooseFirstNumberFromBasic800List() {
        waitUntilElementAppeared(basic800NumbersBlock.listAvailableBasic800Numbers.get(0));
        basic800NumbersBlock.listAvailableBasic800Numbers.get(0).click();
    }

    public void chooseLastNumberFromBasic800ListAfterLoadMore() {
        waitUntilElementAppeared(basic800NumbersBlock.listBasic800Numbers.get(0));
        int counter = 0;
        while(basic800NumbersBlock.listBasic800Numbers.size() % 32 == 0) {
            waitUntilElementWillBeClickable(buttonMoreNumbers);
            buttonMoreNumbers.click();
            waiting2seconds();
            counter++;
            if (counter == 5)
                break;
        }
        scrollToElement(basic800NumbersBlock.listBasic800Numbers.get(basic800NumbersBlock.listBasic800Numbers.size() - 1));
        basic800NumbersBlock.listBasic800Numbers.get(basic800NumbersBlock.listBasic800Numbers.size() - 1).click();
    }

    public void chooseFirstNumberFromBasic800ListAfterLoadMore() {
        waitUntilElementAppeared(basic800NumbersBlock.listBasic800Numbers.get(0));
        int counter = 0;
        while(basic800NumbersBlock.listBasic800Numbers.size() % 32 == 0) {
            waitUntilElementWillBeClickable(buttonMoreNumbers);
            buttonMoreNumbers.click();
            waiting2seconds();
            counter++;
            if (counter == 1)
                break;
        }
        scrollToElement(basic800NumbersBlock.listBasic800Numbers.get(0));
        basic800NumbersBlock.listBasic800Numbers.get(0).click();
    }

    public void choose32thNumberFromBasic800List() {
        waitUntilElementAppeared(basic800NumbersBlock.listBasic800Numbers.get(0));
        scrollToElement(basic800NumbersBlock.listBasic800Numbers.get(basic800NumbersBlock.listBasic800Numbers.size() - 1));
        basic800NumbersBlock.listBasic800Numbers.get(basic800NumbersBlock.listBasic800Numbers.size() - 1).click();
    }


}
