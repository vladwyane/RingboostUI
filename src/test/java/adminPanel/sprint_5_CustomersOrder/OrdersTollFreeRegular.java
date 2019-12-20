package adminPanel.sprint_5_CustomersOrder;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
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

    private String searchRequest = "apple";


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

    @Ignore
    @Test
    public void test1CheckingCorrectColumnOrdersTollFreeListing() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersTollFree();
        orderListingPage.checkingCorrectColumnOrdersTollFreeListing();
    }

    @Ignore
    @Test
    public void test1CheckingOrdersTollFreeListingIsNotEmpty() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersTollFree();
        orderListingPage.checkingTableNotEmpty();
    }

    @Test
    public void test1PriceOverrideRegularFlowWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(3).substring(0, 10);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10.01");
        double priceOverride = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String pricePlanName = "1 year";
        String description = buyingRegularVanityNumber.getPricePlanDescription(pricePlanName);
        String additionalText = buyingRegularVanityNumber.getPricePlanAdText(pricePlanName);
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(pricePlanName);
        String ringToNumber = "0668843471";
        buyingRegularVanityNumber.enterRingToNumber(ringToNumber);
        buyingRegularVanityNumber.goToCheckout();
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + 19.95 - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String cusSubPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + priceOverride - (priceMonthlyMinutes + priceOverride) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        String promoCode = "";
        String minutesPackage = amountMinutes + " minutes - $" + String.valueOf((int)priceMonthlyMinutes);
        String pricePlan = pricePlanName + " - " + additionalText + " " + description;
        checkout.fillCheckout(Users.VLADYSLAV_50, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderVanityNumbers(displayedName, cusSubPrice, subPrice, "$0.00", "$0.00", pricePlan,
                minutesPackage, "$0.05", ringToNumber, payToday, promoCode, "Completed", Users.VLADYSLAV_50, "", "");
    }

    @Test
    public void test2PriceOverrideRegularFlowWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(4).substring(0, 10);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage.generateLinkWithPromoCodeRegularFlow("99.9", "-EST");
        double priceOverride = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String pricePlanName = "2 years";
        String description = buyingRegularVanityNumber.getPricePlanDescription(pricePlanName);
        String additionalText = buyingRegularVanityNumber.getPricePlanAdText(pricePlanName);
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(pricePlanName);
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        String discountPromoCode = Integer.toString((int)PromoCodes.FIXED_PROMOCODE.getValue());
        buyingRegularVanityNumber.goToCheckout();
        String promoCodeName = PromoCodes.FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + 19.95 - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String cusSubPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + priceOverride - (priceMonthlyMinutes + priceOverride) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " $";
        String minutesPackage = amountMinutes + " minutes - $" + String.valueOf((int)priceMonthlyMinutes);
        String pricePlan = pricePlanName + " - " + additionalText + " " + description;
        checkout.fillCheckout(Users.VLADYSLAV_48, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderVanityNumbers(displayedName, cusSubPrice, subPrice, "$0.00", "$0.00",
                pricePlan, minutesPackage, "$0.07", "", payToday, promoCode, "Completed", Users.VLADYSLAV_48, "", "");
    }

    @Test
    public void test3OrderRegularVanityNumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        vanitySearchResult.open();
        vanitySearchResult.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        String displayedName = buyingRegularVanityNumber.getPhoneNumber().getText();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String pricePlanName = "Month-To-Month";
        String description = buyingRegularVanityNumber.getPricePlanDescription(pricePlanName);
        String additionalText = buyingRegularVanityNumber.getPricePlanAdText(pricePlanName);
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(pricePlanName);
        String ringToNumber = "0668843478";
        buyingRegularVanityNumber.enterRingToNumber(ringToNumber);
        String discountPromoCode = Integer.toString((int)PromoCodes.PERCENT_PROMOCODE.getValue());
        buyingRegularVanityNumber.goToCheckout();
        String promoCodeName = PromoCodes.PERCENT_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + 19.95 - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " %";
        String minutesPackage = amountMinutes + " minutes - $" + String.valueOf((int)priceMonthlyMinutes);
        String pricePlan = pricePlanName + " - " + additionalText + " " + description;
        checkout.fillCheckout(Users.VLADYSLAV_49, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderVanityNumbers(displayedName, "", subPrice, "$0.00", "", pricePlan,
                minutesPackage,"$0.07", ringToNumber, payToday, promoCode, "Completed", Users.VLADYSLAV_49, "", "");
    }

    @Test
    public void test4OrderRegularVanityNumberPaymentError() throws InterruptedException, IOException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        String displayedName = buyingRegularVanityNumber.getPhoneNumber().getText();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String pricePlanName = "Month-To-Month";
        String description = buyingRegularVanityNumber.getPricePlanDescription(pricePlanName);
        String additionalText = buyingRegularVanityNumber.getPricePlanAdText(pricePlanName);
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength(pricePlanName);
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        String discountPromoCode = Integer.toString((int)PromoCodes.HIGH_FIXED_PROMOCODE.getValue());
        buyingRegularVanityNumber.goToCheckout();
        String promoCodeName = PromoCodes.HIGH_FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + 19.95 - (priceMonthlyMinutes + 19.95) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " $";
        String minutesPackage = amountMinutes + " minutes - $" + String.valueOf((int)priceMonthlyMinutes);
        String pricePlan = pricePlanName + " - " + additionalText + " " + description;
        checkout.fillCheckout(Users.VLADYSLAV_50, CreditCards.ERROR_LOST_CARD_STRIPE, false);
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderVanityNumbers(displayedName, "", subPrice, "$0.00", "", pricePlan,
                minutesPackage, "$0.06", "", payToday, promoCode, "Failed", Users.VLADYSLAV_50, "", "");
    }

}
