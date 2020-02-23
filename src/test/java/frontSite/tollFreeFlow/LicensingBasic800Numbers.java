package frontSite.tollFreeFlow;

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
 * Created by bigdrop on 6/19/2019.
 */
public class LicensingBasic800Numbers extends TestBase {

    private HomePage homePage;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BasicIndexPage basicIndexPage;
    private BuyingBasic800Number buyingBasic800Number;
    private ContactUsPage contactUsPage;

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        basicIndexPage = new BasicIndexPage(app.getDriver());
        buyingBasic800Number = new BuyingBasic800Number(app.getDriver());
        contactUsPage = new ContactUsPage(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test(description = "Order Basic800 Number without Promo Code")
    @Story("status: 32, call_for_price: false, nationwide: false, premium: false, type: vanity, easy_dial, basic800")
    public void test1OrderBasic800Number() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseNumberFromBasic800List(1);
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Business Pro");
        buyingBasic800Number.enterRingToNumber("8722413731");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_55, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseBasic800Number(priceActivationFee, pricePlan);
    }

    @Test(description = "Order Basic800 Number with fixed Promo Code")
    @Story("status: 32, call_for_price: false, nationwide: false, premium: false, type: vanity, easy_dial, basic800")
    public void test2OrderBasic800NumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic-numbers");
        basicIndexPage.chooseNumberFromBasic800List(2);
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Premium");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_54, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithFixedPromoCode(priceActivationFee, pricePlan);
    }

    @Test(description = "Order Basic800 Number with high fixed Promo Code")
    @Story("status: 32, call_for_price: false, nationwide: false, premium: false, type: vanity, easy_dial, basic800")
    public void test3OrderBasic800NumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseNumberFromBasic800List(3);
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Starter");
        buyingBasic800Number.enterRingToNumber("0668843478");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_56, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithHighFixedPromoCode(priceActivationFee, pricePlan);
    }

    @Test(description = "Order Basic800 Number with percent Promo Code")
    @Story("status: 32, call_for_price: false, nationwide: false, premium: false, type: vanity, easy_dial, basic800")
    public void test4OrderBasic800NumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic-numbers");
        basicIndexPage.chooseNumberFromBasic800List(4);
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Business Pro");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_54, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithPercentPromoCode(priceActivationFee, pricePlan);
    }

    @Test(description = "Order Basic800 Number after remove Promo Code")
    @Story("status: 32, call_for_price: false, nationwide: false, premium: false, type: vanity, easy_dial, basic800")
    public void test5OrderBasic800NumberAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseNumberFromBasic800List(5);
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Starter");
        buyingBasic800Number.enterRingToNumber("0668843478");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_55, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberAfterRemovePromoCode(priceActivationFee, pricePlan);
    }

    @Test(description = "Order Basic800 Number Payment Error INSUFFICIENT_FUNDS_STRIPE")
    @Story("Order Basic800 Number Payment Error INSUFFICIENT_FUNDS_STRIPE")
    public void test6OrderBasic800NumberPaymentError() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.chooseNumberFromBasic800List(6);
        buyingBasic800Number.choosePickYourMonthlyPlan("Premium");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_56, CreditCards.ERROR_INSUFFICIENT_FUNDS_STRIPE, false);
        checkout.checkingPaymentError();
    }

    @Test(description = "Checking Call For Price Basic800 number")
    @Story("Checking Call For Price Basic800 number")
    public void test7CheckingCallForPriceBasic800() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.chooseNumberFromBasic800List(7);
        contactUsPage.checkingCorrectRedirectToContactUsPage();
    }

}
