package pages.front;

import blocks.front.FiltersBlock;
import blocks.front.LocalNumbersBlock;
import blocks.front.SearchBlock;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 7/15/2019.
 */
public class LocalStateDetail extends BasePage {

    FiltersBlock filtersBlock;
    LocalNumbersBlock localNumbersBlock;
    SearchBlock searchBlock;

    public LocalStateDetail(WebDriver driver) {
        super(driver);
    }

    @Step("Open State Detail Page")
    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("localStateDetail.url"));
    }

    @FindBy(css = "h1")
    private WebElement titleH1;

    @Step("Search local number with request: {0}")
    public void searchLocalNumbers(String request) {
        type(searchBlock.getLocalSearchField(), request);
        waiting2seconds();
    }

    public void clickButtonClearAllFilters() {
        filtersBlock.getButtonClearAllFilters().click();
        waiting2seconds();
    }


    @Step("Choose State In Select: {0}")
    public void chooseStateInSelect(String nameState) {
        filtersBlock.getSelectStates().click();
        waitUntilElementAppeared(filtersBlock.getListOfStatesInSelectDropDown().get(0));
        for (int i = 0; i < filtersBlock.getListOfStatesInSelectDropDown().size(); i++) {
            if(filtersBlock.getListOfStatesInSelectDropDown().get(i).getText().equals(nameState)) {
                filtersBlock.getListOfStatesInSelectDropDown().get(i).click();
                waiting2seconds();
            }
        }
    }

    @Step("Choose AreaCode In Select: {0}")
    public void chooseAreaCodeInSelect(String nameAreaCode) {
        filtersBlock.getSelectAreaCode().click();
        waitUntilElementAppeared(filtersBlock.getListOfAreaCodesInSelectDropDown().get(0));
        for (int i = 0; i < filtersBlock.getListOfAreaCodesInSelectDropDown().size(); i++) {
            if(filtersBlock.getListOfAreaCodesInSelectDropDown().get(i).getText().equals(nameAreaCode)) {
                filtersBlock.getListOfAreaCodesInSelectDropDown().get(i).click();
                waiting2seconds();
            }
        }
    }

    @Step("Choose Pattern In Select: {0}")
    public void choosePatternInSelect(String namePattern) {
        filtersBlock.getSelectPattern().click();
        waitUntilElementAppeared(filtersBlock.getListOfPatternsInSelectDropDown().get(0));
        for (int i = 0; i < filtersBlock.getListOfPatternsInSelectDropDown().size(); i++) {
            if(filtersBlock.getListOfPatternsInSelectDropDown().get(i).getText().toLowerCase().equals(namePattern.toLowerCase())) {
                filtersBlock.getListOfPatternsInSelectDropDown().get(i).click();
                waiting2seconds();
            }
        }
    }

    @Step("Choose Sort by In Select: {0}")
    public void chooseSortByInSelect(String sortBy) {
        scrollToElement(filtersBlock.getSelectSortBy());
        filtersBlock.getSelectSortBy().click();
        waitUntilElementAppeared(filtersBlock.getListOfSortByInSelectDropDown().get(0));
        for (int i = 0; i < filtersBlock.getListOfSortByInSelectDropDown().size(); i++) {
            if(filtersBlock.getListOfSortByInSelectDropDown().get(i).getText().toLowerCase().equals(sortBy.toLowerCase())) {
                filtersBlock.getListOfSortByInSelectDropDown().get(i).click();
                waiting2seconds();
            }
        }
    }

    @Step("Сhecking Selected State From State Index Page")
    public void checkingSelectedStateFromStateIndexPage(String nameState) {
        waitUntilElementAppeared(localNumbersBlock);
        softAssert.assertTrue(titleH1.getText().contains(nameState), "Title incorrect");
        softAssert.assertTrue(filtersBlock.getSelectStates().getText().contains(nameState), "Category in select incorrect");
        softAssert.assertNotEquals(localNumbersBlock.getListPricesLocalNumbers().size(), 0, "Numbers are null");
        softAssert.assertEquals(localNumbersBlock.getListLocalNumbersLi().size(), 32, "Amount of numbers is incorrect");
        softAssert.assertTrue(isElementPresent(buttonMoreNumbers), "Load More is absent");
        softAssert.assertAll();
    }

    @Step("Сhecking Default State")
    public void checkingDefaultState() {
        softAssert.assertEquals(titleH1.getText(), "Local Numbers");
        softAssert.assertTrue(isElementContainsAttributeValue(filtersBlock.getPlaceholderSelectState(), "placeholder", "State"),"Placeholder State absent");
        softAssert.assertTrue(isElementContainsAttributeValue(filtersBlock.getPlaceholderSelectAreaCode(), "placeholder", "Area Code"),"Placeholder Area Code absent");
        softAssert.assertTrue(isElementContainsAttributeValue(filtersBlock.getPlaceholderSelectPattern(), "placeholder", "Pattern"), "Placeholder Pattern absent");
        softAssert.assertNotEquals(localNumbersBlock.getListPricesLocalNumbers(), 0, "Numbers are null");
        softAssert.assertTrue(isElementPresent(filtersBlock.getButtonClearAllFilters()), "Clear All is absent");
        softAssert.assertEquals(filtersBlock.getSelectSortBy().getText(), "", "Sort By is incorrect");
        softAssert.assertEquals(filtersBlock.getRangeTooltipStart().getText(), "1");
        softAssert.assertEquals(filtersBlock.getRangeTooltipFinish().getText(), "10000");
        softAssert.assertEquals(localNumbersBlock.getListLocalNumbersLi().size(), 32, "Amount of numbers is incorrect");
        softAssert.assertTrue(isElementPresent(buttonMoreNumbers), "Load More is absent");
        softAssert.assertAll();
    }

    @Step("Checking Load More Is Absent After Filtration")
    public void checkingLoadMoreIsAbsentAfterFiltration(String nameState, String areaCode, String pattern) {
        softAssert.assertTrue(titleH1.getText().contains(nameState), "Title incorrect");
        softAssert.assertTrue(filtersBlock.getSelectStates().getText().contains(nameState), "Category in select is incorrect");
        softAssert.assertTrue(filtersBlock.getSelectAreaCode().getText().contains(areaCode), "AreaCode in select is incorrect");
        softAssert.assertTrue(filtersBlock.getSelectPattern().getText().toLowerCase().contains(pattern.toLowerCase()), "Pattern in select is incorrect");
        softAssert.assertNotEquals(localNumbersBlock.getListLocalNumbersLi().size(), 32, "Amount of numbers is incorrect");
        softAssert.assertNotEquals(localNumbersBlock.getListPricesLocalNumbers().size(), 0, "Numbers are null");
        softAssert.assertFalse(isElementPresent(buttonMoreNumbers), "Load More is absent");
        softAssert.assertAll();
    }

    @Step("Сhecking Correct Filter By Pattern")
    public void checkingCorrectFilterByPattern(String pattern) {
        boolean correctFilter = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText().substring(8, 9).
                    equals(localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText().substring(9, 10)) &&
                        localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText().substring(9, 10).
                            equals(localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText().substring(10, 11)) &&
                                localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText().substring(10, 11).
                                    equals(localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText().substring(11, 12))) {
                correctFilter = true;
            } else {
                correctFilter = false;
                break;
            }
        }
        softAssert.assertTrue(correctFilter, "Numbers is not filter");
        softAssert.assertTrue(filtersBlock.getSelectPattern().getText().toLowerCase().contains(pattern.toLowerCase()), "Pattern in select is incorrect");
        softAssert.assertEquals(localNumbersBlock.getListLocalNumbersLi().size(), 32, "Amount of numbers is incorrect");
        softAssert.assertNotEquals(localNumbersBlock.getListPricesLocalNumbers().size(), 0, "Numbers are null");
        softAssert.assertTrue(isElementPresent(buttonMoreNumbers), "Load More is absent");
        softAssert.assertAll();
    }

    @Step("Сhecking Correct Filter By State")
    public void checkingCorrectFilterByState(String nameState, String areaCode) {
        boolean correctFilter = false;
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            if (localNumbersBlock.getListOfAvailableLocalNumbers().get(i).getText().substring(0, 3).equals(areaCode)) {
                correctFilter = true;
            } else {
                correctFilter = false;
                break;
            }
        }
        softAssert.assertTrue(correctFilter, "Numbers is not filter");
        softAssert.assertTrue(titleH1.getText().contains(nameState), "Title incorrect");
        softAssert.assertTrue(filtersBlock.getSelectStates().getText().contains(nameState), "Category in select is incorrect");
        softAssert.assertTrue(filtersBlock.getSelectAreaCode().getText().contains(areaCode), "AreaCode in select is incorrect");
        softAssert.assertEquals(localNumbersBlock.getListLocalNumbersLi().size(), 32, "Amount of numbers is incorrect");
        softAssert.assertNotEquals(localNumbersBlock.getListPricesLocalNumbers().size(), 0, "Numbers are null");
        softAssert.assertTrue(isElementPresent(buttonMoreNumbers), "Load More is absent");
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

    @Step("Сhecking Correct Sort By")
    public void checkingCorrectSortBy(String nameState) {
        boolean correctFilter = false;
        double maxPrice = Double.parseDouble(localNumbersBlock.getListPricesLocalNumbers().get(0).getText().replaceAll("\\D+",""));
        for (int i = 0; i < localNumbersBlock.getListOfAvailableLocalNumbers().size(); i++) {
            double currentPrice = Double.parseDouble(localNumbersBlock.getListPricesLocalNumbers().get(i).getText().replaceAll("\\D+",""));
            if (currentPrice <= maxPrice) {
                maxPrice = currentPrice;
                correctFilter = true;
            } else {
                correctFilter = false;
                break;
            }
        }
        softAssert.assertTrue(correctFilter, "Numbers is not filter");
        softAssert.assertTrue(titleH1.getText().contains(nameState), "Title incorrect");
        softAssert.assertTrue(filtersBlock.getSelectStates().getText().contains(nameState), "Category in select is incorrect");
        softAssert.assertEquals(localNumbersBlock.getListLocalNumbersLi().size(), 32, "Amount of numbers is incorrect");
        softAssert.assertNotEquals(localNumbersBlock.getListPricesLocalNumbers().size(), 0, "Numbers are null");
        softAssert.assertTrue(isElementPresent(buttonMoreNumbers), "Load More is absent");
        softAssert.assertAll();
    }

}
