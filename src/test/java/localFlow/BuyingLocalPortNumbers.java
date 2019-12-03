package localFlow;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 6/14/2019.
 */
public class BuyingLocalPortNumbers extends TestBase {

    private HomePage homePage;
    private LocalIndexPage localIndexPage;
    private BuyingLocalNumber buyingLocalNumber;
    private LocalSearchResult localSearchResult;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;

    private String boughtNumber;
    private String searchRequest = "6555";
    private String planName = "Port A Number";

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());

    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void orderLocalPortNumber() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan(planName);
        boughtNumber = buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_34, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumber(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberSold() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(boughtNumber);
        localSearchResult.checkingStatusSold();
    }

    @Test
    public void orderLocalPortNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_33, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumberWithFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_32, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumberWithHighFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_33, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumberWithPercentPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_32, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchasePortNumberAfterRemovePromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberWithoutPickPlan() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = 0.0;
        buyingLocalNumber.clickLinkContinueToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_34, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumber(priceNumber, pricePlan);
    }

    @Test
    public void checkingCorrectInfoOnCheckoutSidebarLocalPortNumberWithoutPickPlan() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumberFirst = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.clickLinkContinueToCheckout();
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseLastNumberFromLocalNumbersList();
        double priceNumberLast = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.clickLinkContinueToCheckout();
        checkout.checkingCorrectInfoInSidebar(priceNumberFirst, priceNumberLast);
    }

    @Test
    public void orderLocalPortNumberPaymentError() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan(planName);
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_34, CreditCards.ERROR_STOLEN_CARD_STRIPE, false);
        checkout.checkingPaymentError();
    }

}
