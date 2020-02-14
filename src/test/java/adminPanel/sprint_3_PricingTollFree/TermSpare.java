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
 * Created by bigdrop on 10/4/2019.
 */
public class TermSpare extends TestBase{

    private Login login;
    private Admin admin;
    private PricingTollFreePage pricingTollFreePage;
    private HomePage homePage;
    private VanitySearchResult vanitySearchResult;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    String tabName = "Term - Spare";


    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        pricingTollFreePage = new PricingTollFreePage(app.getDriver());
        homePage = new HomePage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
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
    public void test1SuccessCreatingNewSpareTerm() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickAddTermButton();
        pricingTollFreePage.createNewPremiumTerm(PricingTollFreeSettings.TERM_SPARE_TEST);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.TERM_SPARE_TEST);
    }

    @Test
    public void test1ErrorCreatingNewSpareTermAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
      //  login.open();
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
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        buyingRegularVanityNumber.choose5000MonthlyMinutes();
        buyingRegularVanityNumber.checkingCreatedTermFromAdmin(PricingTollFreeSettings.TERM_SPARE_TEST);
    }

    @Test
    public void test2OrderRegularVanityNumberWithCreatedTerm() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers("4204ER5");
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(PricingTollFreeSettings.TERM_SPARE_TEST.getName());
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_56, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void test3EditSpareTerm() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickEditIcon(PricingTollFreeSettings.TERM_SPARE_TEST);
        pricingTollFreePage.editRule(PricingTollFreeSettings.TERM_SPARE_UPDATE);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.TERM_SPARE_UPDATE);
    }

    @Test
    public void test4DeleteSpareTerm() throws InterruptedException, IOException, JSONException {
    //    login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickDeleteIcon(PricingTollFreeSettings.TERM_SPARE_UPDATE);
        pricingTollFreePage.checkingSuccessDeleted(PricingTollFreeSettings.TERM_SPARE_UPDATE, tabName);
    }
}
