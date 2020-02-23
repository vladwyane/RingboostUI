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

public class LicensingVanityRegularNumbers extends TestBase {

    private HomePage homePage;
    private VanityIndexPage vanityIndexPage;
    private VanitySearchResult vanitySearchResult;
    private TollFreeIndexPage tollFreeIndexPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private LocalSearchResult localSearchResult;

    private String searchRequest = "error";

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        vanityIndexPage = new VanityIndexPage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        tollFreeIndexPage = new TollFreeIndexPage(app.getDriver());
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test(description = "Order Regular Vanity Number without Promo Code")
    @Story("status: 32, weight: 10, nationwide: true, owner_id: 46, premium: false, type: vanity, easy_dial, basic800")
    public void test1OrderRegularVanityNumber() throws InterruptedException, IOException, JSONException {
        homePage.open();
        tollFreeIndexPage.openTollFreeIndexPageFromMainNav();
        tollFreeIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromRegularVanityList(1);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 years");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_56, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test(description = "Order Regular Vanity Number with fixed Promo Code")
    @Story("status: 32, weight: 9, nationwide: true, owner_id: 48, premium: false, type: vanity, easy_dial, basic800")
    public void test2OrderRegularVanityNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("vanity-numbers");
        vanityIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromRegularVanityList(2);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_54, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test(description = "Order Regular Vanity Number with high fixed Promo Code")
    @Story("status: 32, weight: 8, nationwide: true, owner_id: null, premium: false, type: vanity, easy_dial, basic800")
    public void test3OrderRegularVanityNumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        tollFreeIndexPage.open();
        tollFreeIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromRegularVanityList(3);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_55, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithHighFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test(description = "Order Regular Vanity Number with percent Promo Code")
    @Story("status: 32, weight: 7, nationwide: true, owner_id: 48, premium: false, type: vanity, easy_dial, basic800")
    public void test4OrderRegularVanityNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        vanitySearchResult.open();
        vanitySearchResult.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromRegularVanityList(4);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 year");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_54, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithPercentPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test(description = "Order Regular Vanity Number after remove Promo Code")
    @Story("status: 32, weight: 6, nationwide: true, owner_id: 48, premium: false, type: vanity, easy_dial, basic800")
    public void test5OrderRegularVanityNumberAfterRemovePromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromRegularVanityList(5);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 years");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_55, CreditCards.JCB, true);
        orderConfirmationPage.checkingYourPurchaseAfterRemovePromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test(description = "Order Regular Vanity Number after remove Promo Code")
    @Story("status: 32, weight: 5, nationwide: true, owner_id: 48, premium: false, type: vanity, easy_dial, basic800")
    public void test6OrderRegularVanityNumberPaymentError() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseIndexNumberFromRegularVanityList(6);
        buyingRegularVanityNumber.choose5000MonthlyMinutes();
        buyingRegularVanityNumber.chooseTermLength("month");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_56, CreditCards.ERROR_CVC_STRIPE, true);
        checkout.checkingPaymentError();
    }

}
