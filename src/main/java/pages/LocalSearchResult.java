package pages;

import blocks.LocalNumbersBlock;
import blocks.RelatedVanityBlock;
import blocks.SearchBlock;
import org.openqa.selenium.WebDriver;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 6/14/2019.
 */
public class LocalSearchResult extends BasePage {

    public LocalSearchResult(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("searchResultsLocal.url"));
    }

    private SearchBlock searchBlock;
    private LocalNumbersBlock localNumbersBlock;
    private RelatedVanityBlock relatedVanityBlock;

    public void searchLocalNumbers(String request) {
        type(searchBlock.getLocalSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }

    public void chooseFirstNumberFromLocalNumbersList() {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        waitUntilElementWillBeClickable(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        localNumbersBlock.getListOfAvailableLocalNumbers().get(0).click();
    }

    public void chooseLastNumberFromLocalNumbersList() {
        waitUntilElementWillBeClickable(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(localNumbersBlock.getListOfAvailableLocalNumbers().size()- 1));
        localNumbersBlock.getListOfAvailableLocalNumbers().get(localNumbersBlock.getListOfAvailableLocalNumbers().size()- 1).click();
    }

    public void chooseNumberFromLocalNumbersList(int numberOrder) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        waitUntilElementWillBeClickable(localNumbersBlock.getListOfAvailableLocalNumbers().get(numberOrder - 1));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(numberOrder - 1));
        localNumbersBlock.getListOfAvailableLocalNumbers().get(numberOrder - 1).click();
    }

    public void chooseFirstNumberFromRelatedVanityList() {
        waitUntilElementWillBeClickable(relatedVanityBlock.getLisRelatedVanityNumbers().get(0));
        relatedVanityBlock.getLisRelatedVanityNumbers().get(0).click();
    }

    public void chooseLastNumberFromRelatedVanityList() {
        waitUntilElementWillBeClickable(relatedVanityBlock.getLisRelatedVanityNumbers().get(0));
        scrollToElement(relatedVanityBlock.getLisRelatedVanityNumbers().get(relatedVanityBlock.getLisRelatedVanityNumbers().size()- 1));
        relatedVanityBlock.getLisRelatedVanityNumbers().get(relatedVanityBlock.getLisRelatedVanityNumbers().size()- 1).click();
    }

    public void checkingStatusSold () {
        waitUntilElementWillBeClickable(localNumbersBlock.getListLocalNumbersLi().get(0));
        softAssert.assertTrue(isElementContainsAttributeValue(localNumbersBlock.getListLocalNumbersLi().get(0), "class", "sold"),
                "Number is available");
        softAssert.assertTrue(waitUntilElementWillBeClickable(localNumbersBlock.getListLocalNumbersLi().get(0)));
        softAssert.assertAll();
    }
}
