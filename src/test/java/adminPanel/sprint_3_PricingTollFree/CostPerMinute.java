package adminPanel.sprint_3_PricingTollFree;

import data.PricingTollFreeSettings;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.*;
import pages.admin.Admin;
import pages.admin.Login;
import pages.admin.PricingTollFreePage;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/4/2019.
 */
public class CostPerMinute extends TestBase {

    private Login login;
    private Admin admin;
    private PricingTollFreePage pricingTollFreePage;
    private HomePage homePage;
    private VanitySearchResult vanitySearchResult;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;


    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        pricingTollFreePage = new PricingTollFreePage(app.getDriver());
        homePage = new HomePage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
        buyingPremiumVanityNumber = new BuyingPremiumVanityNumber(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
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
    public void test1SuccessCreatingNewCostPerMinute() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab("Cost Per Minute");
        pricingTollFreePage.clickAddPriceMinutesButton();
        pricingTollFreePage.createNewPriceMinutes(PricingTollFreeSettings.MINUTES_TEST);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.MINUTES_TEST);
    }

    @Test
    public void test1ErrorCreatingNewCostPerMinuteAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab("Cost Per Minute");
        pricingTollFreePage.clickAddPriceMinutesButton();
        pricingTollFreePage.clickSaveButton();
        pricingTollFreePage.checkingErrorMessagesCreatingPriceMinutesEmptyFields();
    }

    @Test
    public void test2CheckingCreatedTermFromAdminOnPremiumFlow() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers("4204ER5");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Kansas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(1);
        buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        buyingPremiumVanityNumber.chooseTermLength("month");
        buyingRegularVanityNumber.checkingCreatedPriceMinuteFromAdmin(PricingTollFreeSettings.MINUTES_TEST);
    }

    @Test
    public void test2CheckingCreatedTermFromAdminRegularFlow() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers("4204ER5");
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        buyingRegularVanityNumber.checkingCreatedPriceMinuteFromAdmin(PricingTollFreeSettings.MINUTES_TEST);
    }

    @Test
    public void test3EditCostPerMinute() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab("Cost Per Minute");
        pricingTollFreePage.clickEditIcon(PricingTollFreeSettings.MINUTES_TEST);
        pricingTollFreePage.editRule(PricingTollFreeSettings.MINUTES_UPDATE);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.MINUTES_UPDATE);
    }

    @Test
    public void test4DeleteCostPerMinute() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab("Cost Per Minute");
        pricingTollFreePage.clickDeleteIcon(PricingTollFreeSettings.MINUTES_UPDATE);
        pricingTollFreePage.checkingSuccessDeleted(PricingTollFreeSettings.MINUTES_UPDATE);
    }


}
