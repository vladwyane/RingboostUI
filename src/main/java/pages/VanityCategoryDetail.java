package pages;

import blocks.RegularVanityNumbersBlock;
import blocks.VanityCategoryDetailBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigProperties;

import java.util.List;

public class VanityCategoryDetail extends BasePage {

    public VanityCategoryDetail(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("vanityCategoryDetail.url"));
    }

    private VanityCategoryDetailBlock vanityCategoryDetailBlock;
    private RegularVanityNumbersBlock regularVanityNumbersBlock;

    public void clickButtonClearAllFilters() {
        vanityCategoryDetailBlock.getButtonClearAllFilters().click();
        waiting2seconds();
    }

    public String chooseCategoryInSelect(String nameCategory) {
        vanityCategoryDetailBlock.getSelectCategory().click();
        waitUntilElementAppeared(vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().get(0));
        for (int i = 0; i < vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().size(); i++) {
            if(vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().get(i).getText().equals(nameCategory)) {
                vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().get(i).click();
                waiting2seconds();
                return regularVanityNumbersBlock.listRegularVanityNumbers.get(0).getText();
            }
        }
        return regularVanityNumbersBlock.listRegularVanityNumbers.get(0).getText();
    }

    public String choosePrefixInSelect(String prefix) {
        vanityCategoryDetailBlock.getSelectPrefix().click();
        waitUntilElementAppeared(vanityCategoryDetailBlock.getListOfPrefixInSelectDropDown().get(0));
        for (int i = 0; i < vanityCategoryDetailBlock.getListOfPrefixInSelectDropDown().size(); i++) {
            if(vanityCategoryDetailBlock.getListOfPrefixInSelectDropDown().get(i).getText().equals(prefix)) {
                vanityCategoryDetailBlock.getListOfPrefixInSelectDropDown().get(i).click();
                waiting2seconds();
                return regularVanityNumbersBlock.listRegularVanityNumbers.get(0).getText();
            }
        }
        return regularVanityNumbersBlock.listRegularVanityNumbers.get(0).getText();
    }

    public void checkingSelectedCategoryFromCategoryIndexPage(String nameCategory) {
        waitUntilElementAppeared(vanityCategoryDetailBlock.getTitleH1());
        softAssert.assertTrue(vanityCategoryDetailBlock.getTitleH1().getText().contains(nameCategory), "Title incorrect");
        softAssert.assertTrue(vanityCategoryDetailBlock.getSelectCategory().getText().toLowerCase().contains(nameCategory.toLowerCase()), "Category in select incorrect");
        softAssert.assertNotEquals(regularVanityNumbersBlock.listRegularVanityNumbers.size(), 0, "Numbers are null");
        softAssert.assertAll();
    }

    public void checkingDefaultState() {
        softAssert.assertEquals(vanityCategoryDetailBlock.getTitleH1().getText(), "Vanity Phone Numbers");
        softAssert.assertTrue(isElementContainsAttributeValue(vanityCategoryDetailBlock.getPlaceholderSelectCategory(), "placeholder", "Category"), "Placeholder Category is absent");
        softAssert.assertTrue(isElementContainsAttributeValue(vanityCategoryDetailBlock.getPlaceholderSelectPrefix(), "placeholder", "Prefix"), "Placeholder Prefix is absent");
        softAssert.assertNotEquals(regularVanityNumbersBlock.listRegularVanityNumbers.size(), 0, "Numbers are null");
        softAssert.assertTrue(isElementPresent(buttonMoreNumbers), "Load More is absent");
        softAssert.assertTrue(isElementPresent(vanityCategoryDetailBlock.getButtonClearAllFilters()), "Clear All is absent");
        softAssert.assertAll();
    }

    public void checkingSelectCategories(String firstNumberInListBefore, String firstNumberInListAfter, String nameCategory) {
        softAssert.assertTrue(vanityCategoryDetailBlock.getTitleH1().getText().contains(nameCategory), "Title incorrect");
        softAssert.assertTrue(vanityCategoryDetailBlock.getSelectCategory().getText().toLowerCase().contains(nameCategory.toLowerCase()), "Category in select incorrect");
        softAssert.assertNotEquals(firstNumberInListBefore, firstNumberInListAfter, "Numbers is not different");
        softAssert.assertAll();
    }

    public void checkingSelectPrefix(String firstNumberInList, String prefix) {
        softAssert.assertTrue(vanityCategoryDetailBlock.getSelectPrefix().getText().contains(prefix), "Prefix in select incorrect");
        softAssert.assertTrue(firstNumberInList.contains(prefix), "Numbers is not filter");
        softAssert.assertAll();
    }

    public void checkingLoadMore () {
        waitUntilElementWillBeClickable(buttonMoreNumbers);
        buttonMoreNumbers.click();
        waiting2seconds();
        softAssert.assertTrue(regularVanityNumbersBlock.getListRegularVanityNumbers().size() > 32, "Load More is not working");
        softAssert.assertAll();
    }


}
