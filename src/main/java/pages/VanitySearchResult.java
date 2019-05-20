package pages;

import blocks.RegularVanityNumbersBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bigdrop on 5/15/2019.
 */
public class VanitySearchResult extends BasePage {

    private RegularVanityNumbersBlock regularVanityNumbersBlock;

    public VanitySearchResult(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void checkingClickLoadMore () {
        int totalNumberBefore;
        int totalNumberAfter;
        waitUntilTextInElementAppear(regularVanityNumbersBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        totalNumberBefore = regularVanityNumbersBlock.getListRegularVanityNumbers().size();
        buttonMoreNumbers.click();
        waiting2seconds();
        totalNumberAfter = regularVanityNumbersBlock.getListRegularVanityNumbers().size();
        softAssert.assertTrue(totalNumberAfter > totalNumberBefore, "New numbers is not loaded");
        softAssert.assertAll();
    }

    public void checkingLoadMoreIfAllNumbersIsLoaded() {
        waitUntilTextInElementAppear(regularVanityNumbersBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        while(regularVanityNumbersBlock.getListRegularVanityNumbers().size() % 32 == 0) {
            int counter = 0;
            waitUntilElementWillBeClickable(buttonMoreNumbers);
            buttonMoreNumbers.click();
            waiting2seconds();
            counter++;
            if (counter == 5)
                break;
        }
        softAssert.assertFalse(isElementPresent(buttonMoreNumbers), "Load More is still present");
        softAssert.assertAll();
    }

    public void chooseFirstNumberFromRegularVanityList() {
        waitUntilTextInElementAppear(regularVanityNumbersBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        regularVanityNumbersBlock.getListRegularVanityNumbers().get(0).click();
    }

    public void chooseLastNumberFromRegularVanityListAfterLoadMore() {
        waitUntilTextInElementAppear(regularVanityNumbersBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        while(regularVanityNumbersBlock.getListRegularVanityNumbers().size() % 32 == 0) {
            waitUntilElementWillBeClickable(buttonMoreNumbers);
            buttonMoreNumbers.click();
            waiting2seconds();
        }
        regularVanityNumbersBlock.getListRegularVanityNumbers().get(regularVanityNumbersBlock.getListRegularVanityNumbers().size() - 1).click();
    }

    public void choose32thNumberFromRegularVanityList() {
        waitUntilTextInElementAppear(regularVanityNumbersBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        regularVanityNumbersBlock.getListRegularVanityNumbers().get(regularVanityNumbersBlock.getListRegularVanityNumbers().size() - 1).click();
    }


}
