package pages.admin;

import blocks.admin.pricingTollFree.*;
import data.PricingTollFreeSettings;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Created by bigdrop on 9/12/2019.
 */
public class PricingTollFreePage extends BasePage {

    public PricingTollFreePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    AddRulePopups addRulePopups;
    PricingTollFreeTable pricingTollFreeTable;

    public void clickTab(String tabName) {
        waitUntilElementAppeared(pricingTollFreeTable.getListOfTabs().get(0));
        for(WebElement element : pricingTollFreeTable.getListOfTabs()) {
            if (element.getText().equals(tabName.toUpperCase())) {
                element.click();
                return;
            }
        }
        addRulePopups.getListOfCountry().get(0).click();
    }

    public void clickAddNewTierButton() {
        waitUntilElementAppeared(pricingTollFreeTable.getAddTierButton());
        pricingTollFreeTable.getAddTierButton().click();
    }

    public void createNewTier(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        type(addRulePopups.getNameField(), pricingTollFreeSettings.getName());
        type(addRulePopups.getValueField(), pricingTollFreeSettings.getValue());
        addRulePopups.getButtonSave().click();
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        scrollToElement(addRulePopups.getButtonSave());
        addRulePopups.getButtonSave().click();
        waiting2seconds();
    }

    public void editRule(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        addRulePopups.getNameField().sendKeys(Keys.CONTROL + "a");
        addRulePopups.getNameField().sendKeys(Keys.DELETE);
        type(addRulePopups.getNameField(), pricingTollFreeSettings.getName());
        addRulePopups.getButtonSave().click();
    }

    public void clickAddTermButton() {
        waitUntilElementAppeared(pricingTollFreeTable.getAddTermButton());
        pricingTollFreeTable.getAddTermButton().click();
    }

    public void createNewPremiumTerm(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        type(addRulePopups.getNameField(), pricingTollFreeSettings.getName());
        type(addRulePopups.getDescriptionField(), pricingTollFreeSettings.getDescription());
        type(addRulePopups.getDiscountField(), pricingTollFreeSettings.getDiscount());
        type(addRulePopups.getDurationField(), pricingTollFreeSettings.getDuration());
        type(addRulePopups.getAdditionalTextField(), pricingTollFreeSettings.getAdditionalText());
        chooseCountry(pricingTollFreeSettings.getCountry());
        addRulePopups.getIsPopularCheckbox().click();
        addRulePopups.getButtonSave().click();
    }

    public void createNewTermBasic800(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        type(addRulePopups.getNameField(), pricingTollFreeSettings.getName());
        type(addRulePopups.getDescriptionField(), pricingTollFreeSettings.getDescription());
        type(addRulePopups.getValueField(), pricingTollFreeSettings.getValue());
        type(addRulePopups.getDurationField(), pricingTollFreeSettings.getDuration());
        type(addRulePopups.getAdditionalTextField(), pricingTollFreeSettings.getAdditionalText());
        type(addRulePopups.getAdditionalCoastField(), pricingTollFreeSettings.getAdditionalCoast());
        type(addRulePopups.getActivationFeeField(), pricingTollFreeSettings.getActivationFee());
        chooseCountry(pricingTollFreeSettings.getCountry());
        addRulePopups.getIsPopularCheckbox().click();
        addRulePopups.getButtonSave().click();
    }

    public void clickEditIcon(PricingTollFreeSettings pricingTollFreeSettings) {
        int indexTd = 0;
        for (int i = 0; i < pricingTollFreeTable.getListTd().size(); i++) {
            if(pricingTollFreeTable.getListTd().get(i).getText().equals(pricingTollFreeSettings.getName())){
                indexTd = i;
                break;
            }
        }
        int index = Math.round(indexTd/ pricingTollFreeTable.getListColumnHeader().size()) * 2;
        pricingTollFreeTable.getListOfActions().get(index).click();

    }

