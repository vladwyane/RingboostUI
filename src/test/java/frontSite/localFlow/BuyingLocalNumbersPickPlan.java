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

public class BuyingLocalNumbersPickPlan extends TestBase {

    private HomePage homePage;
    private LocalIndexPage localIndexPage;
    private LocalStateDetail localStateDetail;
    private BuyingLocalNumber buyingLocalNumber;
    private LocalSearchResult localSearchResult;
    private ContactUsPage contactUsPage;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;

    private String boughtNumber = "";
    private String searchRequest = "812288";
    private String planName = "Pick A Plan";


    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        localStateDetail = new LocalStateDetail(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
        contactUsPage = new ContactUsPage(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test(description = "Order Local Number with Custom Price Plan and without Promo Code")
    @Story("status: 32, carrier_id: 45, call_for_price: false, port_date: 2020-01-24, port_status: 16, popular: true, featured: true")
    public void test1OrderLocalNumberPickPlan() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(1);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Preferred");
        buyingLocalNumber.enterRingToNumber("8722413731");
        boughtNumber = buyingLocalNumber.goToCheckout();
/*        checkout.fillCheckout(Users.VLADYSLAV_68, CreditCards.VISA_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseParkNumber(priceNumber, pricePlan);*/
    }

    @Test(description = "Checking status Sold by phone number")
    @Story("Checking status Sold phone number ")
    public void test2CheckingStatusSold() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.enterAreaCode(boughtNumber.substring(0, 3));
        localIndexPage.searchLocalNumbers(boughtNumber.substring(3));
/*        localSearchResult.checkingStatusSold();*/
    }

    @Test(description = "Order Local Number with Custom Price Plan and with Fixed Promo Code")
    @Story("status: 32, carrier_id: 44, call_for_price: false, acquisition_date: 2020-02-09, call_forward: true, city: Miami, price_override: 11.00")
    public void test3OrderLocalNumberPickPlanWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(2);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Premium");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
/*        checkout.fillCheckout(Users.VLADYSLAV_67, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithFixedPromoCode(priceNumber, pricePlan);*/
    }

    @Test(description = "Order Local Number with Custom Price Plan and with High Fixed Promo Code")
    @Story("status: 32, carrier_id: 43, call_for_price: false, rev_share: 12, call_forward: false, port_status: 32, price_override: 1255.99")
    public void test4OrderLocalNumberPickPlanWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(3);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Starter");
        buyingLocalNumber.enterRingToNumber("9968843478");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
/*        checkout.fillCheckout(Users.VLADYSLAV_66, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithHighFixedPromoCode(priceNumber, pricePlan);*/
    }

    @Test(description = "Order Local Number with Custom Price Plan and with Percent Promo Code")
    @Story("status: 32, carrier_id: null, call_for_price: false, rev_share: 99, call_forward: true, port_date: 2020-04-02, price_override: 0")
    public void test5OrderLocalNumberPickPlanWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(4);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Preferred");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
/*        checkout.fillCheckout(Users.VLADYSLAV_67, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithPercentPromoCode(priceNumber, pricePlan);*/
    }

    @Test(description = "Order Local Number with Custom Price Plan and after remove Promo Code")
    @Story("status: 32, carrier_id: null, call_for_price: false, city: New York, port_date: 2020-04-02, acquisition_date: 2020-02-09, price_override: 299")
    public void test6orderLocalNumberPickPlanAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(5);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Premium");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.PERCENT_PROMOCODE.getName());
/*        checkout.fillCheckout(Users.VLADYSLAV_66, CreditCards.JCB, true);
        orderConfirmationPage.checkingYourPurchaseParkNumberAfterRemovePromoCode(priceNumber, pricePlan);*/
    }

    @Test(description = "Order Local Number with Custom Price Plan and Payment Error Processing Stripe")
    @Story("status: 32, call_for_price: false, disable_coupon: true, weight: 10, notes: Test notes")
    public void test7OrderLocalNumberPickPlanPaymentError() throws InterruptedException {
        homePage.open();
        homePage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(6);
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.choosePickYourMonthlyPlan("Starter");
        buyingLocalNumber.enterRingToNumber("1368843478");
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_68, CreditCards.ERROR_PROCESSING_STRIPE, true);
        checkout.checkingPaymentError();
    }

    @Test(description = "Checking Disable Coupon Checkbox")
    @Story("Checking disabled coupon field on checkout")
    public void test7CheckingDisableCouponCheckbox() throws InterruptedException {
        localStateDetail.open();
        localStateDetail.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(6);
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice(planName);
        buyingLocalNumber.choosePickYourMonthlyPlan("Premium");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
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
}
