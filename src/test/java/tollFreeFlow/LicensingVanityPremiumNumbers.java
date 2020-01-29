package tollFreeFlow;

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

public class LicensingVanityPremiumNumbers extends TestBase {

    private HomePage homePage;
    private VanityIndexPage vanityIndexPage;
    private VanitySearchResult vanitySearchResult;
    private TollFreeIndexPage tollFreeIndexPage;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private LocalSearchResult localSearchResult;

    private String searchRequest = "333";

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        vanityIndexPage = new VanityIndexPage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        tollFreeIndexPage = new TollFreeIndexPage(app.getDriver());
        buyingPremiumVanityNumber = new BuyingPremiumVanityNumber(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1OrderPremiumVanityNumber() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromPremiumVanityList(1);
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Kansas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(1);
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("2 years");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose750MonthlyMinutes();
        buyingPremiumVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingPremiumVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_52, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void test2OrderPremiumVanityNumberCheckingRegionStatus() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromPremiumVanityList(1);
        buyingPremiumVanityNumber.checkingRegionStatus();
    }

    @Test
    public void test2OrderPremiumVanityNumberCheckingNationwideStatus() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromPremiumVanityList(3);
        buyingPremiumVanityNumber.checkingNationWideStatus();
    }

    @Test
    public void test3OrderPremiumVanityNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        vanityIndexPage.open();
        vanityIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Texas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(2);
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("month");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose5000MonthlyMinutes();
        buyingPremiumVanityNumber.enterRingToNumber("8001234560");
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_51, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void test3OrderPremiumVanityNumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        tollFreeIndexPage.open();
        tollFreeIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.chooseByOneAreaCodesFromSeveralStates(new String[] {"Kansas", "Alabama"});
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("3 years");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose100MonthlyMinutes();
        buyingPremiumVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_53, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithHighFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void test3OrderPremiumVanityNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        tollFreeIndexPage.open();
        tollFreeIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.chooseByOneAreaCodesFromSeveralStates(new String[] {"Kansas", "Alabama", "Vermont"});
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("1 year");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose750MonthlyMinutes();
        buyingPremiumVanityNumber.enterRingToNumber("8001234560");
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_52, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithPercentPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void test3OrderPremiumVanityNumberAfterRemovePromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromPremiumVanityList(2);
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.
                chooseSeveralAreaCodesFromSeveralStates(new String[] {"Kansas", "Vermont"}, new int[] {3, 1});
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("2 years");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose750MonthlyMinutes();
        buyingPremiumVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_53, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseAfterRemovePromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void test4checkingStatusLicensed() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.checkingStatusLicensedByIndex(2);
    }

    @Test
    public void test5OrderRegularVanityNumberPaymentError() throws InterruptedException {
        homePage.open();
        homePage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseFirstNumberFromRelatedVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Alabama");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseFirstAreaCodeFromList();
        buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        buyingPremiumVanityNumber.chooseTermLength("month");
        buyingPremiumVanityNumber.choose5000MonthlyMinutes();
        buyingPremiumVanityNumber.enterRingToNumber("8001234560");
        buyingPremiumVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_52, CreditCards.ERROR_CVC_STRIPE, true);
        checkout.checkingPaymentError();
    }

}
