import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

public class LicensingVanityPremiumNumbers extends TestBase {

    private HomePage homePage;
    private VanityIndexPage vanityIndexPage;
    private VanitySearchResult vanitySearchResult;
    private TollFreeIndexPage tollFreeIndexPage;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private LocalSearchResult localSearchResult;

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
    public void orderPremiumVanityNumber() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers("ERROR");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Kansas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(1);
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("2 Years");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose750MonthlyMinutes();
        buyingPremiumVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingPremiumVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void orderPremiumVanityNumberCheckingRegionStatus() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers("ERROR");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.checkingRegionStatus();
    }

    @Test
    public void orderPremiumVanityNumberWithFixedPromoCode() throws InterruptedException {
        vanityIndexPage.open();
        vanityIndexPage.searchTollFreeNumbers("ring");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Nevada");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseFirstAreaCodeFromList();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("Month");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose5000MonthlyMinutes();
        buyingPremiumVanityNumber.enterRingToNumber("8001234560");
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_24, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void orderPremiumVanityNumberWithHighFixedPromoCode() throws InterruptedException {
        tollFreeIndexPage.open();
        tollFreeIndexPage.searchTollFreeNumber("461ring");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Alabama");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseFirstAreaCodeFromList();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("3 Years");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose100MonthlyMinutes();
        buyingPremiumVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_23, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithHighFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void orderPremiumVanityNumberWithPercentPromoCode() throws InterruptedException {
        tollFreeIndexPage.open();
        tollFreeIndexPage.searchTollFreeNumber("RING");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.chooseByOneAreaCodesFromSeveralStates(new String[] {"Guam", "Alberta"});
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("1 Year");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose750MonthlyMinutes();
        buyingPremiumVanityNumber.enterRingToNumber("8001234560");
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithPercentPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void orderPremiumVanityNumberFromSeveralStates() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers("461RING");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.chooseByOneAreaCodesFromSeveralStates(new String[] {"Kansas", "Vermont"});
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("2 Years");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose750MonthlyMinutes();
        buyingPremiumVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingPremiumVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_24, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchase(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void orderPremiumVanityNumberAfterRemovePromoCode() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers("461RING");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.chooseByOneAreaCodesFromSeveralStates(new String[] {"Kansas", "Alabama", "Vermont"});
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("1 Year");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose5000MonthlyMinutes();
        buyingPremiumVanityNumber.enterRingToNumber("8001234560");
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_23, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseAfterRemovePromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void orderPremiumVanityNumberFromSeveralStatesWithDifferentQuantity() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers("461RING");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.
                chooseSeveralAreaCodesFromSeveralStates(new String[] {"Kansas", "Alabama", "Vermont"}, new int[] {3, 2, 1});
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("3 Years");
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose100MonthlyMinutes();
        buyingPremiumVanityNumber.enterRingToNumber("8001234560");
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_23, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseAfterRemovePromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, priceFromAmountAreaCodesWithDiscount);
    }

    @Test
    public void orderRegularVanityNumberPaymentError() throws InterruptedException {
        homePage.open();
        homePage.searchLocalNumbers("61RING");
        localSearchResult.chooseFirstNumberFromRelatedVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Nevada");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseFirstAreaCodeFromList();
        buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        buyingPremiumVanityNumber.chooseTermLength("Month");
        buyingPremiumVanityNumber.choose5000MonthlyMinutes();
        buyingPremiumVanityNumber.enterRingToNumber("8001234560");
        buyingPremiumVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.ERROR_EXPIRED_CARD_STRIPE, true);
        checkout.checkingPaymentError();
    }

}
