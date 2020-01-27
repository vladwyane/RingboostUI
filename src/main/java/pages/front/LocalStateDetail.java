package pages.front;

import blocks.front.FiltersBlock;
import blocks.front.LocalNumbersBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 7/15/2019.
 */
public class LocalStateDetail extends BasePage {

    FiltersBlock filtersBlock;
    LocalNumbersBlock localNumbersBlock;

    public LocalStateDetail(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("localStateDetail.url"));
    }

    @FindBy(css = "h1")
    private WebElement titleH1;

    public void clickButtonClearAllFilters() {
        filtersBlock.getButtonClearAllFilters().click();
        waiting2seconds();
    }

    public String chooseStateInSelect(String nameState) {
        filtersBlock.getSelectStates().click();
        waitUntilElementAppeared(filtersBlock.getListOfStatesInSelectDropDown().get(0));
        for (int i = 0; i < filtersBlock.getListOfStatesInSelectDropDown().size(); i++) {
            if(filtersBlock.getListOfStatesInSelectDropDown().get(i).getText().equals(nameState)) {
                filtersBlock.getListOfStatesInSelectDropDown().get(i).click();
                waiting2seconds();
                return filtersBlock.getListOfStatesInSelectDropDown().get(0).getText();
            }
        }
        return filtersBlock.getListOfStatesInSelectDropDown().get(0).getText();
    }

    public String chooseAreaCodeInSelect(String nameAreaCode) {
        filtersBlock.getSelectAreaCode().click();
        waitUntilElementAppeared(filtersBlock.getListOfAreaCodesInSelectDropDown().get(0));
        for (int i = 0; i < filtersBlock.getListOfAreaCodesInSelectDropDown().size(); i++) {
            if(filtersBlock.getListOfAreaCodesInSelectDropDown().get(i).getText().equals(nameAreaCode)) {
                filtersBlock.getListOfAreaCodesInSelectDropDown().get(i).click();
                waiting2seconds();
                return filtersBlock.getListOfAreaCodesInSelectDropDown().get(0).getText();
            }
        }
        return filtersBlock.getListOfAreaCodesInSelectDropDown().get(0).getText();
    }

    public String choosePatternInSelect(String namePattern) {
        filtersBlock.getSelectPattern().click();
        waitUntilElementAppeared(filtersBlock.getListOfPatternsInSelectDropDown().get(0));
        for (int i = 0; i < filtersBlock.getListOfPatternsInSelectDropDown().size(); i++) {
            if(filtersBlock.getListOfPatternsInSelectDropDown().get(i).getText().equals(namePattern)) {
                filtersBlock.getListOfPatternsInSelectDropDown().get(i).click();
                waiting2seconds();
                return filtersBlock.getListOfPatternsInSelectDropDown().get(0).getText();
            }
        }
        return filtersBlock.getListOfPatternsInSelectDropDown().get(0).getText();
    }

    public void checkingSelectedStateFromStateIndexPage(String nameState) {
        waitUntilElementAppeared(localNumbersBlock);
        softAssert.assertTrue(titleH1.getText().contains(nameState), "Title incorrect");
        softAssert.assertTrue(filtersBlock.getSelectStates().getText().contains(nameState), "Category in select incorrect");
        softAssert.assertNotEquals(localNumbersBlock.getListPricesLocalNumbers().size(), 0, "Numbers are null");
        softAssert.assertAll();
    }

    public void checkingDefaultState() {
        softAssert.assertEquals(titleH1.getText(), "All Local Numbers");
        softAssert.assertEquals(filtersBlock.getSelectStates().getText(), "All");
        softAssert.assertTrue(isElementContainsAttributeValue(filtersBlock.getSelectAreaCode(), "placeholder", "Area Code"),"Placeholder Area Code absent");
        softAssert.assertTrue(isElementContainsAttributeValue(filtersBlock.getPlaceholderSelectPattern(), "placeholder", "Pattern"), "Placeholder Pattern absent");
        softAssert.assertNotEquals(localNumbersBlock.getListPricesLocalNumbers(), 0, "Numbers are null");
        softAssert.assertTrue(isElementPresent(filtersBlock.getButtonClearAllFilters()), "Clear All is absent");
        softAssert.assertEquals(filtersBlock.getSelectSortBy().getText(), "", "Sort By is incorrect");
        softAssert.assertEquals(filtersBlock.getRangeTooltipStart().getText(), "1");
        softAssert.assertEquals(filtersBlock.getRangeTooltipFinish().getText(), "10000");
        softAssert.assertAll();
    }


}
