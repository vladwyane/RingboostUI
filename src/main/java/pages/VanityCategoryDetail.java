package pages;

import blocks.RegularVanityNumbersBlock;
import blocks.VanityCategoryDetailBlock;
import org.openqa.selenium.WebDriver;
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

    public void clickButtonClearAllFilters() {
        vanityCategoryDetailBlock.getButtonClearAllFilters().click();
        waiting2seconds();
    }

    public void chooseCategoryInSelect() {
        vanityCategoryDetailBlock.getSelectCategory().click();
        vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().get(10).click();
    }

    public void checkingSelectedCategoryFromCategoryIndexPage(String nameCategory) {
        softAssert.assertTrue(vanityCategoryDetailBlock.getTitleH1().getText().contains(nameCategory), "Title incorrect");
        softAssert.assertTrue(vanityCategoryDetailBlock.getSelectCategory().getText().contains(nameCategory.toLowerCase()), "Category in select incorrect");
        softAssert.assertNotEquals(regularVanityNumbersBlock.listRegularVanityNumbers.size(), 0, "Numbers are null");
        softAssert.assertAll();
    }

    public void checkingCategoriesDetailPageDefaultState() {
        softAssert.assertEquals(vanityCategoryDetailBlock.getTitleH1().getText(), "Vanity Numbers");
        softAssert.assertTrue(isElementContainsAttributeValue(vanityCategoryDetailBlock.getPlaceholderSelectCategory(), "placeholder", "Category"), "Placeholder Category is absent");
        softAssert.assertTrue(isElementContainsAttributeValue(vanityCategoryDetailBlock.getPlaceholderSelectPrefix(), "placeholder", "Prefix"), "Placeholder Prefix is absent");
        softAssert.assertNotEquals(regularVanityNumbersBlock.listRegularVanityNumbers.size(), 0, "Numbers are null");
        softAssert.assertAll();
    }
}
