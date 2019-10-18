package pages.admin;

import blocks.admin.areaCodes.AreaCodePopup;
import blocks.admin.areaCodes.AreaCodesTable;
import blocks.admin.areaCodes.StateListUS;
import data.AreaCodesData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 10/7/2019.
 */
public class AreaCodesPage extends BasePage {

    public AreaCodesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("adminAreaCodes.url"));
    }

    StateListUS stateListUS;
    AreaCodePopup areaCodePopup;
    AreaCodesTable areaCodesTable;

    public void chooseState(String stateName) {
        waitUntilElementAppeared(stateListUS.getListOfStates().get(0));
        for(WebElement element : stateListUS.getListOfStates()) {
            if (element.getText().equals(stateName)) {
                element.click();
                return;
            }
        }
        stateListUS.getListOfStates().get(0).click();
    }

    public void clickAddAreaCodesButton() {
        waitUntilElementAppeared(areaCodesTable.getAddAreaCodesButton());
        areaCodesTable.getAddAreaCodesButton().click();
    }

    public void addNewAreaCode(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodePopup.getButtonSave());
        type(areaCodePopup.getAreaCodesField(), areaCodesData.getAreaCode1());
        areaCodePopup.getAreaCodesField().sendKeys(Keys.ENTER);
        type(areaCodePopup.getMultiplierField(), areaCodesData.getMultiplier());
        type(areaCodePopup.getGroupNameField(), areaCodesData.getAreaCode1());
        areaCodePopup.getButtonSave().click();
    }

    public void addNewBundleAreaCodes(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodePopup.getButtonSave());
        type(areaCodePopup.getAreaCodesField(), areaCodesData.getAreaCode2());
        areaCodePopup.getAreaCodesField().sendKeys(Keys.ENTER);
        type(areaCodePopup.getAreaCodesField(), areaCodesData.getAreaCode3());
        areaCodePopup.getAreaCodesField().sendKeys(Keys.ENTER);
        type(areaCodePopup.getMultiplierField(), areaCodesData.getMultiplier());
        type(areaCodePopup.getGroupNameField(), areaCodesData.getGroupName());
        areaCodePopup.getButtonSave().click();
    }

    public void editBundle(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodePopup.getButtonSave());
        type(areaCodePopup.getAreaCodesField(), areaCodesData.getAreaCode1());
        areaCodePopup.getAreaCodesField().sendKeys(Keys.ENTER);
        areaCodePopup.getButtonSave().click();
    }

    public void editAreaCode(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodePopup.getButtonSave());
        type(areaCodePopup.getAreaCodesField(), areaCodesData.getAreaCode1());
        areaCodePopup.getAreaCodesField().sendKeys(Keys.ENTER);
        areaCodePopup.getGroupNameField().sendKeys(Keys.CONTROL + "a");
        areaCodePopup.getGroupNameField().sendKeys(Keys.DELETE);
        type(areaCodePopup.getGroupNameField(), areaCodesData.getGroupName());
        areaCodePopup.getButtonSave().click();
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(areaCodePopup.getButtonSave());
        scrollToElement(areaCodePopup.getButtonSave());
        areaCodePopup.getButtonSave().click();
        waiting2seconds();
    }

    public void checkingErrorMessagesAddingAreaCodeEmptyFields() {
        waitUntilElementAppeared(areaCodePopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(areaCodePopup.getListOfErrorMessage().get(1).getText(), "The codes field is required.");
        softAssert.assertEquals(areaCodePopup.getListOfErrorMessage().get(3).getText(), "The name must be a string.");
        softAssert.assertEquals(areaCodePopup.getListOfErrorMessage().get(4).getText(), "The multiplier must be a number.The multiplier must be at least 0.01.The multiplier field is required.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAddingAreaCodeLessThan200() {
        waitUntilElementAppeared(areaCodePopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(areaCodePopup.getListOfErrorMessage().get(1).getText(), "Area codes may not be smaller than 200");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAddingAreaCodeMoreThan999() {
        waitUntilElementAppeared(areaCodePopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(areaCodePopup.getListOfErrorMessage().get(1).getText(), "Area codes may not be greater than 999");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAddingAreaCodeAlreadyUsed(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodePopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(areaCodePopup.getListOfErrorMessage().get(1).getText(), "The area code " + areaCodesData.getAreaCode1() +" is already in use.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAddingBundleAlreadyUsed(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodePopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(areaCodePopup.getListOfErrorMessage().get(1).getText(),
                "The area code " + areaCodesData.getAreaCode2() +" is already in use.The area code " + areaCodesData.getAreaCode3() + " is already in use.");
        softAssert.assertAll();
    }

    public void checkingSuccessAddingNewBundle(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodesTable.getSuccessAlert());
        boolean successAlert = isElementPresent(areaCodesTable.getSuccessAlert());
        boolean areaCode = false;
        waiting2seconds();
        for (int i = 0; i < areaCodesTable.getListTd().size(); i++) {
            if(areaCodesTable.getListTd().get(i).getText().equals(areaCodesData.getAreaCode2() + ", " + areaCodesData.getAreaCode3())){
                areaCode = true;
                break;
            }
        }
        softAssert.assertTrue(areaCode, "AreaCode is not added");
        softAssert.assertTrue(successAlert, "Success message is not appeared");
        softAssert.assertAll();
    }

    public void checkingSuccessAddingNewAreaCode(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodesTable.getSuccessAlert());
        boolean successAlert = isElementPresent(areaCodesTable.getSuccessAlert());
        boolean areaCode = false;
        waiting2seconds();
        for (int i = 0; i < areaCodesTable.getListTd().size(); i++) {
            if(areaCodesTable.getListTd().get(i).getText().equals(areaCodesData.getAreaCode1())){
                areaCode = true;
                break;
            }
        }
        softAssert.assertTrue(areaCode, "AreaCode is not added");
        softAssert.assertTrue(successAlert, "Success message is not appeared");
        softAssert.assertAll();
    }

    public void checkingSuccessEditName(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodesTable.getSuccessAlert());
        boolean successAlert = isElementPresent(areaCodesTable.getSuccessAlert());
        boolean areaCode = false;
        waiting2seconds();
        for (int i = 0; i < areaCodesTable.getListTd().size(); i++) {
            if(areaCodesTable.getListTd().get(i).getText().equals(areaCodesData.getGroupName())){
                areaCode = true;
                break;
            }
        }
        softAssert.assertTrue(areaCode, "Name is incorrect");
        softAssert.assertTrue(successAlert, "Success message is not appeared");
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted(AreaCodesData areaCodesData) {
        waitUntilElementAppeared(areaCodesTable.getSuccessAlert());
        boolean successAlert = isElementPresent(areaCodesTable.getSuccessAlert());
        boolean rule = false;
        waiting2seconds();
        for (int i = 0; i < areaCodesTable.getListTd().size(); i++) {
            if(areaCodesTable.getListTd().get(i).getText().equals(areaCodesData.getGroupName())){
                rule = true;
                break;
            }
        }
        softAssert.assertFalse(rule, "Bundle is not deleted");
        softAssert.assertTrue(successAlert, "Success message is not appeared");
        softAssert.assertAll();
    }

    public void clickEditIcon(AreaCodesData areaCodesData) {
        waiting2seconds();
        for (int i = 0; i < areaCodesTable.getListTd().size(); i++) {
            if(areaCodesTable.getListTd().get(i).getText().equals(areaCodesData.getGroupName())){
                int index = Math.round(i/ areaCodesTable.getListColumnHeader().size()) * 2;
                areaCodesTable.getListOfActions().get(index).click();
                break;
            }
        }
    }

    public void clickDeleteIcon(AreaCodesData areaCodesData) {
        waiting2seconds();
        for (int i = 0; i < areaCodesTable.getListTd().size(); i++) {
            if(areaCodesTable.getListTd().get(i).getText().equals(areaCodesData.getGroupName())){
                int index = (Math.round(i/ areaCodesTable.getListColumnHeader().size()) * 2) + 1;
                scrollToElement(areaCodesTable.getListOfActions().get(index));
                areaCodesTable.getListOfActions().get(index).click();
                break;
            }
        }
        waiting2seconds();
        areaCodesTable.getButtonDelete().click();
        waiting2seconds();
    }
}
