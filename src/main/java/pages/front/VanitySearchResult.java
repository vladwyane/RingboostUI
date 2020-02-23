package pages.front;

import blocks.front.PremiumVanityNumbersBlock;
import blocks.front.RegularVanityNumbersBlock;
import blocks.front.SmallSearchBlock;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;
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
        driver.get(ConfigProperties.getProperty("searchResultsVanity.url"));
    }

    public void searchTollFreeNumbers(String request) {
        type(smallSearchBlock.getTollFreeSearchField(), request);
        waiting2seconds();
    }

    public void clickButtonLoadMore() {
        waiting2seconds();
        buttonMoreNumbers.click();
        waiting2seconds();
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
        scrollToElement(regularVanityNumbersBlock.getListRegularVanityNumbers().get(0));
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

    @Step("Choose phone number {0}st from toll-free regular numbers list")
    public void chooseIndexNumberFromRegularVanityList(int numberOrder) {
        waitUntilElementAppeared(regularVanityNumbersBlock.getTitleSection());
        scrollToElement(regularVanityNumbersBlock.getListRegularVanityNumbers().get(numberOrder - 1));
        regularVanityNumbersBlock.getListRegularVanityNumbers().get(numberOrder - 1).click();
    }

    public void chooseFirstNumberFromPremiumVanityList() {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(0));
        premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(0).click();
    }

    @Step("Choose phone number {0}st from toll-free premium numbers list")
    public void chooseIndexNumberFromPremiumVanityList(int numberOrder) {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(numberOrder - 1));
        premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(numberOrder - 1).click();
    }

    public void chooseLastNumberFromPremiumVanityList() {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(premiumVanityNumbersBlock.getListPremiumVanityNumbers().size() - 1));
        premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(premiumVanityNumbersBlock.getListPremiumVanityNumbers().size() - 1).click();
    }

    public void checkingSearchResultSevenOrEightSymbols(String request) {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(0));
        boolean searchResult = false;
        for (int i = 0; i < premiumVanityNumbersBlock.getListPremiumVanityNumbers().size(); i++) {
            if ( premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(7).equals(request) ||
                    premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(4).equals(request)) {
                searchResult = true;
            } else {
                searchResult = false;
                break;
            }
        }
        softAssert.assertTrue(searchResult);
        softAssert.assertAll();
    }

    public void checkingSearchResultFourAndMoreSymbols(String request) {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(0));
        boolean searchResult = false;
        for (int i = 0; i < premiumVanityNumbersBlock.getListPremiumVanityNumbers().size(); i++) {
            if ( premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(7).equals(request) ||
                    premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(i).getText()
                            .replaceAll("-","").substring(4).contains(request)) {
                searchResult = true;
            } else {
                searchResult = false;
                break;
            }
        }
        softAssert.assertTrue(searchResult);
        softAssert.assertAll();
    }

    public void checkingSearchResultMoreThen14Symbols(String request) {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(0));
        boolean searchResult = false;
        for (int i = 0; i < premiumVanityNumbersBlock.getListPremiumVanityNumbers().size(); i++) {
            if (premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(7).equals(request.substring(0, 14)) ||
                    premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(i).getText()
                            .replaceAll("-","").substring(4).equals(request.substring(0, 14))) {
                searchResult = true;
            } else {
                searchResult = false;
                break;
            }
        }
        softAssert.assertTrue(searchResult);
        softAssert.assertAll();
    }

    public void checkingSearchResult3Symbols(String request) {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(0));
        boolean searchResult = false;
        for (int i = 0; i < premiumVanityNumbersBlock.getListPremiumVanityNumbers().size(); i++) {
            if (premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(i).getText()
                            .replaceAll("-","").substring(4).contains(request)) {
                searchResult = true;
            } else {
                searchResult = false;
                break;
            }
        }
        softAssert.assertTrue(searchResult);
        softAssert.assertAll();
    }

    public void checkingSearchResultSpecialSymbols(String request) {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getTitleSection());
        scrollToElement(premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(0));
        boolean searchResult = true;
        for (int i = 0; i < premiumVanityNumbersBlock.getListPremiumVanityNumbers().size(); i++) {
            if (premiumVanityNumbersBlock.getListPremiumVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(4).contains(request)) {
                searchResult = true;
                break;
            } else {
                searchResult = false;
            }
        }
        softAssert.assertFalse(searchResult, "Search result is not correct");
        softAssert.assertAll();
    }

    public void checkingSearchResultFourAndMoreSymbolsFromCategoryDetailPage(String request) {
        waitUntilElementAppeared(regularVanityNumbersBlock.getListRegularVanityNumbers().get(0));
        boolean searchResult = false;
        for (int i = 0; i < regularVanityNumbersBlock.getListRegularVanityNumbers().size(); i++) {
            if (regularVanityNumbersBlock.getListRegularVanityNumbers().get(i).getText()
                    .replaceAll("-","").substring(7).equals(request) ||
                    regularVanityNumbersBlock.getListRegularVanityNumbers().get(i).getText()
                            .replaceAll("-","").substring(4).contains(request)) {
                searchResult = true;
            } else {
                searchResult = false;
                break;
            }
        }
        softAssert.assertTrue(searchResult);
        softAssert.assertAll();
    }

    public void checkingLoadMore () {
        waitUntilElementWillBeClickable(buttonMoreNumbers);
        scrollToElement(buttonMoreNumbers);
        int before = regularVanityNumbersBlock.getListStatusNumbers().size();
        buttonMoreNumbers.click();
        waiting2seconds();
        int after = regularVanityNumbersBlock.getListStatusNumbers().size();
        softAssert.assertTrue( after > before, "Load More is not working");
        softAssert.assertAll();
    }

    @Step("Checking Status Licensed By Index")
    public void checkingStatusLicensedByIndex(int index) {
        waitUntilElementAppeared(premiumVanityNumbersBlock.getListStatusPremiumVanityNumbers().get(index - 1));
        softAssert.assertEquals(premiumVanityNumbersBlock.getListStatusPremiumVanityNumbers().get(index - 1).getText(), "Licensed");
        softAssert.assertAll();
    }


}
