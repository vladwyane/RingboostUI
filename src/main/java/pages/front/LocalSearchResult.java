package pages.front;

import blocks.front.LocalNumbersBlock;
import blocks.front.RelatedVanityBlock;
import blocks.front.SearchBlock;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;
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

    @Step("Choose phone number {0}st from local numbers list")
    public void chooseNumberFromLocalNumbersList(int numberOrder) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        waitUntilElementWillBeClickable(localNumbersBlock.getListOfAvailableLocalNumbers().get(numberOrder - 1));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(numberOrder - 1));
        localNumbersBlock.getListOfAvailableLocalNumbers().get(numberOrder - 1).click();
    }

    @Step("Click make offer link {0}st by local number")
    public void clickMakeOfferLink(int linkOrder) {
        waitUntilElementAppeared(localNumbersBlock.getListMakeOfferLinks().get(0));
        waitUntilElementWillBeClickable(localNumbersBlock.getListMakeOfferLinks().get(linkOrder - 1));
        scrollToElement(localNumbersBlock.getListMakeOfferLinks().get(linkOrder - 1));
        localNumbersBlock.getListMakeOfferLinks().get(linkOrder - 1).click();
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

    @Step("Checking status Sold on site")
    public void checkingStatusSold () {
        waitUntilElementWillBeClickable(localNumbersBlock.getListLocalNumbersLi().get(0));
        softAssert.assertTrue(isElementContainsAttributeValue(localNumbersBlock.getListLocalNumbersLi().get(0), "class", "sold"),
                "Number is available");
        softAssert.assertTrue(waitUntilElementWillBeClickable(localNumbersBlock.getListLocalNumbersLi().get(0)));
        softAssert.assertAll();
    }
}
