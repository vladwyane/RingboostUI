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

    private String searchRequest = "phone";

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
        String pricePlanDiscount = "Save 15%";
        String amountMinutes = String.valueOf(linksListingPage.getAmountOfMinute());
        double priceMonthlyMinute = linksListingPage.getPriceMonthlyMinute();
        double saleSelectedPlan = linksListingPage.getSaleSelectedPlan();
        String discountPromoCode = Integer.toString((int) PromoCodes.PERCENT_PROMOCODE.getValue());
        linksListingPage.clickGenerateLinkButtonPremiumFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        String promoCodeName = PromoCodes.PERCENT_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        double oldSale = (saleSelectedPlan / (priceMonthlyMinute + priceOverride)) * (priceMonthlyMinute + oldPriceChosenAreaCodes);
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinute + oldPriceChosenAreaCodes - oldSale) * 100.0) / 100.0);
        String cusSubPrice = "$" + String.valueOf(Math.round((priceMonthlyMinute + priceOverride - saleSelectedPlan) * 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " %";
        String minutesPackage = amountMinutes + " minutes - $" + String.valueOf((int) priceMonthlyMinute);
        String pricePlan = termLength + " - " + pricePlanDiscount;
        checkout.fillCheckout(Users.VLADYSLAV_45, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderVanityNumbers(displayedName, cusSubPrice, subPrice, "$0.00", "$0.00", pricePlan,
                minutesPackage, "$0.06", "", payToday, promoCode, "Completed", Users.VLADYSLAV_45, "", "");
    }

    @Test
    public void test2OrderPremiumNumberWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(2);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        double priceOverride = 10.01;
        String termLength = "1 year";
        String pricePlanDiscount = "Save Now 10%";
        String displayedName = linksListingPage
                .generateLinkWithoutPromoCodePremiumFlow(Double.toString(priceOverride), "Kansas", 1,
                        termLength, "750");
        double oldPriceChosenAreaCodes = linksListingPage.getSumOldPriceChosenAreaCodes();
        String amountMinutes = String.valueOf(linksListingPage.getAmountOfMinute());
        double priceMonthlyMinute = linksListingPage.getPriceMonthlyMinute();
        double saleSelectedPlan = linksListingPage.getSaleSelectedPlan();
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double oldSale = (saleSelectedPlan / (priceMonthlyMinute + priceOverride)) * (priceMonthlyMinute + oldPriceChosenAreaCodes);
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinute + oldPriceChosenAreaCodes - oldSale) * 100.0) / 100.0) + "0";
        String cusSubPrice = "$" + String.valueOf(Math.round((priceMonthlyMinute + priceOverride - saleSelectedPlan)* 100.0) / 100.0);
        String payToday = checkout.getPricePayToday();
        String promoCode = "";
        String minutesPackage = amountMinutes + " minutes - $" + String.valueOf((int) priceMonthlyMinute);
        String pricePlan = termLength + " - " + pricePlanDiscount;
        checkout.fillCheckout(Users.VLADYSLAV_44, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderVanityNumbers(displayedName, cusSubPrice, subPrice, "$0.00", "$0.00", pricePlan,
                minutesPackage, "$0.08", "", payToday, promoCode, "Completed", Users.VLADYSLAV_44, "", "");
    }

    @Test
    public void test3OrderPremiumVanityNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        vanityIndexPage.open();
        vanityIndexPage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        String displayedName = buyingPremiumVanityNumber.getPhoneNumber().getText();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Alabama");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(2);
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        String pricePlanName = "3 years";
        String description = buyingPremiumVanityNumber.getPricePlanDescription(pricePlanName);
        String additionalText = buyingPremiumVanityNumber.getPricePlanAdText(pricePlanName);
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength(pricePlanName);
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose5000MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingPremiumVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String ringToNumber = "8001234560";
        buyingPremiumVanityNumber.enterRingToNumber(ringToNumber);
        buyingPremiumVanityNumber.goToCheckout();
        String promoCodeName = PromoCodes.FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String discountPromoCode = Integer.toString((int)PromoCodes.FIXED_PROMOCODE.getValue());
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + priceFromAmountAreaCodesWithDiscount -
                (priceMonthlyMinutes + priceFromAmountAreaCodesWithDiscount) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0) + "0";
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " $";
        String minutesPackage = amountMinutes + " minutes - $" + String.valueOf((int) priceMonthlyMinutes);
        String pricePlan = pricePlanName + " - " + additionalText + " " + description;
        checkout.fillCheckout(Users.VLADYSLAV_46, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderVanityNumbers(displayedName, "", subPrice, "$0.00", "", pricePlan,
                minutesPackage, "$0.05", ringToNumber, payToday, promoCode, "Completed", Users.VLADYSLAV_46, "Lebron James", "1%");
    }

    @Test
    public void test4OrderRegularVanityNumberPaymentError() throws InterruptedException, IOException {
        homePage.open();
        homePage.searchTollFreeNumbers(searchRequest);
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        String displayedName = buyingPremiumVanityNumber.getPhoneNumber().getText();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        double priceFromAmountAreaCodesWithDiscount = buyingPremiumVanityNumber.
                chooseSeveralAreaCodesFromSeveralStates(new String[] {"Kansas", "Vermont"}, new int[] {3, 1});
        String pricePlanName = "Month-To-Month";
        String description = buyingPremiumVanityNumber.getPricePlanDescription(pricePlanName);
        String additionalText = buyingPremiumVanityNumber.getPricePlanAdText(pricePlanName);
        int discountPriceSelectedPlan = buyingPremiumVanityNumber.chooseTermLength(pricePlanName);
        double priceMonthlyMinutes = buyingPremiumVanityNumber.choose750MonthlyMinutes();
        String amountMinutes = String.valueOf(buyingPremiumVanityNumber.getAmountMinutes(priceMonthlyMinutes));
        String ringToNumber = "8001234560";
        buyingPremiumVanityNumber.enterRingToNumber(ringToNumber);
        buyingPremiumVanityNumber.goToCheckout();
        String promoCodeName = PromoCodes.HIGH_FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String discountPromoCode = Integer.toString((int)PromoCodes.HIGH_FIXED_PROMOCODE.getValue());
        String subPrice = "$" + String.valueOf(Math.round((priceMonthlyMinutes + priceFromAmountAreaCodesWithDiscount -
                (priceMonthlyMinutes + priceFromAmountAreaCodesWithDiscount) * discountPriceSelectedPlan * 0.01)* 100.0) / 100.0) + "0";
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " $";
        String minutesPackage = amountMinutes + " minutes - $" + String.valueOf((int) priceMonthlyMinutes);
        String pricePlan = pricePlanName + " - " + additionalText + " " + description;
        checkout.fillCheckout(Users.VLADYSLAV_46, CreditCards.ERROR_EXPIRED_CARD_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersTollFree();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderVanityNumbers(displayedName, "", subPrice, "$0.00", "", pricePlan,
                minutesPackage, "$0.07", ringToNumber, payToday, promoCode, "Failed", Users.VLADYSLAV_46, "Lebron James", "1%");
    }
}
