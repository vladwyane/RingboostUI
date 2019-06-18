import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

public class LicensingVanityRegularNumbers extends TestBase {

    private HomePage homePage;
    private VanityIndexPage vanityIndexPage;
    private VanitySearchResult vanitySearchResult;
    private TollFreeIndexPage tollFreeIndexPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private VanityCategoryDetail vanityCategoryDetail;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;
    private LocalSearchResult localSearchResult;

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
        buyingPremiumVanityNumber = new BuyingPremiumVanityNumber(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void orderRegularVanityNumber() throws InterruptedException {
        homePage.open();
        tollFreeIndexPage.openTollFreeIndexPageFromMainNav();
        tollFreeIndexPage.searchTollFreeNumber("error");
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 Years");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_5, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberWithFixedPromoCode() throws InterruptedException {
        homePage.open();
        homePage.clickSubNavItemTollFree("vanity");
        vanityIndexPage.searchTollFreeNumbers("ring");
        vanitySearchResult.chooseLastNumberFromRegularVanityListAfterLoadMore();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose750MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("Month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_6, CreditCards.MASTERCART_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberWithHighFixedPromoCode() throws InterruptedException {
        tollFreeIndexPage.open();
        tollFreeIndexPage.searchTollFreeNumber("!@#$%^&*");
        vanitySearchResult.chooseLastNumberFromRegularVanityListAfterLoadMore();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("Month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_4, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithHighFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberWithPercentPromoCode() throws InterruptedException {
        vanitySearchResult.open();
        vanitySearchResult.searchTollFreeNumbers("bug");
        vanitySearchResult.chooseFirstNumberFromRegularVanityListAfterLoadMore();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose750MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 Years");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_5, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithPercentPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberAfterRemovePromoCode() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers("ring");
        vanitySearchResult.choose32thNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 Year");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_6, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseAfterRemovePromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void orderRegularVanityNumberPaymentError() throws InterruptedException {
        homePage.open();
        homePage.searchLocalNumbers("ERROR");
        localSearchResult.chooseFirstNumberFromRelatedVanityList();
        buyingRegularVanityNumber.choose5000MonthlyMinutes();
        buyingRegularVanityNumber.chooseTermLength("Month");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_4, CreditCards.ERROR_CVC_STRIPE, true);
        checkout.checkingPaymentError();
    }

}