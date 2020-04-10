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
    private LocalStateDetail localStateDetail;
    private ContactUsPage contactUsPage;

    private String boughtNumber;
    private String searchRequest = "POLOS";
    private String planName = "Port A Number";

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        localStateDetail = new LocalStateDetail(app.getDriver());
        contactUsPage = new ContactUsPage(app.getDriver());

    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test(description = "Order Local Number without Price Plan and without Promo Code")
    @Story("status: 32, carrier_id: 45, call_for_price: false, port_date: 2020-09-20, port_status: 16, popular: true, price_override: 23.5")
    public void test1OrderLocalPortNumber() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(1);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        boughtNumber = buyingLocalNumber.goToCheckout();
       /* checkout.fillCheckout(Users.VLADYSLAV_68, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumber(priceNumber, pricePlan);*/
    }

    @Test(description = "Checking status Sold by phone number")
    @Story("Checking status Sold phone number ")
    public void test2OrderLocalPortNumberSold() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.enterAreaCode(boughtNumber.substring(0, 3));
        localIndexPage.searchLocalNumbers(boughtNumber.substring(3));
/*        localSearchResult.checkingStatusSold();*/
    }

    @Test(description = "Order Local Number without Price Plan and with Fixed Promo Code")
    @Story("status: 32, carrier_id: 44, call_for_price: false, acquisition_date: 2020-02-09, call_forward: true, city: Washington, price_override: 39.44")
    public void test3OrderLocalPortNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(2);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
 /*       checkout.fillCheckout(Users.VLADYSLAV_67, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchasePortNumberWithFixedPromoCode(priceNumber, pricePlan);*/
    }

    @Test(description = "Order Local Number without Price Plan and with High Fixed Promo Code")
    @Story("status: 32, carrier_id: 43, call_for_price: false, rev_share: 12, call_forward: false, port_status: 32, price_override: 2000.01")
    public void test4OrderLocalPortNumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(3);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
/*        checkout.fillCheckout(Users.VLADYSLAV_66, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumberWithHighFixedPromoCode(priceNumber, pricePlan);*/
    }

    @Test(description = "Order Local Number without Price Plan and with Percent Promo Code")
    @Story("status: 32, carrier_id: null, call_for_price: false, rev_share: 99, call_forward: true, port_date: 2020-04-02, price_override: 900")
    public void test5OrderLocalPortNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(4);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
/*        checkout.fillCheckout(Users.VLADYSLAV_67, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumberWithPercentPromoCode(priceNumber, pricePlan);*/
    }

    @Test(description = "Order Local Number without Price Plan and after remove Promo Code")
    @Story("status: 32, carrier_id: null, call_for_price: false, city: New York, port_date: 2020-11-11, acquisition_date: 2020-12-12, price_override: 4.01")
    public void test6OrderLocalPortNumberAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(5);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.FIXED_PROMOCODE.getName());
/*        checkout.fillCheckout(Users.VLADYSLAV_66, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchasePortNumberAfterRemovePromoCode(priceNumber, pricePlan);*/
    }

    @Test(description = "Order Local Number without Price Plan go to checkout at once")
    @Story("status: 32, carrier_id: 45, call_for_price: false, port_date: 2020-01-20, port_status: 16, popular: true, price_override: 311.32")
    public void test7OrderLocalPortNumberWithoutPickPlan() throws InterruptedException, IOException, JSONException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(6);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = 0.0;
        buyingLocalNumber.clickLinkContinueToCheckout();
/*        checkout.fillCheckout(Users.VLADYSLAV_68, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumber(priceNumber, pricePlan);*/
    }

    @Test(description = "Checking Correct Info On Checkout Sidebar Local Port Number Without Pick Plan")
    @Story("Checking Correct Info On Checkout Sidebar Local Port Number Without Pick Plan")
    public void test8CheckingCorrectInfoOnCheckoutSidebarLocalPortNumberWithoutPickPlan() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(7);
        double priceNumberFirst = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.clickLinkContinueToCheckout();
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(8);
        double priceNumberLast = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.clickLinkContinueToCheckout();
        checkout.checkingCorrectInfoInSidebar(priceNumberFirst, priceNumberLast);
    }

    @Test(description = "Order Local Number with Default Price Plan and Payment Error Stolen Card Stripe")
    @Story("status: 32, call_for_price: false, disable_coupon: true, weight: 12, notes: Test notes, price_override: 221")
    public void test8OrderLocalPortNumberPaymentError() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(7);
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_68, CreditCards.ERROR_STOLEN_CARD_STRIPE, false);
        checkout.checkingPaymentError();
    }

    @Test(description = "Checking Disable Coupon Checkbox")
    @Story("Checking disabled coupon field on checkout")
    public void test8CheckingDisableCouponCheckbox() throws InterruptedException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(7);
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.goToCheckout();
        checkout.checkingDisableCouponField();
    }

    @Test(description = "Checking Make Offer Link")
    @Story("Checking Make Offer Link on listing phone numbers")
    public void test8CheckingMakeOfferLink() throws InterruptedException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.clickMakeOfferLink(7);
        buyingLocalNumber.checkingVisibilityMakeOfferForm();
    }

    @Test(description = "Checking Call For Price Checkbox")
    @Story("Checking Call For Price Checkbox")
    public void test9CheckingCallForPriceCheckbox() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(9);
        contactUsPage.checkingCorrectRedirectToContactUsPage();
    }

    @Test(description = "Checking Call For Price Premium PriceTier")
    @Story("Checking Call For Price Premium PriceTier")
    public void test9CheckingCallForPricePremiumPriceTier() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(10);
        contactUsPage.checkingCorrectRedirectToContactUsPage();
    }

}
