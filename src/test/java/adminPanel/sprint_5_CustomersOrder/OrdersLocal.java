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
 * Created by bigdrop on 11/26/2019.
 */
public class OrdersLocal extends TestBase {

    private Login login;
    private Admin admin;
    private OrderListingPage orderListingPage;
    private OrderDetailPage orderDetailPage;
    private LinksListingPage linksListingPage;
    private InventoryTollfree inventoryTollfree;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private VanitySearchResult vanitySearchResult;
    private HomePage homePage;
    private LocalIndexPage localIndexPage;
    private BuyingLocalNumber buyingLocalNumber;
    private LocalSearchResult localSearchResult;
    private InventoryLocal inventoryLocal;

    private String searchRequest = "812288";
    private String searchRequest2 = "POLOS";


    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        homePage = new HomePage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        orderListingPage = new OrderListingPage(app.getDriver());
        orderDetailPage = new OrderDetailPage(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        inventoryTollfree = new InventoryTollfree(app.getDriver());
        checkout = new Checkout(app.getDriver());
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        inventoryLocal = new InventoryLocal(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Ignore
    @Test
    public void test1CheckingCorrectColumnOrdersLocalListing() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersLocal();
        orderListingPage.checkingCorrectColumnOrdersLocalListing();
    }

    @Ignore
    @Test
    public void test1CheckingOrdersLocalListingIsNotEmpty() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersLocal();
        orderListingPage.checkingTableNotEmpty();
    }

