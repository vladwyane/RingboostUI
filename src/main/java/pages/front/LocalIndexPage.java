package pages.front;

import blocks.front.SearchBlock;
import blocks.front.StateCatalogBlock;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 6/14/2019.
 */
public class LocalIndexPage extends BasePage {

    public LocalIndexPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Local Index Page")
    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("localIndex.url"));
    }

    private SearchBlock searchBlock;
    private StateCatalogBlock stateCatalogBlock;

    @Step("Enter Area Code: {0}")
    public void enterAreaCode(String areaCode) {
        waitUntilTextInElementAppear(searchBlock.getTitleH1(), "Buy a Local Phone Number");
        type(searchBlock.getAreaCodeField(), areaCode);
    }

    @Step("Search local number with request: {0}")
    public void searchLocalNumbers(String request) {
        waitUntilTextInElementAppear(searchBlock.getTitleH1(), "Buy a Local Phone Number");
        type(searchBlock.getLocalSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }

    @Step("Сhecking Filter Local States")
    public void checkingFilterLocalStates(String letter) {
        boolean firstStateOptions = false;
        boolean lettersOptionsIs27 = false;
        boolean totalStatesNot0 = false;
        boolean startWithLetter = false;

        searchBlock.getStateLink().click();
        waitUntilElementAppeared(stateCatalogBlock.getListLettersOptions().get(0));
        if (isElementContainsAttributeValue(stateCatalogBlock.getListLettersOptions().get(0), "class", "checked") &&
                stateCatalogBlock.getListLettersOptions().get(0).getText().equals("ALL"))
            firstStateOptions = true;

        if(stateCatalogBlock.getListLettersOptions().size() == 27)
            lettersOptionsIs27 = true;

        if(stateCatalogBlock.getListStates().size() != 0)
            totalStatesNot0 = true;

        for (int i = 0; i < stateCatalogBlock.getListLettersOptions().size(); i++) {
            if (stateCatalogBlock.getListLettersOptions().get(i).getText().equals(letter)) {
                scrollToElement(stateCatalogBlock.getListLettersOptions().get(i));
                stateCatalogBlock.getListLettersOptions().get(i).click();
                break;
            }
        }

        for (int i = 0; i < stateCatalogBlock.getListStates().size(); i++) {
            if(stateCatalogBlock.getListStates().get(i).getText().startsWith(letter))
                startWithLetter = true;
            else {
                startWithLetter = false;
                break;
            }
        }

        softAssert.assertTrue(firstStateOptions, "First state options is not ALL");
        softAssert.assertTrue(lettersOptionsIs27, "Letters options is 28");
        softAssert.assertTrue(totalStatesNot0, "Total categories is 0");
        softAssert.assertTrue(startWithLetter, "Start with letter incorrect");
        softAssert.assertAll();
    }

    @Step("Сhoose Local State: {0}")
    public String chooseLocalState(String nameState) {
        scrollToElement(searchBlock.getStateLink());
        searchBlock.getStateLink().click();
        waiting2seconds();
        for (int i = 0; i < stateCatalogBlock.getListStates().size(); i++) {
            String dd = stateCatalogBlock.getListStates().get(i).getText();
            if(stateCatalogBlock.getListStates().get(i).getText().equals(nameState)) {
                waitUntilElementWillBeClickable(stateCatalogBlock.getListStates().get(i));
                stateCatalogBlock.getListStates().get(i).click();
                break;
            }
        }

/*        for (int i = 0; i < vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().size(); i++) {
            if(vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().get(i).getText().equals(nameCategory)) {
                vanityCategoryDetailBlock.getListOfCategoryInSelectDropDown().get(i).click();
                waiting2seconds();
                return regularVanityNumbersBlock.listRegularVanityNumbers.get(0).getText();
            }
        } */
        return stateCatalogBlock.getListStates().get(0).getText();
    }

}
