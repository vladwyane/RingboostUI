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

    @Test
    public void orderRegularVanityNumber() throws InterruptedException, IOException, JSONException {
        homePage.open();
        tollFreeIndexPage.openTollFreeIndexPageFromMainNav();
        tollFreeIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 years");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_44, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.clickSubNavItemTollFree("vanity-numbers");
        vanityIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_45, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        tollFreeIndexPage.open();
        tollFreeIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_46, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithHighFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        vanitySearchResult.open();
        vanitySearchResult.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 year");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_45, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithPercentPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberAfterRemovePromoCode() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 years");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_46, CreditCards.JCB, true);
        orderConfirmationPage.checkingYourPurchaseAfterRemovePromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberPaymentError() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        buyingRegularVanityNumber.choose5000MonthlyMinutes();
        buyingRegularVanityNumber.chooseTermLength("month");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_44, CreditCards.ERROR_CVC_STRIPE, true);
        checkout.checkingPaymentError();
    }

}
