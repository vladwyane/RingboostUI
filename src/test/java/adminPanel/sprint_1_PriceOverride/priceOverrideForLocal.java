package adminPanel.sprint_1_PriceOverride;

import data.CreditCards;
import data.Users;
import org.testng.annotations.*;
import pages.BuyingLocalNumber;
import pages.Checkout;
import pages.OrderConfirmationPage;
import pages.admin.*;
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

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        inventoryLocal = new InventoryLocal(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1GenerateLinkWithoutPromoCode() throws InterruptedException, IOException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,"0ZUPO");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(0);
        linksListingPage.goToGeneratedLink(generatedLink);
        buyingLocalNumber.choosePlan("Port A Number");
        buyingLocalNumber.goToCheckout();
        boolean isPromocode = checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithoutPromoCodePortNumber(price, isPromocode);
    }

    @Test
    public void test2GenerateLinkWithPromoCode() throws InterruptedException, IOException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("601", "2125170ZUP");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(0);
        linksListingPage.goToGeneratedLink(generatedLink);
        double pricePlan = buyingLocalNumber.choosePlan("Park A Number");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_24, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinParkNumberWithFixedPromoCode(price, pricePlan, displayedName);
    }

    @Test
    public void test3GenerateLinkAndEdit() throws InterruptedException, IOException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(2);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithoutPromoCodeRegularFlow("10.01");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickEditButton(0);
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("1110.99", "ADYL-000-987");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(0);
        linksListingPage.goToGeneratedLink(generatedLink);
        buyingLocalNumber.choosePlan("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Preferred");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_23, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinParkNumberWithPercentPromoCode(price, pricePlan, displayedName);
    }

    @Test
    public void test4CopyLink() throws InterruptedException, IOException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(3);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("210.99", "AMSY-300-ZUP");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickCopyButton(0);
        linksListingPage.goToGeneratedLinkAfterCopyPaste(0);
        buyingLocalNumber.choosePlan("Pick A Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Starter");
        buyingLocalNumber.enterRingToNumber("9968843478");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_23, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinParkNumberWithHighFixedPromoCode(price, pricePlan, displayedName);
    }

    @Test
    public void test5DeleteLink() throws InterruptedException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(4);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("1123");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(0);
        linksListingPage.deleteAllLink();
        String linkAfterDelete = linksListingPage.getGeneratedLink(0);
        linksListingPage.checkingAfterDelete(generatedLink, linkAfterDelete);
    }

    @Test
    public void test6inVisibleCreateNewURL() throws InterruptedException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,"0987");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(5);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("1123");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickBreadcrunbsLink("Local");
        inventoryLocal.searchNumber(0,"0987");
        inventoryLocal.clickCreateNewLinkByNumber(5);
        linksListingPage.checkingInvisibleCreateNewURL();
    }

    @Test
    public void test7GenerateLinkWithoutPickPlan() throws InterruptedException, IOException {
        login.open();
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0,"0ZUP");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(6);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("4520.11");
        double price = linksListingPage.clickGenerateLinkButtonRegularFlow();
        String generatedLink = linksListingPage.getGeneratedLink(0);
        linksListingPage.goToGeneratedLink(generatedLink);
        buyingLocalNumber.clickLinkContinueToCheckout();
        boolean isPromocode = checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithoutPromoCodePortNumber(price, isPromocode);
    }

}
