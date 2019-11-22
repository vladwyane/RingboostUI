package pages.front;

import blocks.front.RegularVanityNumbersBlock;
import blocks.front.SmallSearchBlock;
import blocks.front.VanityCategoryDetailBlock;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.ConfigProperties;

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
    private SmallSearchBlock smallSearchBlock;

    public void searchTollFreeNumbers(String request) {
        type(smallSearchBlock.getTollFreeSearchField(), request);
        smallSearchBlock.getButtonFindNumber().click();
    }

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
        return regularVanityNumbersBlock.getFullListRegularVanityNumbers().get(0).getText();
    }

    public String[] choosePrefixInSelect(String prefix) {
        vanityCategoryDetailBlock.getSelectPrefix().click();
        waitUntilElementAppeared(vanityCategoryDetailBlock.getListOfPrefixInSelectDropDown().get(0));
        String[] array = new String[2];
        for (int i = 0; i < vanityCategoryDetailBlock.getListOfPrefixInSelectDropDown().size(); i++) {
            if(vanityCategoryDetailBlock.getListOfPrefixInSelectDropDown().get(i).getText().equals(prefix)) {
                vanityCategoryDetailBlock.getListOfPrefixInSelectDropDown().get(i).click();
                waiting2seconds();
                array[0] = regularVanityNumbersBlock.getFullListRegularVanityNumbers().get(0).getText();
                array[1] = regularVanityNumbersBlock.getFullListRegularVanityNumbers().
                        get(regularVanityNumbersBlock.getFullListRegularVanityNumbers().size() - 1).getText();
                return array;
            }
        }
        return array;
    }

    public void checkingSelectedCategoryFromCategoryIndexPage(String nameCategory) {
        waitUntilElementAppeared(vanityCategoryDetailBlock.getTitleH1());
        softAssert.assertTrue(vanityCategoryDetailBlock.getTitleH1().getText().contains(nameCategory), "Title incorrect");
        softAssert.assertTrue(vanityCategoryDetailBlock.getSelectCategory().getText().toLowerCase().contains(nameCategory.toLowerCase()), "Category in select incorrect");
        softAssert.assertNotEquals(regularVanityNumbersBlock.getFullListRegularVanityNumbers().size(), 0, "Numbers are null");
        softAssert.assertAll();
    }

    public void checkingDefaultState() {
        waiting2seconds();
        softAssert.assertEquals(vanityCategoryDetailBlock.getTitleH1().getText(), "Vanity Phone Numbers");
        softAssert.assertTrue(isElementContainsAttributeValue(vanityCategoryDetailBlock.getPlaceholderSelectCategory(), "placeholder", "Category"), "Placeholder Category is absent");
        softAssert.assertTrue(isElementContainsAttributeValue(vanityCategoryDetailBlock.getPlaceholderSelectPrefix(), "placeholder", "Prefix"), "Placeholder Prefix is absent");
        softAssert.assertNotEquals(regularVanityNumbersBlock.getFullListRegularVanityNumbers().size(), 0, "Numbers are null");
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

    public void checkingSelectPrefix(String[] numbersInListAfter, String prefix) {
        boolean correctFilter = false;
        for (String aNumbersInListAfter : numbersInListAfter) {
            if (aNumbersInListAfter.contains(prefix)) {
                correctFilter = true;
            } else {
                correctFilter = false;
                break;
            }
        }
        softAssert.assertTrue(vanityCategoryDetailBlock.getSelectPrefix().getText().contains(prefix), "Prefix in select incorrect");
        softAssert.assertTrue(correctFilter, "Numbers is not filter");
        softAssert.assertAll();
    }

    public void checkingLoadMore () {
        waitUntilElementWillBeClickable(buttonMoreNumbers);
        buttonMoreNumbers.click();
        waiting2seconds();
        softAssert.assertTrue(regularVanityNumbersBlock.getFullListRegularVanityNumbers().size() > 32, "Load More is not working");
        softAssert.assertAll();
    }


}
