package pages.front;

import blocks.front.LocalNumbersBlock;
import blocks.front.RelatedVanityBlock;
import blocks.front.SearchBlock;
import org.openqa.selenium.NoSuchElementException;
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

    @Step("Search local number with request: {0}")
    public void searchLocalNumbers(String request) {
        type(searchBlock.getLocalSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }

    @Step("Search click button Load More: {0}")
    public void clickButtonLoadMore() {
        waiting2seconds();
        scrollToElement(buttonMoreNumbers);
        buttonMoreNumbers.click();
        waiting2seconds();
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

    @Step("Choose First Number From Related Vanity List")
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

    @Step("Checking Search Result Seven Symbols, request: {0}")
    public void checkingSearchResultSevenSymbols(String request) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        boolean searchResultLocal = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText()
                    .substring(4, 11).equals(request)) {
                searchResultLocal = true;
            } else {
                searchResultLocal = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultLocal, "Search result local is incorrect");
        softAssert.assertEquals(relatedVanityBlock.getTitleSection().getText(), "The Following Related Vanity Numbers are Available for");
        boolean searchResultTollFree = false;
        for (int i = 0; i < relatedVanityBlock.getLisRelatedVanityNumbers().size(); i++) {
            if (relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(7).equals(request) ||
                    relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                            .replaceAll("-","").substring(4).equals(request)) {
                searchResultTollFree = true;
            } else {
                searchResultTollFree = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultTollFree, "Search result toll-free is incorrect");
        softAssert.assertAll();
    }

    @Step("Checking Search Result Eight Symbols, request: {0}")
    public void checkingSearchResultEightLetters(String request) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        boolean searchResultLocal = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText()
                    .substring(4, 11).toLowerCase().equals(request.substring(0, 7).toLowerCase())) {
                searchResultLocal = true;
            } else {
                searchResultLocal = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultLocal, "Search result local is incorrect");
        softAssert.assertEquals(relatedVanityBlock.getTitleSection().getText(), "The Following Related Vanity Numbers are Available for");
        boolean searchResultTollFree = false;
        for (int i = 0; i < relatedVanityBlock.getLisRelatedVanityNumbers().size(); i++) {
            if (relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(7).toLowerCase().equals(request.substring(0, 7).toLowerCase()) ||
                    relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                            .replaceAll("-","").substring(4).toLowerCase().equals(request.substring(0, 7).toLowerCase())) {
                searchResultTollFree = true;
            } else {
                searchResultTollFree = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultTollFree, "Search result toll-free is incorrect");
        softAssert.assertAll();
    }

    @Step("Checking Search Result Six Symbols, request: {0}")
    public void checkingSearchResultSixSymbols(String request) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        boolean searchResultLocal = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText()
                            .replaceAll("-","").substring(3).toLowerCase().contains(request.toLowerCase())) {
                searchResultLocal = true;
            } else {
                searchResultLocal = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultLocal, "Search result local is incorrect");
        softAssert.assertEquals(relatedVanityBlock.getTitleSection().getText(), "The Following Related Vanity Numbers are Available for");
        boolean searchResultTollFree = false;
        for (int i = 0; i < relatedVanityBlock.getLisRelatedVanityNumbers().size(); i++) {
            if (relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(7).toLowerCase().equals(request.toLowerCase()) ||
                    relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                            .replaceAll("-","").substring(4).toLowerCase().contains(request.toLowerCase())) {
                searchResultTollFree = true;
            } else {
                searchResultTollFree = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultTollFree, "Search result toll-free is incorrect");
        softAssert.assertAll();
    }

    @Step("Checking Search Result Five Symbols, request: {0}")
    public void checkingSearchResultFiveSymbolsSymbols(String request) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        boolean searchResultLocal = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText()
                    .replaceAll("-","").substring(5, 10).toLowerCase().equals(request.toLowerCase())) {
                searchResultLocal = true;
            } else {
                searchResultLocal = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultLocal, "Search result local is incorrect");
        softAssert.assertEquals(relatedVanityBlock.getTitleSection().getText(), "The Following Related Vanity Numbers are Available for");
        boolean searchResultTollFree = false;
        for (int i = 0; i < relatedVanityBlock.getLisRelatedVanityNumbers().size(); i++) {
            if (relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                            .replaceAll("-","").substring(6).toLowerCase().equals(request.toLowerCase())) {
                searchResultTollFree = true;
            } else {
                searchResultTollFree = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultTollFree, "Search result toll-free is incorrect");
        softAssert.assertAll();
    }

    @Step("Checking Search Result Four Symbols, request: {0}")
    public void checkingSearchResultFourSymbolsSymbols(String request) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        boolean searchResultLocal = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText()
                    .replaceAll("-","").substring(6, 10).toLowerCase().equals(request.toLowerCase())) {
                searchResultLocal = true;
            } else {
                searchResultLocal = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultLocal, "Search result local is incorrect");
        softAssert.assertEquals(relatedVanityBlock.getTitleSection().getText(), "The Following Related Vanity Numbers are Available for");
        boolean searchResultTollFree = false;
        for (int i = 0; i < relatedVanityBlock.getLisRelatedVanityNumbers().size(); i++) {
            if (relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(7).toLowerCase().equals(request.toLowerCase())) {
                searchResultTollFree = true;
            } else {
                searchResultTollFree = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultTollFree, "Search result toll-free is incorrect");
        softAssert.assertAll();
    }

    @Step("Checking Search Result Three Symbols, request: {0}")
    public void checkingSearchResultThreeSymbolsSymbols(String request) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        boolean searchResultLocal = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText()
                    .replaceAll("-","").substring(7, 10).toLowerCase().equals(request.toLowerCase())) {
                searchResultLocal = true;
            } else {
                searchResultLocal = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultLocal, "Search result local is incorrect");
        softAssert.assertEquals(relatedVanityBlock.getTitleSection().getText(), "The Following Related Vanity Numbers are Available for");
        boolean searchResultTollFree = false;
        for (int i = 0; i < relatedVanityBlock.getLisRelatedVanityNumbers().size(); i++) {
            if (relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(8).toLowerCase().equals(request.toLowerCase())) {
                searchResultTollFree = true;
            } else {
                searchResultTollFree = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultTollFree, "Search result toll-free is incorrect");
        softAssert.assertAll();
    }

    @Step("Checking Search Result Four Symbols, request: {0}")
    public void checkingSearchResultSpecialSymbols(String request) {
        waitUntilElementAppeared(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        scrollToElement(localNumbersBlock.getListOfAvailableLocalNumbers().get(0));
        boolean searchResultLocal = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (!localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText()
                    .replaceAll("-","").substring(4, 11).toLowerCase().contains(request.toLowerCase())) {
                searchResultLocal = true;
            } else {
                searchResultLocal = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultLocal, "Search result local is incorrect");
        softAssert.assertEquals(relatedVanityBlock.getTitleSection().getText(), "The Following Related Vanity Numbers are Available for");
        boolean searchResultTollFree = false;
        for (int i = 0; i < relatedVanityBlock.getLisRelatedVanityNumbers().size(); i++) {
            if (!relatedVanityBlock.getLisRelatedVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(4).toLowerCase().contains(request.toLowerCase())) {
                searchResultTollFree = true;
            } else {
                searchResultTollFree = false;
                break;
            }
        }
        softAssert.assertTrue(searchResultTollFree, "Search result toll-free is incorrect");
        softAssert.assertAll();
    }

    @Step("Сhecking Load More")
    public void checkingLoadMore () {
        waitUntilElementWillBeClickable(buttonMoreNumbers);
        try {
            buttonMoreNumbers.click();
        }
        catch (NoSuchElementException e) {
            e.getMessage();
        }
        waiting2seconds();
        softAssert.assertTrue(localNumbersBlock.getListLocalNumbersLi().size() > 32, "Load More is not working");
        softAssert.assertAll();
    }

}
