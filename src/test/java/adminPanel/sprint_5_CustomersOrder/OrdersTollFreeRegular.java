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
 * Created by bigdrop on 11/21/2019.
 */
public class OrdersTollFreeRegular extends TestBase {

    private Login login;
    private Admin admin;
    private OrderListingPage orderListingPage;
    private OrderDetailPage orderDetailPage;
    private LinksListingPage linksListingPage;
    private InventoryTollfree inventoryTollfree;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private HomePage homePage;
    private VanitySearchResult vanitySearchResult;

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
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
        homePage = new HomePage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1CheckingCorrectColumnOrdersTollFreeListing() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersTollFree();
        orderListingPage.checkingCorrectColumnOrdersTollFreeListing();
    }

    @Test
    public void test1CheckingOrdersTollFreeListingIsNotEmpty() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersTollFree();
        orderListingPage.checkingTableNotEmpty();
    }

    @Test
    public void test1CheckingCorrectStructureAdditionalDetailLocal() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersLocal();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectStructureDataLocalOrder();
    }

    @Test
    public void test2PriceOverrideRegularFlowWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(3).substring(0, 10);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage.generateLinkWithoutPromoCodeRegularFlow("100");
        double priceOverride = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int amountMinutes = buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes);
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 year");
        int planDuration = buyingRegularVanityNumber.getPricePlanDuration(discountPriceSelectedPlan);
        String ringToNumber = "0668843471";
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber(ringToNumber);
        double subscriptionPrice = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_29, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderRegularFlow(displayedName, priceOverride, priceMonthlyMinutes, amountMinutes,
                planDuration, ringToNumber, subscriptionPrice, "-", "-");
    }

    @Test
    public void test2PriceOverrideRegularFlowWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(4).substring(0, 10);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage.generateLinkWithPromoCodeRegularFlow("10", "-EST");
        double priceOverride = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int amountMinutes = buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes);
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 years");
        int planDuration = buyingRegularVanityNumber.getPricePlanDuration(discountPriceSelectedPlan);
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        double subscriptionPrice = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        String discountPromoCode = Double.toString(PromoCodes.FIXED_PROMOCODE.getValue());
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_30, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderRegularFlow(displayedName, priceOverride, priceMonthlyMinutes, amountMinutes, planDuration,
                "-", subscriptionPrice, discountPromoCode, PromoCodes.FIXED_PROMOCODE.getName());
    }

    @Test
    public void test2orderRegularVanityNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        vanitySearchResult.open();
        vanitySearchResult.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        String displayedName = buyingRegularVanityNumber.getPhoneNumber().getText();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int amountMinutes = buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes);
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("month");
        int planDuration = buyingRegularVanityNumber.getPricePlanDuration(discountPriceSelectedPlan);
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        double subscriptionPrice = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        String discountPromoCode = Double.toString(Math.round(subscriptionPrice * PromoCodes.PERCENT_PROMOCODE.getValue() / 100 * 100.0) / 100.0);
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_31, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderRegularFlow(displayedName, 0.0, priceMonthlyMinutes, amountMinutes,
                planDuration, "-", subscriptionPrice, discountPromoCode, PromoCodes.FIXED_PROMOCODE.getName());
    }

}
