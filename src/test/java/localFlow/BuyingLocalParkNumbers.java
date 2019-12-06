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

public class BuyingLocalParkNumbers extends TestBase {

    private HomePage homePage;
    private LocalIndexPage localIndexPage;
    private BuyingLocalNumber buyingLocalNumber;
    private LocalSearchResult localSearchResult;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;

    private String boughtNumber;
    private String searchRequest = "6555";
    private String planName = "Park A Number";


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
    public void orderLocalParkNumber() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        boughtNumber = buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_37, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumber(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalParkNumberSold() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(boughtNumber);
        localSearchResult.checkingStatusSold();
    }

    @Test
    public void orderLocalParkNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_36, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalParkNumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_35, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithHighFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalParkNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_37, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithPercentPromoCode(priceNumber, pricePlan);
    }


    @Test
    public void orderLocalParkNumberAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_36, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberAfterRemovePromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalParkNumberPaymentError() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_35, CreditCards.ERROR_LOST_CARD_STRIPE, false);
        checkout.checkingPaymentError();
    }
}
