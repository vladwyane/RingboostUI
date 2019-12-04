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
    public void test2PriceOverrideRegularFlowWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(3).substring(0, 10);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage.generateLinkWithoutPromoCodeRegularFlow("99.9");
        double priceOverride = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String pricePlanName = "1 year";
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(pricePlanName);
        String pricePlan = pricePlanName + " - " + discountPriceSelectedPlan + "%";
        String planDuration = String.valueOf(buyingRegularVanityNumber.getPricePlanDuration(discountPriceSelectedPlan));
        String ringToNumber = "0668843471";
        buyingRegularVanityNumber.enterRingToNumber(ringToNumber);
        buyingRegularVanityNumber.goToCheckout();
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + priceOverride - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String cusSubPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + priceOverride - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_32, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderRegularFlow(displayedName, cusSubPrice, subPrice, pricePlan, discountPriceSelectedPlan + "%",
                "$" + String.valueOf((int)priceMonthlyMinutes), amountMinutes, "$0.05", planDuration, ringToNumber, payToday,
                "", "", "", "Completed");
    }

    @Test
    public void test2PriceOverrideRegularFlowWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(4).substring(0, 10);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage.generateLinkWithPromoCodeRegularFlow("10.01", "-EST");
        double priceOverride = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String pricePlanName = "2 years";
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(pricePlanName);
        String pricePlan = pricePlanName + " - " + discountPriceSelectedPlan + "%";
        String planDuration = String.valueOf(buyingRegularVanityNumber.getPricePlanDuration(discountPriceSelectedPlan));
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        String discountPromoCode = Integer.toString((int)PromoCodes.FIXED_PROMOCODE.getValue());
        buyingRegularVanityNumber.goToCheckout();
        String promoCodeName = PromoCodes.FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + 19.95 - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String cusSubPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + priceOverride - (priceMonthlyMinutes + priceOverride) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_33, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderRegularFlow(displayedName, cusSubPrice, subPrice, pricePlan, discountPriceSelectedPlan + "%",
                "$" + String.valueOf((int)priceMonthlyMinutes), amountMinutes, "$0.07", planDuration, "", payToday,
                discountPromoCode, promoCodeName, "$", "Completed");
    }

    @Test
    public void test2orderRegularVanityNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        vanitySearchResult.open();
        vanitySearchResult.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        String displayedName = buyingRegularVanityNumber.getPhoneNumber().getText();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String pricePlanName = "Month-To-Month";
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(pricePlanName);
        String pricePlan = pricePlanName + " - " + discountPriceSelectedPlan + "%";
        String planDuration = String.valueOf(buyingRegularVanityNumber.getPricePlanDuration(discountPriceSelectedPlan));
        buyingRegularVanityNumber.enterRingToNumber("0668843478");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        String discountPromoCode = Integer.toString((int)PromoCodes.PERCENT_PROMOCODE.getValue());
        buyingRegularVanityNumber.goToCheckout();
        String promoCodeName = PromoCodes.PERCENT_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + 19.95 - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_34, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderRegularFlow(displayedName, "", subPrice, pricePlan, discountPriceSelectedPlan + "%",
                "$" + String.valueOf((int)priceMonthlyMinutes), amountMinutes, "$0.07", planDuration, "", payToday,
                discountPromoCode, promoCodeName, "%", "Completed");
    }

    @Test
    public void orderRegularVanityNumberPaymentError() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        String displayedName = buyingRegularVanityNumber.getPhoneNumber().getText();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String pricePlanName = "Month-To-Month";
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(pricePlanName);
        String pricePlan = pricePlanName + " - " + discountPriceSelectedPlan + "%";
        String planDuration = String.valueOf(buyingRegularVanityNumber.getPricePlanDuration(discountPriceSelectedPlan));
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        String discountPromoCode = Integer.toString((int)PromoCodes.HIGH_FIXED_PROMOCODE.getValue());
        buyingRegularVanityNumber.goToCheckout();
        String promoCodeName = PromoCodes.HIGH_FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + 19.95 - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        checkout.fillCheckout(Users.VLADYSLAV_32, CreditCards.ERROR_LOST_CARD_STRIPE, false);
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.clickTab("Additional details");
        orderDetailPage.checkingCorrectDataOrderRegularFlow(displayedName, "", subPrice, pricePlan, discountPriceSelectedPlan + "%",
                "$" + String.valueOf((int)priceMonthlyMinutes), amountMinutes, "$0.07", planDuration, "", payToday,
                discountPromoCode, promoCodeName, "%", "Failed");
    }

}
