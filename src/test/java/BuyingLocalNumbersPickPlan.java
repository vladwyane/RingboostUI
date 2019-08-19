import data.CreditCards;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

import java.io.IOException;

public class BuyingLocalNumbersPickPlan extends TestBase {

    private HomePage homePage;
    private LocalIndexPage localIndexPage;
    private BuyingLocalNumber buyingLocalNumber;
    private LocalSearchResult localSearchResult;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;

    private String boughtNumber;


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
    public void orderLocalNumberPickPlan() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("0987");
        localSearchResult.chooseNumberFromLocalNumbersList(10);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Preferred");
        buyingLocalNumber.enterRingToNumber("8722413731");
        boughtNumber = buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.VISA_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseParkNumber(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanSold() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(boughtNumber);
        localSearchResult.checkingStatusSold();
    }

    @Test
    public void orderLocalNumberPickPlanWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchLocalNumbers("0987");
        localSearchResult.chooseNumberFromLocalNumbersList(5);
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Premium");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_24, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanWithHighFixedPromoCode() throws InterruptedException, IOException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("12345");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Starter");
        buyingLocalNumber.enterRingToNumber("9968843478");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_23, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithHighFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanWithPercentPromoCode() throws InterruptedException, IOException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("12345");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Preferred");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_24, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithPercentPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanAfterRemovePromoCodee() throws InterruptedException, IOException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("12345");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Premium");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_23, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberAfterRemovePromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanPaymentError() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("12345");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick A Plan");
        buyingLocalNumber.choosePickYourMonthlyPlan("Starter");
        buyingLocalNumber.enterRingToNumber("1368843478");
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.ERROR_PROCESSING_STRIPE, false);
        checkout.checkingPaymentError();
    }
}
