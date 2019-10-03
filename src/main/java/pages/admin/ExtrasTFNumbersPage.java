package pages.admin;

import blocks.admin.extrasTFNumbers.*;
import data.TFNumberSettings;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Created by bigdrop on 9/12/2019.
 */
public class ExtrasTFNumbersPage extends BasePage {

    public ExtrasTFNumbersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    AddRulePopups addRulePopups;
    ExtrasTFNumbersTable extrasTFNumbersTable;

    public void clickTab(String tabName) {
        waitUntilElementAppeared(extrasTFNumbersTable.getListOfTabs().get(0));
        for(WebElement element : extrasTFNumbersTable.getListOfTabs()) {
            if (element.getText().equals(tabName.toUpperCase())) {
                element.click();
                return;
            }
        }
        addRulePopups.getListOfCountry().get(0).click();
    }

    public void clickAddNewTierButton() {
        waitUntilElementAppeared(extrasTFNumbersTable.getAddTierButton());
        extrasTFNumbersTable.getAddTierButton().click();
    }

    public void createNewTier(TFNumberSettings tfNumberSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        type(addRulePopups.getNameField(), tfNumberSettings.getName());
        type(addRulePopups.getValueField(), tfNumberSettings.getValue());
        addRulePopups.getButtonSave().click();
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        addRulePopups.getButtonSave().click();
        waiting2seconds();
    }

    public void editRule(TFNumberSettings tfNumberSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        addRulePopups.getNameField().sendKeys(Keys.CONTROL + "a");
        addRulePopups.getNameField().sendKeys(Keys.DELETE);
        type(addRulePopups.getNameField(), tfNumberSettings.getName());
        addRulePopups.getButtonSave().click();
    }

    public void clickAddTermButton() {
        waitUntilElementAppeared(extrasTFNumbersTable.getAddTermButton());
        extrasTFNumbersTable.getAddTermButton().click();
    }

    public void createNewPremiumTerm(TFNumberSettings tfNumberSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        type(addRulePopups.getNameField(), tfNumberSettings.getName());
        type(addRulePopups.getDescriptionField(), tfNumberSettings.getDescription());
        type(addRulePopups.getDiscountField(), tfNumberSettings.getDiscount());
        type(addRulePopups.getDurationField(), tfNumberSettings.getDuration());
        type(addRulePopups.getAdditionalTextField(), tfNumberSettings.getAdditionalText());
        chooseCountry(tfNumberSettings.getCountry());
        addRulePopups.getIsPopularCheckbox().click();
        addRulePopups.getButtonSave().click();
    }

    public void clickEditIcon(TFNumberSettings tfNumberSettings) {
        int indexTd = 0;
        for (int i = 0; i < extrasTFNumbersTable.getListTd().size(); i++) {
            if(extrasTFNumbersTable.getListTd().get(i).getText().equals(tfNumberSettings.getName())){
                indexTd = i;
                break;
            }
        }
        int index = Math.round(indexTd/extrasTFNumbersTable.getListColumnHeader().size()) * 2;
        extrasTFNumbersTable.getListOfActions().get(index).click();

    }

    public void clickDeleteIcon(TFNumberSettings tfNumberSettings) {
        int indexTd = 0;
        for (int i = 0; i < extrasTFNumbersTable.getListTd().size(); i++) {
            if(extrasTFNumbersTable.getListTd().get(i).getText().equals(tfNumberSettings.getName())){
                indexTd = i;
                break;
            }
        }
        int index = (Math.round(indexTd/extrasTFNumbersTable.getListColumnHeader().size()) * 2) + 1;
        scrollToElement(extrasTFNumbersTable.getListOfActions().get(index));
        extrasTFNumbersTable.getListOfActions().get(index).click();
        waiting2seconds();
        extrasTFNumbersTable.getButtonDelete().click();
        waiting2seconds();
    }

    public void chooseCountry(String country) {
        waitUntilElementAppeared(addRulePopups.getSelectOfCounties());
        waiting2seconds();
        addRulePopups.getSelectOfCounties().click();
        waiting2seconds();
        for(WebElement element : addRulePopups.getListOfCountry()) {
            if (element.getText().equals(country)) {
                element.click();
                return;
            }
        }
        addRulePopups.getListOfCountry().get(0).click();
    }

    public void checkingSuccessCreatingNewRule(TFNumberSettings tfNumberSettings) {
        waitUntilElementAppeared(extrasTFNumbersTable.getSuccessAlert());
        boolean successAlert = isElementPresent(extrasTFNumbersTable.getSuccessAlert());
        boolean rule = false;
        for (int i = 0; i < extrasTFNumbersTable.getListTd().size(); i++) {
            if(extrasTFNumbersTable.getListTd().get(i).getText().equals(tfNumberSettings.getName())){
                rule = true;
                break;
            }
        }
        softAssert.assertTrue(rule, "Rule is not added");
        softAssert.assertTrue(successAlert, "Success message is not appeared");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesCreatingPriceTierEmptyFields() {
        waitUntilElementAppeared(addRulePopups.getListOfErrorMessage().get(0));
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(0).getText(), "The name must be a string.The name field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(1).getText(), "The value must be a number.The value must be at least 0.1.The value field is required.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesCreatingPriceTierNameHasBeenUsed() {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        waiting2seconds();
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(0).getText(), "The name has already been taken.");
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted(TFNumberSettings tfNumberSettings) {
        waitUntilElementAppeared(extrasTFNumbersTable.getSuccessAlert());
        boolean successAlert = isElementPresent(extrasTFNumbersTable.getSuccessAlert());
        boolean rule = false;
        for (int i = 0; i < extrasTFNumbersTable.getListTd().size(); i++) {
            if(extrasTFNumbersTable.getListTd().get(i).getText().equals(tfNumberSettings.getName())){
                rule = true;
                break;
            }
        }
        softAssert.assertFalse(rule, "Rule is not deleted");
        softAssert.assertTrue(successAlert, "Success message is not appeared");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesCreatingPremiumTermEmptyFields() {
        waitUntilElementAppeared(addRulePopups.getListOfErrorMessage().get(0));
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(0).getText(), "The name must be a string.The name field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(1).getText(), "The value must be a number.The value field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(5).getText(), "The additional text must be a string.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(7).getText(), "The duration must be a number.The duration field is required.");
        softAssert.assertAll();
    }

}
