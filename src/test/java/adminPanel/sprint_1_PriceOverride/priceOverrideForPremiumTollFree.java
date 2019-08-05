package adminPanel.sprint_1_PriceOverride;

import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Checkout;
import pages.OrderConfirmationPage;
import pages.admin.*;
import testBase.TestBase;

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

    @BeforeMethod
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

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1GenerateLinkWithPromoCode() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        // inventoryTollfree.searchNumber(0, "8335897464");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(0);
        linksListingPage.generateLinkWithPromoCode("23", "Colorado", 3, "2");
        String generatedLink = linksListingPage.getGeneratedLink(0);
        linksListingPage.goToGeneratedLink(generatedLink);
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_21, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithFixedPromoCode();
    }

    @Test
    public void test2GenerateLinkWithPromoCode() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        inventoryTollfree.clickCreateNewLinkByNumber(0);
        linksListingPage.clickEditButton(1);
    }
}