    public void clickDeleteIcon(PricingTollFreeSettings pricingTollFreeSettings) {
        int indexTd = 0;
        for (int i = 0; i < pricingTollFreeTable.getListTd().size(); i++) {
            if(pricingTollFreeTable.getListTd().get(i).getText().equals(pricingTollFreeSettings.getName())){
                indexTd = i;
                break;
            }
        }
        int index = (Math.round(indexTd/ pricingTollFreeTable.getListColumnHeader().size()) * 2) + 1;
        scrollToElement(pricingTollFreeTable.getListOfActions().get(index));
        pricingTollFreeTable.getListOfActions().get(index).click();
        waiting2seconds();
        pricingTollFreeTable.getButtonDelete().click();
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

    public void clickAddPriceMinutesButton() {
        waitUntilElementAppeared(pricingTollFreeTable.getAddPriceMinutesButton());
        pricingTollFreeTable.getAddPriceMinutesButton().click();
    }

    public void createNewPriceMinutes(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        type(addRulePopups.getNameField(), pricingTollFreeSettings.getName());
        type(addRulePopups.getDescriptionField(), pricingTollFreeSettings.getDescription());
        type(addRulePopups.getValueField(), pricingTollFreeSettings.getValue());
        type(addRulePopups.getPriceField(), pricingTollFreeSettings.getPrice());
        type(addRulePopups.getPricePerMinuteField(), pricingTollFreeSettings.getPricePerMinute());
        addRulePopups.getButtonSave().click();
    }

    public void clickAddPriceMultipleAreasButton() {
        waitUntilElementAppeared(pricingTollFreeTable.getAddPriceMultipleAreasButton());
        pricingTollFreeTable.getAddPriceMultipleAreasButton().click();
    }

    public void createNewPriceMultipleAreas(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        type(addRulePopups.getNumberField(), pricingTollFreeSettings.getNumber());
        type(addRulePopups.getDescriptionField(), pricingTollFreeSettings.getDescription());
        type(addRulePopups.getValueField(), pricingTollFreeSettings.getValue());
        addRulePopups.getIsActiveCheckbox().click();
        addRulePopups.getButtonSave().click();
    }

    public void editMultipleAreas(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        addRulePopups.getNumberField().sendKeys(Keys.CONTROL + "a");
        addRulePopups.getNumberField().sendKeys(Keys.DELETE);
        type(addRulePopups.getNumberField(), pricingTollFreeSettings.getNumber());
        addRulePopups.getContactUsCheckbox().click();
        addRulePopups.getButtonSave().click();
    }

    public void checkingSuccessCreatingNewRule(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(pricingTollFreeTable.getSuccessAlert());
        boolean successAlert = isElementPresent(pricingTollFreeTable.getSuccessAlert());
        boolean rule = false;
        for (int i = 0; i < pricingTollFreeTable.getListTd().size(); i++) {
            if(pricingTollFreeTable.getListTd().get(i).getText().equals(pricingTollFreeSettings.getName())){
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

    public void checkingSuccessDeleted(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementAppeared(pricingTollFreeTable.getSuccessAlert());
        boolean successAlert = isElementPresent(pricingTollFreeTable.getSuccessAlert());
        boolean rule = false;
        for (int i = 0; i < pricingTollFreeTable.getListTd().size(); i++) {
            if(pricingTollFreeTable.getListTd().get(i).getText().equals(pricingTollFreeSettings.getName())){
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
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(1).getText(), "The value must be a number.Value can`t be decimalThe value field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(7).getText(), "The duration must be a number.The duration field is required.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesCreatingBasic800TermEmptyFields() {
        waitUntilElementAppeared(addRulePopups.getListOfErrorMessage().get(0));
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(0).getText(), "The name must be a string.The name field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(1).getText(), "The value must be a number.Value can`t be decimalThe value field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(4).getText(), "The additional cost must be a number.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(6).getText(), "The activation fee must be a number.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(7).getText(), "The duration must be a number.The duration field is required.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesCreatingPriceMinutesEmptyFields() {
        waitUntilElementAppeared(addRulePopups.getListOfErrorMessage().get(0));
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(0).getText(), "The name must be a string.The name field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(1).getText(), "The value must be an integer.The value field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(2).getText(), "The price must be a number.The price field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(3).getText(), "The price per minute must be a number.The price per minute field is required.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesCreatingMultipleAreasEmptyFields() {
        waitUntilElementAppeared(addRulePopups.getListOfErrorMessage().get(0));
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(0).getText(), "The number field is required.");
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(1).getText(), "The value must be a number.The value field is required.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesCreatingMultipleAreasNameHasBeenUsed() {
        waitUntilElementAppeared(addRulePopups.getButtonSave());
        waiting2seconds();
        softAssert.assertEquals(addRulePopups.getListOfErrorMessage().get(0).getText(), "The number has already been taken.");
        softAssert.assertAll();
    }

}
