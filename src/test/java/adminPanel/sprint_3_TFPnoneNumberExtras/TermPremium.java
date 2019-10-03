package adminPanel.sprint_3_TFPnoneNumberExtras;

import data.CreditCards;
import data.TFNumberSettings;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import pages.admin.Admin;
import pages.admin.ExtrasTFNumbersPage;
import pages.admin.Login;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/3/2019.
 */
public class TermPremium extends TestBase {

    private Login login;
    private Admin admin;
    private ExtrasTFNumbersPage extrasTFNumbersPage;
    private HomePage homePage;
    private VanitySearchResult vanitySearchResult;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;


    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        extrasTFNumbersPage = new ExtrasTFNumbersPage(app.getDriver());
        homePage = new HomePage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        buyingPremiumVanityNumber = new BuyingPremiumVanityNumber(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreatingNewPremiumTerm() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickTab("Term - Premium");
        extrasTFNumbersPage.clickAddTermButton();
        extrasTFNumbersPage.createNewPremiumTerm(TFNumberSettings.TERMS_TEST);
        extrasTFNumbersPage.checkingSuccessCreatingNewRule(TFNumberSettings.TERMS_TEST);
    }

    @Test
    public void test1ErrorCreatingNewPremiumTermAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickTab("Term - Premium");
        extrasTFNumbersPage.clickAddTermButton();
        extrasTFNumbersPage.clickSaveButton();
        extrasTFNumbersPage.checkingErrorMessagesCreatingPremiumTermEmptyFields();
    }

/*    @Test
    public void test3EditPremiumTerm() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickTab("Term - Premium");
        extrasTFNumbersPage.clickEditIcon(TFNumberSettings.TERMS_TEST);
        extrasTFNumbersPage.editRule(TFNumberSettings.TERMS_UPDATE);
        extrasTFNumbersPage.checkingSuccessCreatingNewRule(TFNumberSettings.TERMS_UPDATE);
    }*/

    @Test
    public void test4DeletePremiumTerm() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickExtrasTFNumbersLink();
        extrasTFNumbersPage.clickTab("Term - Premium");
        extrasTFNumbersPage.clickDeleteIcon(TFNumberSettings.TERMS_TEST);
        extrasTFNumbersPage.checkingSuccessDeleted(TFNumberSettings.TERMS_TEST);
    }

    @Test
    public void test1OrderPremiumVanityNumber() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers("4204ER5");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Kansas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(1);
        buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        buyingPremiumVanityNumber.checkingCreatedTermFromAdmin(TFNumberSettings.TERMS_TEST);

    }

}
