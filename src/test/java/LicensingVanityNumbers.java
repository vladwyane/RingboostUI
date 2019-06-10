import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

public class LicensingVanityNumbers extends TestBase {

    private HomePage homePage;
    private VanityIndexPage vanityIndexPage;
    private VanitySearchResult vanitySearchResult;
    private TollFreeIndexPage tollFreeIndexPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private VanityCategoryDetail vanityCategoryDetail;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        vanityIndexPage = new VanityIndexPage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        tollFreeIndexPage = new TollFreeIndexPage(app.getDriver());
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
        vanityCategoryDetail = new VanityCategoryDetail(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
    }


    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void orderRegularVanityNumber() throws InterruptedException {
        homePage.open();
        tollFreeIndexPage.openTollFreeIndexPageFromMainNav();
        tollFreeIndexPage.searchTollFreeNumber("er");
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 Year");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }


    @Test
    public void orderRegularVanityNumberWithFixedPromoCode() throws InterruptedException {
        homePage.open();
        homePage.clickSubNavItemTollFree("vanity");
        vanityIndexPage.searchTollFreeNumbers("bu");
        vanitySearchResult.chooseLastNumberFromRegularVanityListAfterLoadMore();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("Month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_2, CreditCards.MASTERCART_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberWithPercentPromoCode() throws InterruptedException {
        vanitySearchResult.open();
        vanitySearchResult.searchTollFreeNumbers("bu");
        vanitySearchResult.chooseFirstNumberFromRegularVanityListAfterLoadMore();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 Year");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_1, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberAfterRemovePromoCode() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers("er");
        vanitySearchResult.choose32thNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 Year");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_1, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseAfterRemovePromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberPaymentError() throws InterruptedException {
        vanitySearchResult.open();
        vanitySearchResult.chooseFirstNumberFromRegularVanityListAfterLoadMore();
        buyingRegularVanityNumber.choose500MonthlyMinutes();
        buyingRegularVanityNumber.chooseTermLength("Month");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_1, CreditCards.ERROR_STRIPE, true);
        checkout.checkingPaymentError();
    }

}
