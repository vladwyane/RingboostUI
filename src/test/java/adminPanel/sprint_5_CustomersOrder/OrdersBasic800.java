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
        String additionalCost = buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        double pricePlanPrice = buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName);
        String ringToNumber = "8722413731";
        buyingBasic800Number.enterRingToNumber(ringToNumber);
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_34, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName, pricePlanPrice, amountMinutes,
                additionalCost, priceActivationFee, ringToNumber, "", "-", "Success");
    }

    @Test
    public void orderBasic800NumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic-numbers");
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Premium";
        String additionalCost = buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        double pricePlanPrice = buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName);
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        String discountPromoCode = Double.toString(PromoCodes.FIXED_PROMOCODE.getValue());
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_33, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName, pricePlanPrice, amountMinutes,
                additionalCost, priceActivationFee, "-", discountPromoCode, PromoCodes.FIXED_PROMOCODE.getName(), "Success");
    }

    @Test
    public void orderBasic800NumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Starter";
        String additionalCost = buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        double pricePlanPrice = buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName);
        String ringToNumber = "0668843478";
        buyingBasic800Number.enterRingToNumber(ringToNumber);
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        String discountPromoCode = Double.toString(PromoCodes.HIGH_FIXED_PROMOCODE.getValue());
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_32, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName, pricePlanPrice, amountMinutes,
                additionalCost, priceActivationFee, ringToNumber, discountPromoCode, PromoCodes.HIGH_FIXED_PROMOCODE.getName(), "Success");
    }

    @Test
    public void orderBasic800NumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic-numbers");
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Business Pro";
        String additionalCost = buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        double pricePlanPrice = buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName);
        String ringToNumber = "8722413731";
        buyingBasic800Number.enterRingToNumber(ringToNumber);
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        String discountPromoCode = Double.toString(Math.round((priceActivationFee + pricePlanPrice) * PromoCodes.PERCENT_PROMOCODE.getValue() / 100 * 100.0) / 100.0);
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_33, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName, pricePlanPrice, amountMinutes,
                additionalCost, priceActivationFee, "-", discountPromoCode, PromoCodes.PERCENT_PROMOCODE.getName(), "Success");
    }

    @Test
    public void orderBasic800NumberAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Starter";
        String additionalCost = buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        double pricePlanPrice = buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName);
        String ringToNumber = "0668843478";
        buyingBasic800Number.enterRingToNumber(ringToNumber);
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_34, CreditCards.JCB, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName, pricePlanPrice, amountMinutes,
                additionalCost, priceActivationFee, "-", "-", "-", "Success");
    }

    @Test
    public void orderBasic800NumberPaymentError() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Premium";
        String additionalCost = buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        double pricePlanPrice = buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName);
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_32, CreditCards.ERROR_STOLEN_CARD_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName, pricePlanPrice, amountMinutes,
                additionalCost, priceActivationFee, "-", "", "-", "Error");
    }
}
