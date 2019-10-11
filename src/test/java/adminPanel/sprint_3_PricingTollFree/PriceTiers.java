package adminPanel.sprint_3_PricingTollFree;

import data.PricingTollFreeSettings;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/1/2019.
 */
public class PriceTiers extends TestBase {

    private Login login;
    private Admin admin;
    private PricingTollFreePage pricingTollFreePage;

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        pricingTollFreePage = new PricingTollFreePage(app.getDriver());
    }

    @BeforeMethod
    public void login() {
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreatingNewPriceTier() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickAddNewTierButton();
        pricingTollFreePage.createNewTier(PricingTollFreeSettings.PRICE_TIER_TEST);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.PRICE_TIER_TEST);
    }

    @Test
    public void test1ErrorCreatingNewPriceTierAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
    //    login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickAddNewTierButton();
        pricingTollFreePage.clickSaveButton();
        pricingTollFreePage.checkingErrorMessagesCreatingPriceTierEmptyFields();
    }

    @Test
    public void test2ErrorCreatingNewPriceTierSameName() throws InterruptedException, IOException, JSONException {
  //      login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickAddNewTierButton();
        pricingTollFreePage.createNewTier(PricingTollFreeSettings.PRICE_TIER_TEST);
        pricingTollFreePage.checkingErrorMessagesCreatingPriceTierNameHasBeenUsed();
    }

    @Test
    public void test3EditPriceTier() throws InterruptedException, IOException, JSONException {
   //     login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickEditIcon(PricingTollFreeSettings.PRICE_TIER_TEST);
        pricingTollFreePage.editRule(PricingTollFreeSettings.PRICE_TIER_UPDATE);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.PRICE_TIER_UPDATE);
    }

    @Test
    public void test4DeletePriceTier() throws InterruptedException, IOException, JSONException {
 //       login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickDeleteIcon(PricingTollFreeSettings.PRICE_TIER_UPDATE);
        pricingTollFreePage.checkingSuccessDeleted(PricingTollFreeSettings.PRICE_TIER_UPDATE);
    }
}
