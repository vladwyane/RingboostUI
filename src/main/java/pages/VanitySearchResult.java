package pages;

import blocks.RegularVanityBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

/**
 * Created by bigdrop on 5/15/2019.
 */
public class VanitySearchResult extends BasePage {

    RegularVanityBlock regularVanityBlock;

    public VanitySearchResult(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    @FindBy(css = ".load-more")
    private WebElement buttonMoreNumbers;

    public void checkingClickLoadMore () {
        int totalNumberBefore;
        int totalNumberAfter;
        waitUntilTextInElementAppear(regularVanityBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        totalNumberBefore = regularVanityBlock.getListRegularVanityNumbers().size();
        buttonMoreNumbers.click();
        waiting2seconds();
        totalNumberAfter = regularVanityBlock.getListRegularVanityNumbers().size();
        softAssert.assertTrue(totalNumberAfter > totalNumberBefore, "New numbers is not loaded");
        softAssert.assertAll();
    }

    public void checkingLoadMoreIfAllNumbersIsLoaded() {
        waitUntilTextInElementAppear(regularVanityBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        while(regularVanityBlock.getListRegularVanityNumbers().size() % 32 == 0) {
            buttonMoreNumbers.click();
            waiting2seconds();
        }
        softAssert.assertFalse(isElementPresent(buttonMoreNumbers), "Load More is still present");
        softAssert.assertAll();

    }
}
