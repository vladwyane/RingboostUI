package adminPanel.sprint_5_CustomersOrder;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.*;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 11/29/2019.
 */
public class Orders extends TestBase {

    private Login login;
    private Admin admin;
    private OrderListingPage orderListingPage;
    private OrderDetailPage orderDetailPage;
    private LinksListingPage linksListingPage;
    private InventoryTollfree inventoryTollfree;
    private VanityIndexPage vanityIndexPage;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private HomePage homePage;
    private VanitySearchResult vanitySearchResult;
    private BasicIndexPage basicIndexPage;
    private BuyingBasic800Number buyingBasic800Number;
    private InventoryLocal inventoryLocal;
    private BuyingLocalNumber buyingLocalNumber;
    private LocalIndexPage localIndexPage;
    private LocalSearchResult localSearchResult;

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
        buyingPremiumVanityNumber = new BuyingPremiumVanityNumber(app.getDriver());
        vanityIndexPage = new VanityIndexPage(app.getDriver());
        basicIndexPage = new BasicIndexPage(app.getDriver());
        buyingBasic800Number = new BuyingBasic800Number(app.getDriver());
        inventoryLocal = new InventoryLocal(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1OrderPriceOverrideRegularFlowWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, searchRequest);
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1).substring(0, 10);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
        double priceOverride = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int amountMinutes = buyingRegularVanityNumber.getAmountMinutes(priceMonthlyMinutes);
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 years");
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
    public void test2OrderPremiumVanityNumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
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
        orderDetailPage.checkingCorrectDataOrderPremiumFlow(displayedName, 0.0, priceFromAmountAreaCodesWithDiscount + discountAmountAreaCodes,
                priceMonthlyMinutes, amountOfMinute, planDuration, "", subscriptionPrice, discountPromoCode, PromoCodes.FIXED_PROMOCODE.getName());
    }

    @Test
    public void test3OrderBasic800NumberWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800List();
        String displayedName = buyingBasic800Number.getPhoneNumber().getText();
        String pricePlanName = "Business Pro";
        String additionalCost = buyingBasic800Number.getAdditionalCost(pricePlanName);
        String amountMinutes = buyingBasic800Number.getAmountMinutes(pricePlanName);
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan(pricePlanName);
        String ringToNumber = "8722413731";
        buyingBasic800Number.enterRingToNumber(ringToNumber);
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        String discountPromoCode = Double.toString(PromoCodes.PERCENT_PROMOCODE.getValue());
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_30, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderDetailPage.checkingCorrectDataOrderBasic800Flow(displayedName, pricePlanName, pricePlan, amountMinutes,
                additionalCost, priceActivationFee, "-", discountPromoCode, PromoCodes.PERCENT_PROMOCODE.getName(), "Success");
    }

    @Test
    public void test4OrderPriceOverrideLocalPortANumberWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,searchRequest);
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        double oldPrice = linksListingPage.getOldPrice();
        String displayedName = linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
        double customerPrice = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        String phoneUpsellName = "Port A Number";
        double phoneUpsellPrice = buyingLocalNumber.choosePlan(phoneUpsellName);
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_31, CreditCards.VISA_STRIPE, false);
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, oldPrice, customerPrice, 0, 0, 0,
                phoneUpsellName, phoneUpsellPrice, "", "", 0, 0, 0, 0, "",
                "-", "-", "Success");
    }

    @Test
    public void test5OrderPriceOverrideLocalParkANumberWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,searchRequest);
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        double oldPrice = linksListingPage.getOldPrice();
        String displayedName = linksListingPage.generateLinkWithPromoCodeRegularFlow("601", "-TEST");
        double customerPrice = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        String phoneUpsellName = "Park A Number";
        double phoneUpsellPrice = buyingLocalNumber.choosePlan(phoneUpsellName);
        buyingLocalNumber.goToCheckout();
        String discountPromoCode = Double.toString(PromoCodes.FIXED_PROMOCODE.getValue());
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_29, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, oldPrice, customerPrice, 0, 0, 0,
                phoneUpsellName, phoneUpsellPrice, "", "", 0, 0, 0, 0, "",
                discountPromoCode, PromoCodes.FIXED_PROMOCODE.getName(), "Success");
    }

    @Test
    public void orderLocalNumberPickPlanWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("9998050");
        localSearchResult.chooseNumberFromLocalNumbersList(2);
        String displayedName = buyingRegularVanityNumber.getPhoneNumber().getText();
        double oldPrice = buyingLocalNumber.getPriceNumber();
        String phoneUpsellName = "Pick A Plan";
        double phoneUpsellPrice = buyingLocalNumber.choosePlan(phoneUpsellName);
        String planName = "Preferred";
        String planDescription = buyingLocalNumber.getDescriptionPlan(planName);
        double subscriptionPrice = buyingLocalNumber.choosePickYourMonthlyPlan(planName);
        String ringToNumber = "0668843478";
        buyingLocalNumber.enterRingToNumberWithMultipleCheckbox(ringToNumber);
        buyingLocalNumber.goToCheckout();
        String discountPromoCode = Double.toString(PromoCodes.PERCENT_PROMOCODE.getValue());
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_30, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, oldPrice, 0, subscriptionPrice, 0, 0,
                phoneUpsellName, phoneUpsellPrice, planName, planDescription, 0, 0, 0, 0, "",
                discountPromoCode, PromoCodes.PERCENT_PROMOCODE.getName(), "Success");
    }

}
