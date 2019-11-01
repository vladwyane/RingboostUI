package pages.admin;

import blocks.admin.patterns.NewPatternsPopup;
import blocks.admin.patterns.PatternsTable;
import data.CategoriesData;
import data.PatternsData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 10/30/2019.
 */
public class PatternsTollFreePage extends BasePage {

    public PatternsTollFreePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("adminPatternsTollFree.url"));
    }

    PatternsTable patternsTable;
    NewPatternsPopup newPatternsPopup;

    public void clickAddNewPatternButton() {
        waitUntilElementAppeared(patternsTable.getButtonNewPattern());
        waitUntilElementWillBeClickable(patternsTable.getButtonNewPattern());
        patternsTable.getButtonNewPattern().click();
    }

    public void clickAddPatternButton() {
        waitUntilElementAppeared(newPatternsPopup.getButtonAddPattern());
        waitUntilElementWillBeClickable(newPatternsPopup.getButtonAddPattern());
        newPatternsPopup.getButtonAddPattern().click();
        waiting2seconds();
    }

    public void clickCancelButton() {
        waitUntilElementAppeared(newPatternsPopup.getButtonCancel());
        waitUntilElementWillBeClickable(newPatternsPopup.getButtonCancel());
        newPatternsPopup.getButtonCancel().click();
    }

    public void choosePatternType(String patternType) {
        waitUntilElementAppeared(newPatternsPopup.getSelectPatternType());
        newPatternsPopup.getSelectPatternType().click();
        waiting2seconds();
        for(WebElement element : newPatternsPopup.getListOfPatternType()) {
            if (element.getText().equals(patternType)) {
                element.click();
                return;
            }
        }
        newPatternsPopup.getListOfPatternType().get(0).click();
    }

    public void chooseFlowType(String flowType) {
        waitUntilElementAppeared(newPatternsPopup.getSelectFlowType());
        newPatternsPopup.getSelectFlowType().click();
        waiting2seconds();
        for(WebElement element : newPatternsPopup.getListOfFlowType()) {
            if (element.getText().equals(flowType)) {
                element.click();
                return;
            }
        }
        newPatternsPopup.getListOfFlowType().get(0).click();
    }

    public void chooseCategory(String category) {
        waitUntilElementAppeared(newPatternsPopup.getSelectOfCategory());
        newPatternsPopup.getSelectOfCategory().click();
        waiting2seconds();
        for(WebElement element : newPatternsPopup.getListOfCategory()) {
            if (element.getText().equals(category)) {
                element.click();
                return;
            }
        }
        newPatternsPopup.getListOfCategory().get(0).click();
    }

    public void fillNewPatternForm(PatternsData patternsData) {
        waitUntilElementAppeared(newPatternsPopup.getButtonAddPattern());
        type(newPatternsPopup.getPatternNameField(), patternsData.getNamePatterns());
        choosePatternType(patternsData.getTypePattern());
        chooseFlowType(patternsData.getFlowType());
        chooseCategory(patternsData.getCategory());
    }

    public void createNewPattern(PatternsData patternsData) {
        fillNewPatternForm(patternsData);
        waitUntilElementWillBeClickable(newPatternsPopup.getButtonAddPattern());
        newPatternsPopup.getButtonAddPattern().click();
    }

    public void searchPattern (String searchRequest) {
        waiting2seconds();
        patternsTable.getSearchField().sendKeys(Keys.CONTROL + "a");
        patternsTable.getSearchField().sendKeys(Keys.DELETE);
        type(patternsTable.getSearchField(), searchRequest);
        waiting2seconds();
    }

    public void clickEditIconFirstPattern() {
        waitUntilElementAppeared(patternsTable.getListOfActions().get(0));
        waitUntilElementWillBeClickable(patternsTable.getListOfActions().get(0));
        patternsTable.getListOfActions().get(0).click();
    }

    public void clickDeleteIconFirstPattern() {
        waitUntilElementAppeared(patternsTable.getListOfActions().get(1));
        waitUntilElementWillBeClickable(patternsTable.getListOfActions().get(1));
        patternsTable.getListOfActions().get(1).click();
        waiting2seconds();
        patternsTable.getButtonDelete().click();
        waiting2seconds();
    }

    public void checkingSuccessAlertMessage() {
        waitUntilElementAppeared(patternsTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(patternsTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAllFieldsIsEmpty() {
        waitUntilElementAppeared(newPatternsPopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(newPatternsPopup.getListOfErrorMessage().get(0).getText(), "The category id field is required.");
        softAssert.assertEquals(newPatternsPopup.getListOfErrorMessage().get(1).getText(), "The pattern field is required.");
        softAssert.assertAll();
    }

    public void checkingNameFirstPattern(PatternsData patternsData) {
        softAssert.assertEquals(patternsTable.getListTd().get(0).getText(), patternsData.getNamePatterns());
        softAssert.assertAll();
    }

    public void checkingErrorMessagesIsAbsent() {
        waitUntilElementAppeared(newPatternsPopup.getButtonAddPattern());
        waiting2seconds();
        softAssert.assertTrue(isElementInvisible(newPatternsPopup.getListOfErrorMessage().get(0)));
        softAssert.assertAll();
    }

    public void checkingErrorMessagesNameHasBeenUsed() {
        waitUntilElementAppeared(newPatternsPopup.getButtonAddPattern());
        waiting2seconds();
        softAssert.assertEquals(newPatternsPopup.getListOfErrorMessage().get(0).getText(), "The category id has already been taken.");
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted() {
        waitUntilElementAppeared(patternsTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(patternsTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void clickColumnHeaderOfTable(String titleTh) {
        waitUntilElementAppeared(patternsTable.getListColumnHeader().get(0));
        waitUntilElementWillBeClickable(patternsTable.getListColumnHeader().get(0));
        for (int i = 0; i < patternsTable.getListColumnHeader().size(); i++) {
            if(patternsTable.getListColumnHeader().get(i).getText().contains(titleTh)){
                patternsTable.getListColumnHeader().get(i).click();
                return;
            }
        }
    }

    public String getFistTdByColumn(String headingColumn) {
        int indexColumn = 0;
        for (int i = 0; i < patternsTable.getListColumnHeader().size(); i++) {
            if(patternsTable.getListColumnHeader().get(i).getText().contains(headingColumn)){
                indexColumn = i;
                break;
            }
        }
        waiting2seconds();
        return patternsTable.getListTd().get(indexColumn).getText();
    }

    public void checkingSuccessSorting(String beforeSorting, String afterSorting) {
        softAssert.assertNotEquals(beforeSorting, afterSorting);
        softAssert.assertAll();
    }
}
