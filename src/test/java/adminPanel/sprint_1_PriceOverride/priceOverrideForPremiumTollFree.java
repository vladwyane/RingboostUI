package adminPanel.sprint_1_PriceOverride;

import data.CreditCards;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.Checkout;
import pages.OrderConfirmationPage;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 8/2/2019.
 */
public class priceOverrideForPremiumTollFree extends TestBase {

    private Login login;
    private Admin admin;
    private LinksListingPage linksListingPage;
    private InventoryTollfree inventoryTollfree;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        inventoryTollfree = new InventoryTollfree(app.getDriver());
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
    public void test1GenerateLinkWithoutPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "999ZIX0");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithoutPromoCodePremiumFlow("23", "Kansas", 3,
                        "1 year", "750");
        double payToday = linksListingPage.clickGenerateLinkButtonPremiumFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        boolean isPromocode = checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_23, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithoutPromoCodePremiumFlow(payToday, isPromocode, displayedName);
    }

    @Test
    public void test2GenerateLinkWithPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9999490");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithPromoCodePremiumFlow("890", "Alabama", 2,
                        "Month-To-Month", "5000", "-TEST");
        double payToday = linksListingPage.clickGenerateLinkButtonPremiumFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithFixedPromoCodePremiumFlow(payToday, displayedName);

    }

    @Test
    public void test3GenerateLinkAndEdit() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9999490");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithoutPromoCodePremiumFlow("10.01", "Alabama", 2,
                        "1 years", "750");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickEditButton(linksListingPage.returnIndexLastGeneratedLink());
        String displayedName = linksListingPage
                .generateLinkWithPromoCodePremiumFlow("4001.99", "Texas", 2,
                        "2 year", "100", "1234567");
        double payToday = linksListingPage.clickGenerateLinkButtonPremiumFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_24, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithPercentPromoCodePremiumFlow(payToday, displayedName);
    }

    @Test
    public void test4CopyLink() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9999490");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithPromoCodePremiumFlow("11.99", "Vermont", 1,
                        "3 years", "250", "-ABCDEF");
        double payToday = linksListingPage.clickGenerateLinkButtonPremiumFlow();
        linksListingPage.clickCopyButton(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLinkAfterCopyPaste(linksListingPage.returnIndexLastGeneratedLink());
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_25, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingGeneratedLinkWithHighFixedPromoCodePremiumFlow(payToday, displayedName);
    }

    @Test
    public void test5CheckingLicensedAreaCodes() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9999490");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithPromoCodePremiumFlow("11.99", "Vermont", 1,
                        "3 years", "250", "-ABCDEF");
        linksListingPage.checkingDisabledGenerateLinkButtonPremiumFlow();
    }

    @Test
    public void test5CheckingStatusCompleteAllLinks() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9999490");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.checkingStatusCompleteOfAllLinks("Status");
    }

    @Test
    public void test6DeleteLink() throws InterruptedException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "9999490");
        inventoryTollfree.clickCreateNewLinkByNumber(0);
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.deleteAllLink();
        String linkAfterDelete = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.checkingAfterDelete(generatedLink, linkAfterDelete);
    }

}
