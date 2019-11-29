package adminPanel.sprint_5_CustomersOrder;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.*;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 11/26/2019.
 */
public class OrdersTollFreePremium extends TestBase {


    private Login login;
    private Admin admin;
    private OrderListingPage orderListingPage;
    private OrderDetailPage orderDetailPage;
    private LinksListingPage linksListingPage;
    private InventoryTollfree inventoryTollfree;
    private HomePage homePage;
    private VanityIndexPage vanityIndexPage;
    private VanitySearchResult vanitySearchResult;
    private TollFreeIndexPage tollFreeIndexPage;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;

    private String searchRequest = "orders";

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        orderListingPage = new OrderListingPage(app.getDriver());
        orderDetailPage = new OrderDetailPage(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        inventoryTollfree = new InventoryTollfree(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        homePage = new HomePage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        homePage = new HomePage(app.getDriver());
        vanityIndexPage = new VanityIndexPage(app.getDriver());
        tollFreeIndexPage = new TollFreeIndexPage(app.getDriver());
        buyingPremiumVanityNumber = new BuyingPremiumVanityNumber(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1orderPremiumNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithoutPromoCodePremiumFlow("10.01", "Alabama", 2,
                        "1 year", "750");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickEditButton(linksListingPage.returnIndexLastGeneratedLink());
        double priceOverride = 4001.99;
        String termLength = "2 years";
        String displayedName = linksListingPage
                .generateLinkWithPromoCodePremiumFlow(Double.toString(priceOverride), "Texas", 2,
                        termLength, "100", "1234");
        double oldPriceChosenAreaCodes = linksListingPage.getSumOldPriceChosenAreaCodes();
        int amountOfMinute = linksListingPage.getAmountOfMinute();
        int pricePlanDuration = linksListingPage.getPricePlanDuration(termLength);
        double priceMonthlyMinute = linksListingPage.getPriceMonthlyMinute();
        double saleSelectedPlan = linksListingPage.getSaleSelectedPlan();
        double subscriptionPrice = priceMonthlyMinute + priceOverride - saleSelectedPlan;
        String discountPromoCode = Double.toString(Math.round(subscriptionPrice * PromoCodes.PERCENT_PROMOCODE.getValue() / 100 * 100.0) / 100.0);
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_30, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderPremiumFlow(displayedName, priceOverride, oldPriceChosenAreaCodes, priceMonthlyMinute, amountOfMinute,
                 pricePlanDuration, "-", subscriptionPrice, discountPromoCode, PromoCodes.PERCENT_PROMOCODE.getName());
    }

    @Test
    public void test1orderPremiumNumberWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(2);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        double priceOverride = 10.01;
        String termLength = "1 year";
        String displayedName = linksListingPage
                .generateLinkWithoutPromoCodePremiumFlow(Double.toString(priceOverride), "Kansas", 1,
                        termLength, "750");
        double oldPriceChosenAreaCodes = linksListingPage.getSumOldPriceChosenAreaCodes();
        int amountOfMinute = linksListingPage.getAmountOfMinute();
        int pricePlanDuration = linksListingPage.getPricePlanDuration(termLength);
        double priceMonthlyMinute = linksListingPage.getPriceMonthlyMinute();
        double saleSelectedPlan = linksListingPage.getSaleSelectedPlan();
        double subscriptionPrice = priceMonthlyMinute + priceOverride - saleSelectedPlan;
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        checkout.fillCheckout(Users.VLADYSLAV_29, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderPremiumFlow(displayedName, priceOverride, oldPriceChosenAreaCodes, priceMonthlyMinute, amountOfMinute,
                pricePlanDuration, "-", subscriptionPrice, "-", "-");
    }

    @Test
    public void test3OrderPremiumVanityNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        vanityIndexPage.open();
        vanityIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        String displayedName = buyingPremiumVanityNumber.getPhoneNumber().getText();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.
                chooseSeveralAreaCodesFromSeveralStates(new String[] {"Kansas", "Vermont"}, new int[] {3, 1});
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength("month");
        int planDuration = buyingPremiumVanityNumber.getPricePlanDuration(discountPriceSelectedPlan);
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose5000MonthlyMinutes();
        int amountOfMinute = buyingPremiumVanityNumber.getAmountMinutes(priceMonthlyMinutes);
        buyingPremiumVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingPremiumVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        String discountPromoCode = Double.toString(PromoCodes.FIXED_PROMOCODE.getValue());
        double discountAmountAreaCodes = checkout.getDiscountAmountAreacodes();
        double subscriptionPrice = priceMonthlyMinutes + priceFromAmountAreaCodesWithDiscount -
                (priceMonthlyMinutes + priceFromAmountAreaCodesWithDiscount) * discountPriceSelectedPlan * 0.01 ;
        checkout.fillCheckout(Users.VLADYSLAV_31, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderPremiumFlow(displayedName, 0.0, priceFromAmountAreaCodesWithDiscount + discountAmountAreaCodes,
                priceMonthlyMinutes, amountOfMinute, planDuration, "", subscriptionPrice, discountPromoCode, PromoCodes.FIXED_PROMOCODE.getName());
    }
}
