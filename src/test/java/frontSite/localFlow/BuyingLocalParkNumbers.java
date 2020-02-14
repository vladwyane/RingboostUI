package frontSite.localFlow;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import io.qameta.allure.Story;
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
    private LocalStateDetail localStateDetail;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private ContactUsPage contactUsPage;

    private String boughtNumber;
    private String searchRequest = "4445555";
    private String planName = "Park A Number";


    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
        localStateDetail = new LocalStateDetail(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        contactUsPage = new ContactUsPage(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test(description = "Order Local Number with Default Price Plan and without Promo Code")
    @Story("status: 32, carrier_id: 45, call_for_price: false, port_date: 2020-01-24, port_status: 16, featured: true, price_override: 12.99")
    public void test1OrderLocalParkNumber() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(1);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        boughtNumber = buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_55, CreditCards.VISA_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseParkNumber(priceNumber, pricePlan);
    }

    @Test(description = "Checking status Sold by phone number")
    @Story("Checking status Sold phone number ")
    public void test2OrderLocalParkNumberSold() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.enterAreaCode(boughtNumber.substring(0, 3));
        localIndexPage.searchLocalNumbers(boughtNumber.substring(3));
        localSearchResult.checkingStatusSold();
    }

    @Test(description = "Order Local Number with Default Price Plan and with Fixed Promo Code")
    @Story("status: 32, carrier_id: 44, call_for_price: false, acquisition_date: 2020-02-09, call_forward: true, city: Miami, price_override: 7")
    public void test3OrderLocalParkNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(2);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_54, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithFixedPromoCode(priceNumber, pricePlan);
    }

    @Test(description = "Order Local Number with Default Price Plan and with High Fixed Promo Code")
    @Story("status: 32, carrier_id: 43, call_for_price: false, rev_share: 12, call_forward: false, port_status: 32, price_override: 1255.99")
    public void test4OrderLocalParkNumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(3);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_56, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithHighFixedPromoCode(priceNumber, pricePlan);
    }

    @Test(description = "Order Local Number with Default Price Plan and with Percent Promo Code")
    @Story("status: 32, carrier_id: null, call_for_price: false, rev_share: 99, call_forward: true, port_date: 2020-04-02, price_override: 999.01")
    public void test5OrderLocalParkNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(4);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_55, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithPercentPromoCode(priceNumber, pricePlan);
    }


    @Test(description = "Order Local Number with Default Price Plan and after remove Promo Code")
    @Story("status: 32, carrier_id: null, call_for_price: false, city: New York, port_date: 2020-04-03, acquisition_date: 2020-02-09, price_override: 299")
    public void test6OrderLocalParkNumberAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(5);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_54, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberAfterRemovePromoCode(priceNumber, pricePlan);
    }

    @Test(description = "Order Local Number with Default Price Plan and Payment Error Lost Card Stripe")
    @Story("status: 32, call_for_price: false, disable_coupon: true, notes: Test notes, price_override: 101")
    public void test7OrderLocalParkNumberPaymentError() throws InterruptedException {
        homePage.open();
        homePage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(6);
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_56, CreditCards.ERROR_LOST_CARD_STRIPE, false);
        checkout.checkingPaymentError();
    }

    @Test(description = "Checking Disable Coupon Checkbox")
    @Story("Checking disabled coupon field on checkout")
    public void test7CheckingDisableCouponCheckbox() throws InterruptedException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(6);
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.checkingDisableCouponField();
    }

    @Test(description = "Checking Make Offer Link")
    @Story("Checking Make Offer Link on listing phone numbers")
    public void test7CheckingMakeOfferLink() throws InterruptedException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.clickMakeOfferLink(6);
        buyingLocalNumber.checkingVisibilityMakeOfferForm();
    }

    @Test(description = "Checking Call For Price Checkbox")
    @Story("Checking Call For Price Checkbox")
    public void test8CheckingCallForPriceCheckbox() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(7);
        contactUsPage.checkingCorrectRedirectToContactUsPage();
    }

    @Test(description = "Checking Call For Price Premium PriceTier")
    @Story("Checking Call For Price Premium PriceTier")
    public void test9CheckingCallForPricePremiumPriceTier() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(8);
        contactUsPage.checkingCorrectRedirectToContactUsPage();
    }
}
