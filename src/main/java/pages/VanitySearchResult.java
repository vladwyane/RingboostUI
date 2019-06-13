package pages;

import blocks.PremiumVanityNumbersBlock;
import blocks.RegularVanityNumbersBlock;
import blocks.SmallSearchBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 5/15/2019.
 */
public class VanitySearchResult extends BasePage {

    private RegularVanityNumbersBlock regularVanityNumbersBlock;
    private PremiumVanityNumbersBlock premiumVanityNumbersBlock;
    private SmallSearchBlock smallSearchBlock;

    public VanitySearchResult(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("searchResults.url"));
    }

    public void searchTollFreeNumbers(String request) {
        waitUntilTextInElementAppear(smallSearchBlock.getTitleH1(), "Toll-Free Vanity Numbers");
        type(smallSearchBlock.getTollFreeSearchField(), request);
        smallSearchBlock.getButtonFindNumber().click();
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
        int counter = 0;
        while(regularVanityNumbersBlock.getListRegularVanityNumbers().size() % 32 == 0) {
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
        int counter = 0;
        while(regularVanityNumbersBlock.getListRegularVanityNumbers().size() % 32 == 0) {
            waitUntilElementWillBeClickable(buttonMoreNumbers);
            buttonMoreNumbers.click();
            waiting2seconds();
            counter++;
            if (counter == 5)
                break;
        }
        scrollToElement(regularVanityNumbersBlock.getListRegularVanityNumbers().get(regularVanityNumbersBlock.getListRegularVanityNumbers().size() - 1));
        regularVanityNumbersBlock.getListRegularVanityNumbers().get(regularVanityNumbersBlock.getListRegularVanityNumbers().size() - 1).click();
    }

    public void chooseFirstNumberFromRegularVanityListAfterLoadMore() {
        waitUntilTextInElementAppear(regularVanityNumbersBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        int counter = 0;
        while(regularVanityNumbersBlock.getListRegularVanityNumbers().size() % 32 == 0) {
            waitUntilElementWillBeClickable(buttonMoreNumbers);
            buttonMoreNumbers.click();
            waiting2seconds();
            counter++;
            if (counter == 1)
                break;
        }
        scrollToElement(regularVanityNumbersBlock.getListRegularVanityNumbers().get(0));
        regularVanityNumbersBlock.getListRegularVanityNumbers().get(0).click();
    }

    public void choose32thNumberFromRegularVanityList() {
        waitUntilTextInElementAppear(regularVanityNumbersBlock.getTitleSection(), "The Following Related Vanity Numbers are Available for");
        scrollToElement(regularVanityNumbersBlock.getListRegularVanityNumbers().get(regularVanityNumbersBlock.getListRegularVanityNumbers().size() - 1));
        regularVanityNumbersBlock.getListRegularVanityNumbers().get(regularVanityNumbersBlock.getListRegularVanityNumbers().size() - 1).click();
    }

    public void chooseFirstNumberFromPremiumVanityList() {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(0).click();
    }

    public void chooseLastNumberFromPremiumVanityList() {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(premiumVanityNumbersBlock.getListPremiumVanityNumbers().size() - 1));
        premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(premiumVanityNumbersBlock.getListPremiumVanityNumbers().size() - 1).click();
    }


}