    @Test
    public void test1orderLocalPortNumberPriceOverrideWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        inventoryLocal.open();
        inventoryLocal.searchNumber(0,searchRequest);
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        if(linksListingPage.getTdSize() > 1)
            linksListingPage.clickCreateNewURLButton();
        String pricePayment = "$" + String.valueOf((int) linksListingPage.getOldPrice()) + ".00";
        String displayedName = linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10.99");
        String customerPrice = "$" + String.valueOf(linksListingPage.clickGenerateLinkButtonRegularFlow());
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        String phoneUpsellName = "Port A Number";
        String phoneUpsellPrice = "$" + String.valueOf((int)buyingLocalNumber.getPhoneUpsellPrice(phoneUpsellName));
        buyingLocalNumber.goToCheckout();
        String payToday = checkout.getPricePayToday();
        String promoCode = "";
        String pricePlan = "";
        checkout.fillCheckout(Users.VLADYSLAV_67, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersLocal();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, pricePayment, customerPrice, "$0.00", "$0.00", payToday,
                phoneUpsellName, pricePlan, "", "", promoCode, "Completed", Users.VLADYSLAV_67);
    }

    @Test
    public void test2orderLocalParkANumberPriceOverrideWithFixedPromoCode() throws InterruptedException, IOException, JSONException {
        inventoryLocal.open();
        inventoryLocal.searchNumber(0, searchRequest);
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        if(linksListingPage.getTdSize() > 1)
            linksListingPage.clickCreateNewURLButton();
        String pricePayment = "$" + String.valueOf(linksListingPage.getOldPrice()) + "0";
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("601.01", "-TEST");
        String customerPrice = "$" + String.valueOf(linksListingPage.clickGenerateLinkButtonRegularFlow());
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        String phoneUpsellName = "Park A Number";
        String phoneUpsellPrice = "$" + String.valueOf(buyingLocalNumber.getPhoneUpsellPrice(phoneUpsellName));
        String subsPrice = phoneUpsellPrice;
        buyingLocalNumber.goToCheckout();
        String discountPromoCode = Integer.toString((int)PromoCodes.FIXED_PROMOCODE.getValue());
        String promoCodeName = PromoCodes.FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " $";
        String pricePlan = "";
        checkout.fillCheckout(Users.VLADYSLAV_66, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersLocal();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, pricePayment, customerPrice, subsPrice, subsPrice, payToday,
                phoneUpsellName,  pricePlan, "","", promoCode, "Completed", Users.VLADYSLAV_66);
    }

    @Test
    public void test3orderLocalParkNumberWithHighFixedPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest);
        localSearchResult.chooseNumberFromLocalNumbersList(3);
        String displayedName = buyingLocalNumber.getPhoneNumber().getText();
        String pricePayment = "$" + String.valueOf(Math.round(buyingLocalNumber.getPriceNumber()* 100.0) / 100.0) + "0";
        String phoneUpsellName = "Park A Number";
        String phoneUpsellPrice = "$" + String.valueOf(buyingLocalNumber.getPhoneUpsellPrice(phoneUpsellName));
        String subsPrice = phoneUpsellPrice;
        buyingLocalNumber.goToCheckout();
        String discountPromoCode = Integer.toString((int)PromoCodes.HIGH_FIXED_PROMOCODE.getValue());
        String promoCodeName = PromoCodes.HIGH_FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " $";
        String pricePlan = "";
        checkout.fillCheckout(Users.VLADYSLAV_66, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersLocal();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, pricePayment, "", subsPrice, subsPrice, payToday,
                phoneUpsellName, pricePlan, "", "", promoCode, "Completed", Users.VLADYSLAV_66);
    }

    @Test
    public void test4orderLocalNumberPickPlanWithPercentPromoCode() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest2);
        localSearchResult.chooseNumberFromLocalNumbersList(1);
        String displayedName = buyingLocalNumber.getPhoneNumber().getText();
        String pricePayment = "$" + String.valueOf(Math.round(buyingLocalNumber.getPriceNumber()* 100.0) / 100.0) + "0";
        String phoneUpsellName = "Pick A Plan";
        String pricePlan = "Preferred";
        String phoneUpsellPrice = "$" + String.valueOf(buyingLocalNumber.getPhoneUpsellPrice(phoneUpsellName));
        int value = (int)Math.round(buyingLocalNumber.getAdditionalCoast(pricePlan) * 100);
        String addCoast = "$" + String.valueOf((double)value/100);
        String pricePlanName = pricePlan + " -  " + buyingLocalNumber.getDescriptionPlan(pricePlan);
        String subsPrice = "$" + String.valueOf(buyingLocalNumber.choosePickYourMonthlyPlan(pricePlan));
        String ringToNumber = "0668843478";
        buyingLocalNumber.enterRingToNumber(ringToNumber);
        buyingLocalNumber.goToCheckout();
        String discountPromoCode = Integer.toString((int)PromoCodes.PERCENT_PROMOCODE.getValue());
        String promoCodeName = PromoCodes.PERCENT_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " %";
        checkout.fillCheckout(Users.VLADYSLAV_67, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersLocal();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, pricePayment, "", subsPrice, "", payToday,
                phoneUpsellName, pricePlanName, addCoast, ringToNumber, promoCode, "Completed", Users.VLADYSLAV_67);
    }

    @Test
    public void test5orderLocalNumberPickPlanAfterRemovePromoCodee() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest2);
        localSearchResult.chooseNumberFromLocalNumbersList(2);
        String displayedName = buyingLocalNumber.getPhoneNumber().getText();
        String pricePayment = "$" + String.valueOf(Math.round(buyingLocalNumber.getPriceNumber()* 100.0) / 100.0);
        String phoneUpsellName = "Pick A Plan";
        String phoneUpsellPrice = "$" + String.valueOf(buyingLocalNumber.getPhoneUpsellPrice(phoneUpsellName));
        String pricePlan = "Premium";
        String pricePlanName = pricePlan + " -  " + buyingLocalNumber.getDescriptionPlan(pricePlan);
        int value = (int)Math.round(buyingLocalNumber.getAdditionalCoast(pricePlan) * 100);
        String addCoast = "$" + String.valueOf((double)value/100);
        String subsPrice = "$" + String.valueOf(buyingLocalNumber.choosePickYourMonthlyPlan(pricePlan));
        String ringToNumber = "0668843478";
        buyingLocalNumber.enterRingToNumber(ringToNumber);
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.wait2SecUntilPromoRemove();
        String payToday = checkout.getPricePayToday();
        String promoCode = "";
        checkout.fillCheckout(Users.VLADYSLAV_66, CreditCards.JCB, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersLocal();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, pricePayment, "", subsPrice, "", payToday,
                phoneUpsellName, pricePlanName, addCoast, ringToNumber, promoCode, "Completed", Users.VLADYSLAV_66);
    }

    @Test
    public void test6orderLocalPortNumberWithoutPickPlan() throws InterruptedException, IOException, JSONException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest2);
        localSearchResult.chooseNumberFromLocalNumbersList(3);
        String displayedName = buyingLocalNumber.getPhoneNumber().getText();
        String pricePayment = "$" + String.valueOf(Math.round(buyingLocalNumber.getPriceNumber()* 100.0) / 100.0) + "0";
        String phoneUpsellName = "Port A Number";
        buyingLocalNumber.clickLinkContinueToCheckout();
        String discountPromoCode = Integer.toString((int)PromoCodes.PERCENT_PROMOCODE.getValue());
        String promoCodeName = PromoCodes.PERCENT_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " %";
        String pricePlan = "";
        checkout.fillCheckout(Users.VLADYSLAV_68, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersLocal();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, pricePayment, "", "$0.00", "", payToday,
                phoneUpsellName, pricePlan, "", "", promoCode, "Completed", Users.VLADYSLAV_68);
    }

    @Test
    public void test7orderLocalParkNumberPaymentError() throws InterruptedException, IOException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(searchRequest2);
        localSearchResult.chooseNumberFromLocalNumbersList(4);
        String displayedName = buyingLocalNumber.getPhoneNumber().getText();
        String pricePayment = "$" + String.valueOf(Math.round(buyingLocalNumber.getPriceNumber()* 100.0) / 100.0) + "0";
        String phoneUpsellName = "Port A Number";
        String phoneUpsellPrice = "$" + String.valueOf((int)buyingLocalNumber.getPhoneUpsellPrice(phoneUpsellName));
        buyingLocalNumber.goToCheckout();
        String discountPromoCode = Integer.toString((int)PromoCodes.FIXED_PROMOCODE.getValue());
        String promoCodeName = PromoCodes.FIXED_PROMOCODE.getName();
        checkout.addPromoCode(promoCodeName);
        String payToday = checkout.getPricePayToday();
        String promoCode = promoCodeName + " - " + discountPromoCode + " $";
        String pricePlan = "";
        checkout.fillCheckout(Users.VLADYSLAV_67, CreditCards.ERROR_STOLEN_CARD_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        orderConfirmationPage.wait5SecUntilOrderAddedInAdmin();
        login.open();
        admin.clickOrdersLocal();
        orderListingPage.clickEditIconFirstOrder();
        orderDetailPage.checkingCorrectDataOrderLocalFlow(displayedName, pricePayment, "", "$0.00", "", payToday,
                phoneUpsellName, pricePlan, "","", promoCode, "Failed", Users.VLADYSLAV_67);
    }
}
