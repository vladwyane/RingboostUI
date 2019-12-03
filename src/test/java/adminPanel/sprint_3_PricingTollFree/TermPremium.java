package adminPanel.sprint_3_PricingTollFree;

import data.CreditCards;
import data.PricingTollFreeSettings;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.Admin;
import pages.admin.PricingTollFreePage;
import pages.admin.Login;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/3/2019.
 */
public class TermPremium extends TestBase {

    private Login login;
    private Admin admin;
    private PricingTollFreePage pricingTollFreePage;
    private HomePage homePage;
    private VanitySearchResult vanitySearchResult;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    String tabName = "Term - Premium";


    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        pricingTollFreePage = new PricingTollFreePage(app.getDriver());
        homePage = new HomePage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
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
    public void test1SuccessCreatingNewPremiumTerm() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickAddTermButton();
        pricingTollFreePage.createNewPremiumTerm(PricingTollFreeSettings.TERM_PREMIUM_TEST);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.TERM_PREMIUM_TEST);
    }

    @Test
    public void test1ErrorCreatingNewPremiumTermAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickAddTermButton();
        pricingTollFreePage.clickSaveButton();
        pricingTollFreePage.checkingErrorMessagesCreatingPremiumTermEmptyFields();
    }

    @Test
    public void test2CheckingCreatedTermFromAdmin() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers("4204ER5");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Kansas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(1);
        buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        buyingPremiumVanityNumber.checkingCreatedTermFromAdmin(PricingTollFreeSettings.TERM_PREMIUM_TEST);
    }

    @Test
    public void test2OrderPremiumVanityNumberWithCreatedTerm() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers("4204ER5");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Alabama");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(1);
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength(PricingTollFreeSettings.TERM_PREMIUM_TEST.getName());
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose5000MonthlyMinutes();
        buyingPremiumVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingPremiumVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_33, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void test3EditPremiumTerm() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickEditIcon(PricingTollFreeSettings.TERM_PREMIUM_TEST);
        pricingTollFreePage.editRule(PricingTollFreeSettings.TERM_PREMIUM_UPDATE);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.TERM_PREMIUM_UPDATE);
    }

    @Test
    public void test4DeletePremiumTerm() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickDeleteIcon(PricingTollFreeSettings.TERM_PREMIUM_UPDATE);
        pricingTollFreePage.checkingSuccessDeleted(PricingTollFreeSettings.TERM_PREMIUM_UPDATE, tabName);
    }
}
