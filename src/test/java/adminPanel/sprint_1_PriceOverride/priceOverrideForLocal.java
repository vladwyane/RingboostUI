package adminPanel.sprint_1_PriceOverride;

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
 * Created by bigdrop on 8/2/2019.
 */
public class priceOverrideForLocal extends TestBase{

    private Login login;
    private Admin admin;
    private LinksListingPage linksListingPage;
    private InventoryLocal inventoryLocal;
    private BuyingLocalNumber buyingLocalNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private LocalIndexPage localIndexPage;
    private LocalSearchResult localSearchResult;

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        inventoryLocal = new InventoryLocal(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
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
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0ZUPO");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        buyingLocalNumber.getPhoneUpsellPrice("Port A Number");
        buyingLocalNumber.goToCheckout();
        boolean isPromocode = checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_49, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithoutPromoCodePortNumber(price, isPromocode);
    }

    @Test
    public void test2GenerateLinkWithPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        double price = 0;
        String displayedName = null;
        for (int i = 0; i < 3; i++) {
            linksListingPage.clickCreateNewURLButton();
            displayedName = linksListingPage
                    .generateLinkWithPromoCodeRegularFlow("601", "-TEST");
            price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        }
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        double pricePlan = buyingLocalNumber.getPhoneUpsellPrice("Park A Number");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_50, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinParkNumberWithFixedPromoCode(price, pricePlan, displayedName);
    }


    @Test
    public void test3CheckingDeactivatedStatus() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.checkingStatusDeactivateOfAllLinks("Status");
    }

    @Test
    public void test3GenerateLinkAndEdit() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(2);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithoutPromoCodeRegularFlow("10.01");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickEditButton(linksListingPage.returnIndexLastGeneratedLink());
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("1110.99", "123456789");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        buyingLocalNumber.getPhoneUpsellPrice("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Preferred");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_48, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinParkNumberWithPercentPromoCode(price, pricePlan, displayedName);
    }

    @Test
    public void test4CopyLink() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(3);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("210.99", "-ABCD-EFG");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickCopyButton(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLinkAfterCopyPaste(linksListingPage.returnIndexLastGeneratedLink());
        buyingLocalNumber.getPhoneUpsellPrice("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Starter");
        buyingLocalNumber.enterRingToNumber("9968843478");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_50, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinParkNumberWithHighFixedPromoCode(price, pricePlan, displayedName);
    }

    @Test
    public void test5CheckingErrorMessageIsSold() throws InterruptedException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(3);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("1123");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.checkingErrorMessageNumberIsSold();
    }

    @Test
    public void test5DeleteLink() throws InterruptedException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(4);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("1123");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.deleteAllLink();
        String linkAfterDelete = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.checkingAfterDelete(generatedLink, linkAfterDelete);
    }

    @Test
    public void test6inVisibleCreateNewURL() throws InterruptedException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(5);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("1123");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickBreadcrunbsLink("Local");
        //inventoryLocal.searchNumber(0,"0987");
        inventoryLocal.clickCreateNewLinkByNumber(5);
        linksListingPage.checkingInvisibleCreateNewURL();
    }

    @Test
    public void test7GenerateLinkWithoutPickPlan() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0ZUP");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(6);
        System.out.println(phoneNumber);
        double price = 0;
        for (int i = 0; i < 3; i++) {
            linksListingPage.clickCreateNewURLButton();
            linksListingPage.generateLinkWithoutPromoCodeRegularFlow("4520.11");
            price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        }
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        buyingLocalNumber.clickLinkContinueToCheckout();
        boolean isPromocode = checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_49, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithoutPromoCodePortNumber(price, isPromocode);
    }

    @Test
    public void test8CheckingCompleteStatus() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"0ZUPOTE");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(6);
        System.out.println(phoneNumber);
        linksListingPage.checkingStatusComplete("Status", linksListingPage.returnIndexLastGeneratedLink());
    }

    @Test
    public void test9CheckingDeactivateStatusIfNumberSoldFromSite() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickLocalInventoryLink();
        //inventoryLocal.searchNumber(0,"655560");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(7).substring(3, 10);
        System.out.println(phoneNumber);
        for (int i = 0; i < 3; i++) {
            linksListingPage.clickCreateNewURLButton();
            linksListingPage.generateLinkWithoutPromoCodeRegularFlow("4520.11");
            linksListingPage.clickGenerateLinkButtonRegularFlow();
        }
        localIndexPage.open();
        localIndexPage.searchLocalNumbers(phoneNumber);
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.getPhoneUpsellPrice("Port A Number");
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_49, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.waitUntilConfirmationMessageAppears();
        login.open();
        admin.clickLocalInventoryLink();
       // inventoryLocal.searchNumber(0,"655560");
        inventoryLocal.clickCreateNewLinkByNumber(7);
        linksListingPage.checkingStatusDeactivateOfAllLinks("Status");

    }

}
