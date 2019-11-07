package pages.admin;

import blocks.admin.priceMatrix.LocalPhoneTiersTable;
import blocks.admin.priceMatrix.NewLocalTierPopup;
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

    public void fillNewTierForm(String tier, String pricePremium, String pricePatterns, String priceRandom, boolean callForPrice) {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        type(newLocalTierPopup.getTierField(), tier);
        newLocalTierPopup.getPremiumPriceField().sendKeys(Keys.CONTROL + "a");
        newLocalTierPopup.getPremiumPriceField().sendKeys(Keys.DELETE);
        newLocalTierPopup.getPatternPriceField().sendKeys(Keys.CONTROL + "a");
        newLocalTierPopup.getPatternPriceField().sendKeys(Keys.DELETE);
        newLocalTierPopup.getRandomPriceField().sendKeys(Keys.CONTROL + "a");
        newLocalTierPopup.getRandomPriceField().sendKeys(Keys.DELETE);
        type(newLocalTierPopup.getPremiumPriceField(), pricePremium);
        type(newLocalTierPopup.getPatternPriceField(), pricePatterns);
        type(newLocalTierPopup.getRandomPriceField(), priceRandom);
        if(callForPrice)
            newLocalTierPopup.getListCallForPriceCheckbox().get(0).click();
    }

    public void createNewLocalTier(String tier, String pricePremium, String pricePatterns, String priceRandom, boolean callForPrice) {
        fillNewTierForm(tier, pricePremium, pricePatterns, priceRandom, callForPrice);
        waitUntilElementWillBeClickable(newLocalTierPopup.getButtonSave());
        newLocalTierPopup.getButtonSave().click();
    }

    public void clickEditIcon(String tier) {
        for (int i = 0; i < localPhoneTiersTable.getListTd().size(); i++) {
            if(localPhoneTiersTable.getListTd().get(i).getText().equals(tier)){
                int index = Math.round(i/ localPhoneTiersTable.getListColumnHeader().size()) * 2;
                localPhoneTiersTable.getListOfActionsTiers().get(index).click();
                break;
            }
        }
    }

    public void clickDeleteIcon(String tier) {
        for (int i = 0; i < localPhoneTiersTable.getListTd().size(); i++) {
            if(localPhoneTiersTable.getListTd().get(i).getText().equals(tier)){
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

    public void editTier(String tierName, boolean callForPrice) {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        newLocalTierPopup.getTierField().sendKeys(Keys.CONTROL + "a");
        type(newLocalTierPopup.getTierField(), tierName);
        if(callForPrice)
            newLocalTierPopup.getListCallForPriceCheckbox().get(0).click();
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
        softAssert.assertAll();
    }

    public void checkingAbsentCategorySelect() {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        softAssert.assertFalse(isElementPresent(newLocalTierPopup.getSelectCategory()));
        softAssert.assertAll();
    }

    public void checkingPresentCategorySelect() {
        waitUntilElementAppeared(newLocalTierPopup.getButtonSave());
        boolean tt = isElementPresent(newLocalTierPopup.getSelectCategory());
        softAssert.assertTrue(isElementPresent(newLocalTierPopup.getSelectCategory()));
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
        softAssert.assertEquals(newLocalTierPopup.getListOfErrorMessage().get(0).getText(), "The tier has already been taken.");
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted() {
        waitUntilElementAppeared(localPhoneTiersTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(localPhoneTiersTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void checkingSuccessEditNewTier(String tier) {
        waitUntilElementAppeared(localPhoneTiersTable.getSuccessAlert());
        boolean successAlert = isElementContainsAttributeValue(localPhoneTiersTable.getSuccessAlert(), "style", "display");
        int indexTd = 0;
        for (int i = 0; i < localPhoneTiersTable.getListTd().size(); i++) {
            if(localPhoneTiersTable.getListTd().get(i).getText().equals(tier)){
                indexTd = i;
                break;
            }
        }

        softAssert.assertEquals(localPhoneTiersTable.getListTd().get(indexTd).getText(), tier);
        softAssert.assertEquals(localPhoneTiersTable.getListTd().get(indexTd + 1).getText(), "call for price");
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
