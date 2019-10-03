package adminPanel.sprint_3_TFPnoneNumberExtras;

import data.TFNumberSettings;
import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/1/2019.
 */
public class PriceTiers extends TestBase {

    private Login login;
    private Admin admin;
    private ExtrasTFNumbersPage extrasTFNumbersPage;


    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        extrasTFNumbersPage = new ExtrasTFNumbersPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreatingNewPriceTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickAddNewTierButton();
        extrasTFNumbersPage.createNewTier(TFNumberSettings.PRICE_TIER_TEST);
        extrasTFNumbersPage.checkingSuccessCreatingNewRule(TFNumberSettings.PRICE_TIER_TEST);
    }

    @Test
    public void test1ErrorCreatingNewPriceTierAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickAddNewTierButton();
        extrasTFNumbersPage.clickSaveButton();
        extrasTFNumbersPage.checkingErrorMessagesCreatingPriceTierEmptyFields();
    }

    @Test
    public void test2ErrorCreatingNewPriceTierSameName() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickAddNewTierButton();
        extrasTFNumbersPage.createNewTier(TFNumberSettings.PRICE_TIER_TEST);
        extrasTFNumbersPage.checkingErrorMessagesCreatingPriceTierNameHasBeenUsed();
    }

    @Test
    public void test3EditPriceTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickEditIcon(TFNumberSettings.PRICE_TIER_TEST);
        extrasTFNumbersPage.editRule(TFNumberSettings.PRICE_TIER_UPDATE);
        extrasTFNumbersPage.checkingSuccessCreatingNewRule(TFNumberSettings.PRICE_TIER_UPDATE);
    }

    @Test
    public void test4DeletePriceTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickDeleteIcon(TFNumberSettings.PRICE_TIER_UPDATE);
        extrasTFNumbersPage.checkingSuccessDeleted(TFNumberSettings.PRICE_TIER_UPDATE);
    }
}
