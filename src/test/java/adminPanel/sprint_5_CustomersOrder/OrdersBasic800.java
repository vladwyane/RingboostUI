package adminPanel.sprint_5_CustomersOrder;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.Admin;
import pages.admin.Login;
import pages.admin.OrderDetailPage;
import pages.admin.OrderListingPage;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 11/26/2019.
 */
public class OrdersBasic800 extends TestBase {

    private Login login;
    private Admin admin;
    private HomePage homePage;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BasicIndexPage basicIndexPage;
    private BuyingBasic800Number buyingBasic800Number;
    private OrderListingPage orderListingPage;
    private OrderDetailPage orderDetailPage;

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        homePage = new HomePage(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        basicIndexPage = new BasicIndexPage(app.getDriver());
        buyingBasic800Number = new BuyingBasic800Number(app.getDriver());
        orderListingPage = new OrderListingPage(app.getDriver());
        orderDetailPage = new OrderDetailPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void orderBasic800Number() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Business Pro";
        String additionalCost = "$" + buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        String pricePlanPrice = "$" + String.valueOf(Math.round(buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName)* 100.0) / 100.0);
        String ringToNumber = "8722413731";
        buyingBasic800Number.enterRingToNumber(ringToNumber);
        String priceActivationFee = "$" + String.valueOf(Math.round(buyingBasic800Number.getPriceActivationFee()* 100.0) / 100.0) + "0";
        buyingBasic800Number.goToCheckout();
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_40, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName + " - " + amountMinutes, pricePlanPrice, payToday, additionalCost,
                priceActivationFee, ringToNumber, "", "", "", "Completed");
    }

    @Test
    public void orderBasic800NumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic-numbers");
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Premium";
        String additionalCost = "$" + buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        String pricePlanPrice = "$" + String.valueOf(Math.round(buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName)* 100.0) / 100.0);
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        String priceActivationFee = "$" + String.valueOf(Math.round(buyingBasic800Number.getPriceActivationFee()* 100.0) / 100.0) + "0";
        buyingBasic800Number.goToCheckout();
        String discountPromoCode = Integer.toString((int)PromoCodes.FIXED_PROMOCODE.getValue());
        String promoCodeName = PromoCodes.FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_39, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName + " - " + amountMinutes, pricePlanPrice, payToday, additionalCost,
                priceActivationFee, "", discountPromoCode, promoCodeName, "$", "Completed");
    }

    @Test
    public void orderBasic800NumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Starter";
        String additionalCost = "$" + buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        String pricePlanPrice = "$" + String.valueOf(Math.round(buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName)* 100.0) / 100.0);
        String ringToNumber = "0668843478";
        buyingBasic800Number.enterRingToNumber(ringToNumber);
        String priceActivationFee = "$" + String.valueOf(Math.round(buyingBasic800Number.getPriceActivationFee()* 100.0) / 100.0) + "0";
        String discountPromoCode = Integer.toString((int)PromoCodes.HIGH_FIXED_PROMOCODE.getValue());
        String promoCodeName = PromoCodes.HIGH_FIXED_PROMOCODE.getName();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(promoCodeName);
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_38, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName + " - " + amountMinutes, pricePlanPrice, payToday, additionalCost,
                priceActivationFee, ringToNumber, discountPromoCode, promoCodeName, "$", "Completed");
    }

    @Test
    public void orderBasic800NumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic-numbers");
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Business Pro";
        String additionalCost = "$" + buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        String pricePlanPrice = "$" + String.valueOf(Math.round(buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName)* 100.0) / 100.0);
        String ringToNumber = "8722413731";
        buyingBasic800Number.enterRingToNumberWithMultipleCheckbox(ringToNumber);
        String priceActivationFee = "$" + String.valueOf(Math.round(buyingBasic800Number.getPriceActivationFee()* 100.0) / 100.0) + "0";
        String discountPromoCode = Integer.toString((int)PromoCodes.PERCENT_PROMOCODE.getValue());
        String promoCodeName = PromoCodes.PERCENT_PROMOCODE.getName();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(promoCodeName);
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_39, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName + " - " + amountMinutes, pricePlanPrice, payToday, additionalCost,
                priceActivationFee, "", discountPromoCode, promoCodeName, "%", "Completed");
    }

    @Test
    public void orderBasic800NumberAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Starter";
        String additionalCost = "$" + buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        String pricePlanPrice = "$" + String.valueOf(Math.round(buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName)* 100.0) / 100.0);
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        String priceActivationFee = "$" + String.valueOf(Math.round(buyingBasic800Number.getPriceActivationFee()* 100.0) / 100.0) + "0";
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.wait2SecUntilPromoRemove();
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_38, CreditCards.JCB, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName + " - " + amountMinutes, pricePlanPrice, payToday, additionalCost,
                priceActivationFee, "", "", "", "", "Completed");
    }

    @Test
    public void orderBasic800NumberPaymentError() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Premium";
        String additionalCost = "$" + buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        String pricePlanPrice = "$" + String.valueOf(Math.round(buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName)* 100.0) / 100.0);
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        String priceActivationFee = "$" + String.valueOf(Math.round(buyingBasic800Number.getPriceActivationFee()* 100.0) / 100.0) + "0";
        buyingBasic800Number.goToCheckout();
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_39, CreditCards.ERROR_STOLEN_CARD_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName + " - " + amountMinutes, pricePlanPrice, payToday, additionalCost,
                priceActivationFee, "", "", "", "", "Failed");
    }
}
