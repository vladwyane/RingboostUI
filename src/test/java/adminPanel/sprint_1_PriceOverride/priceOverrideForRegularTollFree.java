package adminPanel.sprint_1_PriceOverride;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.Admin;
import pages.admin.InventoryTollfree;
import pages.admin.LinksListingPage;
import pages.admin.Login;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 8/2/2019.
 */
public class priceOverrideForRegularTollFree extends TestBase {

    private Login login;
    private Admin admin;
    private LinksListingPage linksListingPage;
    private InventoryTollfree inventoryTollfree;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private HomePage homePage;
    private VanitySearchResult vanitySearchResult;

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
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

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1GenerateLinkWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "WWW8709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        double price = 0;
        String displayedName = null;
        for (int i = 0; i < 3; i++) {
            linksListingPage.clickCreateNewURLButton();
            displayedName = linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
            price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        }
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 year");
        buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        boolean isPromocode = checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_38, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithoutPromoCodeRegularFlow(priceMonthlyMinutes, discountPriceSelectedPlan, price, isPromocode, displayedName);
    }

    @Test
    public void test2CheckingDeactivatedStatus() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "WWW8709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.checkingStatusDeactivateOfAllLinks("Status");
    }

    @Test
    public void test2GenerateLinkWithPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9998709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        double price = 0;
        String displayedName = null;
        for (int i = 0; i < 3; i++) {
            linksListingPage.clickCreateNewURLButton();
            displayedName = linksListingPage
                    .generateLinkWithPromoCodeRegularFlow("101", "-EST");
            price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        }
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 years");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_39, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithFixedPromoCodeRegularFlow(priceMonthlyMinutes, discountPriceSelectedPlan, price, displayedName);
    }

    @Test
    public void test3CheckingCompleteStatus() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9998709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.checkingStatusComplete("Status", linksListingPage.returnIndexLastGeneratedLink());
    }

    @Test
    public void test3GenerateLinkAndEdit() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9998709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(2);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithoutPromoCodeRegularFlow("10.01");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickEditButton(linksListingPage.returnIndexLastGeneratedLink());
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("111.99", "1234");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("month");
        buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_39, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithPercentPromoCodeRegularFlow(priceMonthlyMinutes, discountPriceSelectedPlan, price, displayedName);
    }

    @Test
    public void test4CheckingErrorMessageIsLicenced() throws InterruptedException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9998709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(2);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.checkingErrorMessageNumberIsLicenced();
    }

    @Test
    public void test4CopyLink() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9998709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(3);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithPromoCodeRegularFlow("11.99", "-ABC");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickCopyButton(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLinkAfterCopyPaste(linksListingPage.returnIndexLastGeneratedLink());
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("month");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_40, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithHighFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, price);
    }

    @Test
    public void test5DeleteLink() throws InterruptedException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9998709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(4);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.deleteAllLink();
        String linkAfterDelete = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.checkingAfterDelete(generatedLink, linkAfterDelete);
    }


    @Test
    public void test6CheckingDeactivateStatusIfNumberLicensedFromSite() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9998709");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(2).substring(3, 10);
        System.out.println(phoneNumber);
        for (int i = 0; i < 3; i++) {
            linksListingPage.clickCreateNewURLButton();
            linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
            linksListingPage.clickGenerateLinkButtonRegularFlow();
        }
        homePage.open();
        homePage.searchTollFreeNumbers("9998709");
        vanitySearchResult.chooseIndexNumberFromRegularVanityList(2);
        buyingRegularVanityNumber.choose5000MonthlyMinutes();
        buyingRegularVanityNumber.chooseTermLength("1 year");
        buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_40, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9998709");
        inventoryTollfree.clickCreateNewLinkByNumber(2);
        linksListingPage.checkingStatusDeactivateOfAllLinks("Status");

    }
}
