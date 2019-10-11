package tollFreeFlow;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
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

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        basicIndexPage = new BasicIndexPage(app.getDriver());
        buyingBasic800Number = new BuyingBasic800Number(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void orderBasic800Number() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Business Pro");
        buyingBasic800Number.enterRingToNumber("8722413731");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_28, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseBasic800Number(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic-numbers");
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Premium");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_27, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithFixedPromoCode(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Starter");
        buyingBasic800Number.enterRingToNumber("0668843478");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_26, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithHighFixedPromoCode(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic-numbers");
        basicIndexPage.chooseFirstNumberFromBasic800List();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Business Pro");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_27, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithPercentPromoCode(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Starter");
        buyingBasic800Number.enterRingToNumber("0668843478");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_28, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberAfterRemovePromoCode(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberPaymentError() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        buyingBasic800Number.choosePickYourMonthlyPlan("Premium");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_26, CreditCards.ERROR_INSUFFICIENT_FUNDS_STRIPE, false);
        checkout.checkingPaymentError();
    }

}
