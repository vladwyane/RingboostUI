package pages.admin;

import blocks.admin.priceMatrix.LocalPhoneTiersTable;
import blocks.admin.priceMatrix.NewLocalTierPopup;
import data.OwnersData;
import data.PricingTollFreeSettings;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by bigdrop on 10/21/2019.
 */
public class PriceMatrixPage extends BasePage {

    public PriceMatrixPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    LocalPhoneTiersTable localPhoneTiersTable;
    NewLocalTierPopup newLocalTierPopup;


    public void clickAddPhoneTierButton() {
        waitUntilElementAppeared(localPhoneTiersTable.getButtonAddPhoneTier());
        waitUntilElementWillBeClickable(localPhoneTiersTable.getButtonAddPhoneTier());
        localPhoneTiersTable.getButtonAddPhoneTier().click();
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        waitUntilElementWillBeClickable(newLocalTierPopup.getButtonSave());
        newLocalTierPopup.getButtonSave().click();
        waiting2seconds();
    }

    public void clickCancelButton() {
        waitUntilElementAppeared(newLocalTierPopup.getButtonCancel());
        waitUntilElementWillBeClickable(newLocalTierPopup.getButtonCancel());
        newLocalTierPopup.getButtonCancel().click();
    }

    public void fillNewTierForm(String tier, String level, String price, boolean callForPrice) {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        type(newLocalTierPopup.getTierField(), tier);
        type(newLocalTierPopup.getLevelField(), level);
        type(newLocalTierPopup.getPriceField(), price);
        if(callForPrice)
            newLocalTierPopup.getCallForPriceCheckbox().click();
    }

    public void createNewLocalTier(String tier, String level, String price, boolean callForPrice) {
        fillNewTierForm(tier, level, price, callForPrice);
        waitUntilElementWillBeClickable(newLocalTierPopup.getButtonSave());
        newLocalTierPopup.getButtonSave().click();
    }

    public void clickEditIcon(String tierName, String level) {
        for (int i = 0; i < localPhoneTiersTable.getListTitleTiers().size(); i++) {
            if(localPhoneTiersTable.getListTitleTiers().get(i).getText().equals(tierName)){
                localPhoneTiersTable.getListTitleTiers().get(i).click();
                break;
            }
        }
        waiting2seconds();
        for (int i = 0; i < localPhoneTiersTable.getListTd().size(); i++) {
            if(localPhoneTiersTable.getListTd().get(i).getText().equals(level)){
                int index = Math.round(i/ localPhoneTiersTable.getListColumnHeader().size()) * 2;
                localPhoneTiersTable.getListOfActionsTiers().get(index).click();
                break;
            }
        }
    }

    public void clickTierOpenClose(String tierName) {
        for (int i = 0; i < localPhoneTiersTable.getListTitleTiers().size(); i++) {
            if(localPhoneTiersTable.getListTitleTiers().get(i).getText().equals(tierName)){
                localPhoneTiersTable.getListTitleTiers().get(i).click();
                break;
            }
        }
    }

    public void clickDeleteIcon(String tierName, String level) {
        for (int i = 0; i < localPhoneTiersTable.getListTitleTiers().size(); i++) {
            if(localPhoneTiersTable.getListTitleTiers().get(i).getText().equals(tierName)){
                localPhoneTiersTable.getListTitleTiers().get(i).click();
                break;
            }
        }
        waiting2seconds();
        for (int i = 0; i < localPhoneTiersTable.getListTd().size(); i++) {
            if(localPhoneTiersTable.getListTd().get(i).getText().equals(level)){
                int index = (Math.round(i/ localPhoneTiersTable.getListColumnHeader().size()) * 2) + 1;
                scrollToElement(localPhoneTiersTable.getListOfActionsTiers().get(index));
                localPhoneTiersTable.getListOfActionsTiers().get(index).click();
                break;
            }
        }
        waiting2seconds();
        localPhoneTiersTable.getButtonDelete().click();
        waiting2seconds();
    }

    public void editTier(String tierName, String level) {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        newLocalTierPopup.getTierField().sendKeys(Keys.CONTROL + "a");
        newLocalTierPopup.getTierField().sendKeys(Keys.DELETE);
        newLocalTierPopup.getLevelField().sendKeys(Keys.CONTROL + "a");
        newLocalTierPopup.getLevelField().sendKeys(Keys.DELETE);
        type(newLocalTierPopup.getTierField(), tierName);
        type(newLocalTierPopup.getLevelField(), level);
        newLocalTierPopup.getButtonSave().click();
    }


    public void checkingSuccessAlertMessage() {
        waitUntilElementAppeared(localPhoneTiersTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(localPhoneTiersTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAllFieldsIsEmpty() {
        waitUntilElementAppeared(newLocalTierPopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(newLocalTierPopup.getListOfErrorMessage().get(0).getText(), "The tier must be a string.The tier must be at least 1 characters.The tier field is required.");
        softAssert.assertEquals(newLocalTierPopup.getListOfErrorMessage().get(1).getText(), "The level must be an integer.The level must be at least 1.The level field is required.");
        softAssert.assertEquals(newLocalTierPopup.getListOfErrorMessage().get(2).getText(), "The price must be a number.The price field is required.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesIsAbsent() {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        waiting2seconds();
        softAssert.assertTrue(isElementInvisible(newLocalTierPopup.getListOfErrorMessage().get(0)));
        softAssert.assertAll();
    }

    public void checkingErrorMessagesLevelAlreadyUsed() {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        waiting2seconds();
        softAssert.assertEquals(newLocalTierPopup.getListOfErrorMessage().get(1).getText(), "The level has already been taken.");
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted() {
        waitUntilElementAppeared(localPhoneTiersTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(localPhoneTiersTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertEquals(localPhoneTiersTable.getListTd().size(), 0);
        softAssert.assertAll();
    }

    public void checkingSuccessEditNewTier(String tier, String level) {
        waitUntilElementAppeared(localPhoneTiersTable.getSuccessAlert());
        boolean successAlert = isElementContainsAttributeValue(localPhoneTiersTable.getSuccessAlert(), "style", "display");
        boolean levelResult = false;
        boolean tierResult = false;
        for (int i = 0; i < localPhoneTiersTable.getListTitleTiers().size(); i++) {
            if(localPhoneTiersTable.getListTitleTiers().get(i).getText().equals(tier)){
                tierResult = true;
                break;
            }
        }
        for (int i = 0; i < localPhoneTiersTable.getListTd().size(); i++) {
            if(localPhoneTiersTable.getListTd().get(i).getText().equals(level)){
                levelResult = true;
                break;
            }
        }
        softAssert.assertTrue(levelResult, "Level not found");
        softAssert.assertTrue(tierResult, "Tier not found");
        softAssert.assertFalse(successAlert, "Success message is not appeared");
        softAssert.assertAll();
    }

    public void checkingSuccessChangingCallForPrice(String callForPrice) {
        boolean callForPriceYes = false;
        for (int i = 0; i < localPhoneTiersTable.getListTd().size(); i++) {
            if(localPhoneTiersTable.getListTd().get(i).getText().equals(callForPrice)){
                callForPriceYes = true;
                break;
            }
        }
        softAssert.assertTrue(callForPriceYes, "CallForPrice not yes");
        softAssert.assertAll();
    }

}
