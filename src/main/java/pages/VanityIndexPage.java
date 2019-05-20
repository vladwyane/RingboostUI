package pages;

import blocks.RegularVanityNumbersBlock;
import blocks.SearchBlock;
import blocks.VanityCategoryBlock;
import blocks.VanityCategoryDetailBlock;
import org.openqa.selenium.WebDriver;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 5/15/2019.
 */
public class VanityIndexPage extends BasePage {

    private VanityCategoryBlock vanityCategoryBlock;
    private SearchBlock searchBlock;
    private VanityCategoryDetailBlock vanityCategoryDetailBlock;
    private RegularVanityNumbersBlock regularVanityNumbersBlock;

    public VanityIndexPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("vanityIndex.url"));
    }

    public void searchTollFreeNumberFromVanityIndexPage(String request) {
        waitUntilTextInElementAppear(searchBlock.getTitleH1(), "Toll-Free Vanity Numbers");
        type(searchBlock.getTollFreeSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }

    public void checkingFilterVanityCatalog(String letter) {
        boolean firstStateOptions = false;
        boolean lettersOptionsIs28 = false;
        boolean totalCategoriesNot0 = false;
        boolean startWithLetter = false;

        searchBlock.getButtonOpenCatalog().click();
        waitUntilElementAppeared(vanityCategoryBlock.getListLettersOptions().get(0));
        if (isElementContainsAttributeValue(vanityCategoryBlock.getListLettersOptions().get(0), "class", "checked") &&
                vanityCategoryBlock.getListLettersOptions().get(0).getText().equals("ALL"))
            firstStateOptions = true;

        if(vanityCategoryBlock.getListLettersOptions().size() == 28)
            lettersOptionsIs28 = true;

        if(vanityCategoryBlock.getListVanityCategories().size() != 0)
            totalCategoriesNot0 = true;

        for (int i = 0; i < vanityCategoryBlock.getListLettersOptions().size(); i++) {
            if (vanityCategoryBlock.getListLettersOptions().get(i).getText().equals(letter)) {
                scrollToElement(vanityCategoryBlock.getListLettersOptions().get(i));
                vanityCategoryBlock.getListLettersOptions().get(i).click();
                break;
            }
        }

        for (int i = 0; i < vanityCategoryBlock.getListVanityCategories().size(); i++) {
            if(vanityCategoryBlock.getListVanityCategories().get(i).getText().startsWith(letter))
                startWithLetter = true;
            else {
                startWithLetter = false;
                break;
            }
        }

        softAssert.assertTrue(firstStateOptions, "First state options is not ALL");
        softAssert.assertTrue(lettersOptionsIs28, "Letters options is 28");
        softAssert.assertTrue(totalCategoriesNot0, "Total categories is 0");
        softAssert.assertTrue(startWithLetter, "Start with letter incorrect");
        softAssert.assertAll();
    }

    public String chooseVanityCategory(String nameCategory) {
        searchBlock.getButtonOpenCatalog().click();
        for (int i = 0; i < vanityCategoryBlock.getListVanityCategories().size(); i++) {
            if(vanityCategoryBlock.getListVanityCategories().get(i).getText().equals(nameCategory)) {
                waitUntilElementWillBeClickable(vanityCategoryBlock.getListVanityCategories().get(i));
                vanityCategoryBlock.getListVanityCategories().get(i).click();
                break;

            }
        }

        for (int i = 0; i < vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().size(); i++) {
            if(vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().get(i).getText().equals(nameCategory)) {
                vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().get(i).click();
                waiting2seconds();
                return regularVanityNumbersBlock.listRegularVanityNumbers.get(0).getText();
            }
        }
        return regularVanityNumbersBlock.listRegularVanityNumbers.get(0).getText();
    }


}
