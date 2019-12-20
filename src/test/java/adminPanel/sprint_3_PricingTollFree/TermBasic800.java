package adminPanel.sprint_3_PricingTollFree;

import data.CreditCards;
import data.PricingTollFreeSettings;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.Admin;
import pages.admin.PricingTollFreePage;
import pages.admin.Login;
import pages.front.BasicIndexPage;
import pages.front.BuyingBasic800Number;
import pages.front.Checkout;
import pages.front.OrderConfirmationPage;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/4/2019.
 */
public class TermBasic800 extends TestBase {

    private Login login;
    private Admin admin;
    private PricingTollFreePage pricingTollFreePage;
    private OrderConfirmationPage orderConfirmationPage;
    private Checkout checkout;
    private BasicIndexPage basicIndexPage;
    private BuyingBasic800Number buyingBasic800Number;
    String tabName = "Term - Basic 800";


    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        pricingTollFreePage = new PricingTollFreePage(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        basicIndexPage = new BasicIndexPage(app.getDriver());
        buyingBasic800Number = new BuyingBasic800Number(app.getDriver());
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
    public void test1SuccessCreatingNewBasic800Term() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickAddTermButton();
        pricingTollFreePage.createNewTermBasic800(PricingTollFreeSettings.TERM_BASIC800_TEST);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.TERM_BASIC800_TEST);
    }

    @Test
    public void test2ErrorCreatingNewBasic800TermAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
    //    login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickAddTermButton();
        pricingTollFreePage.clickSaveButton();
        pricingTollFreePage.checkingErrorMessagesCreatingBasic800TermEmptyFields();
    }

    @Test
    public void test2CheckingCreatedTermFromAdmin() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        buyingBasic800Number.checkingCreatedTermFromAdmin(PricingTollFreeSettings.TERM_BASIC800_TEST);
    }

    @Test
    public void test2OrderBasic800NumberWithCreatedTerm() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan(PricingTollFreeSettings.TERM_BASIC800_TEST.getName());
        buyingBasic800Number.enterRingToNumber("8722413731");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_49, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseBasic800Number(priceActivationFee, pricePlan);
    }

    @Test
    public void test3EditBasic800Term() throws InterruptedException, IOException, JSONException {
   //     login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickEditIcon(PricingTollFreeSettings.TERM_BASIC800_TEST);
        pricingTollFreePage.editRule(PricingTollFreeSettings.TERM_BASIC800_UPDATE);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.TERM_BASIC800_UPDATE);
    }

    @Test
    public void test4DeleteBasic800Term() throws InterruptedException, IOException, JSONException {
     //   login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickDeleteIcon(PricingTollFreeSettings.TERM_BASIC800_UPDATE);
        pricingTollFreePage.checkingSuccessDeleted(PricingTollFreeSettings.TERM_BASIC800_UPDATE, tabName);
    }
}
