package adminPanel.sprint_1_PriceOverride;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.front.Checkout;
import pages.front.OrderConfirmationPage;
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
        inventoryTollfree.searchNumber(0, "4204er5");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        double payToday = 0;
        String displayedName = null;
        for (int i = 0; i < 3; i++) {
            linksListingPage.clickCreateNewURLButton();
            displayedName = linksListingPage
                    .generateLinkWithoutPromoCodePremiumFlow("23", "Kansas", i + 1,
                            "1 year", "750");
            payToday = linksListingPage.clickGenerateLinkButtonPremiumFlow();
        }

        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        boolean isPromocode = checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_44, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithoutPromoCodePremiumFlow(payToday, isPromocode, displayedName);
    }

    @Test
    public void test2CheckingCompleteStatus() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "4204er5");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.checkingStatusComplete("Status", linksListingPage.returnIndexLastGeneratedLink());
    }

    @Test
    public void test2CheckingDeactivatedStatus() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "4204er5");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        System.out.println(phoneNumber);
        linksListingPage.checkingStatusDeactivateOfAllLinks("Status");
    }

    @Test
    public void test2GenerateLinkWithPromoCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "4204er5");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithPromoCodePremiumFlow("890", "Alabama", 2,
                        "Month-To-Month", "5000", "-BUG");
        double payToday = linksListingPage.clickGenerateLinkButtonPremiumFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        checkout.addPromoCode(PromoCodes.FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_46, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithFixedPromoCodePremiumFlow(payToday, displayedName);

    }

    @Test
    public void test3GenerateLinkAndEdit() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "4204375");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(2);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithoutPromoCodePremiumFlow("10.01", "Alabama", 2,
                        "1 years", "750");
        linksListingPage.clickGenerateLinkButtonRegularFlow();
        linksListingPage.clickEditButton(linksListingPage.returnIndexLastGeneratedLink());
        String displayedName = linksListingPage
                .generateLinkWithPromoCodePremiumFlow("4001.99", "Texas", 2,
                        "2 year", "100", "1234");
        double payToday = linksListingPage.clickGenerateLinkButtonPremiumFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLink(generatedLink);
        checkout.addPromoCode(PromoCodes.PERCENT_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_45, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithPercentPromoCodePremiumFlow(payToday, displayedName);
    }

    @Test
    public void test4CopyLink() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "4204375");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(4);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithPromoCodePremiumFlow("11.99", "Alabama", 1,
                        "3 years", "250", "-ABC");
        double payToday = linksListingPage.clickGenerateLinkButtonPremiumFlow();
        linksListingPage.clickCopyButton(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.goToGeneratedLinkAfterCopyPaste(linksListingPage.returnIndexLastGeneratedLink());
        checkout.addPromoCode(PromoCodes.HIGH_FIXED_PROMOCODE.getName());
        checkout.fillCheckout(Users.VLADYSLAV_46, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingGeneratedLinkWithHighFixedPromoCodePremiumFlow(payToday, displayedName);
    }

    @Test
    public void test5DeleteLink() throws InterruptedException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "4204375");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(3);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithoutPromoCodePremiumFlow("10.01", "Alabama", 1,
                        "1 years", "750");
        linksListingPage.clickGenerateLinkButtonPremiumFlow();
        String generatedLink = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.deleteAllLink();
        String linkAfterDelete = linksListingPage.getGeneratedLink(linksListingPage.returnIndexLastGeneratedLink());
        linksListingPage.checkingAfterDelete(generatedLink, linkAfterDelete);
    }

    @Test
    public void test6CheckingStatusLicensed() throws InterruptedException {
        login.open();
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "4204375");
        inventoryTollfree.checkingStatusLicensed("Status", 4);
    }

}
