package adminPanel.sprint_1_PriceOverride;

import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BuyingRegularVanityNumber;
import pages.Checkout;
import pages.OrderConfirmationPage;
import pages.admin.Admin;
import pages.admin.InventoryTollfree;
import pages.admin.LinksListingPage;
import pages.admin.Login;
import testBase.TestBase;

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

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        inventoryTollfree = new InventoryTollfree(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1GenerateLinkWithoutPromoCode() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        // inventoryTollfree.searchNumber(0, "8335897464");
        inventoryTollfree.clickCreateNewLinkByNumber(1);
        String displayedName = linksListingPage
                .generateLinkWithoutPromoCodeRegularFlow("23", "Colorado");
        double price = linksListingPage.clickGenerateLinkButton();
        String generatedLink = linksListingPage.getGeneratedLink(1);
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("Month");
        buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        boolean isPromocode = checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_20, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkAfterPurchaseRegularFlow(priceMonthlyMinutes, discountPriceSelectedPlan, price, isPromocode, displayedName);
    }

    @Test
    public void test2GenerateLinkWithPromoCode() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        // inventoryTollfree.searchNumber(0, "8335897464");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage
                .generateLinkWithPromoCodeRegularFlow("234");
        double price = linksListingPage.clickGenerateLinkButton();
        String generatedLink = linksListingPage.getGeneratedLink(2);
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 Years");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_21, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseWithFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, price);
    }

    @Test
    public void test2GenerateLinkWithPe() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        inventoryTollfree.clickCreateNewLinkByNumber(0);
        linksListingPage.clickEditButton(1);
    }

    @Test
    public void test3CopyLink() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        inventoryTollfree.clickCreateNewLinkByNumber(0);
        linksListingPage.clickCopyButton(1);
        linksListingPage.goToGeneratedLinkAfterCopyPaste();
    }

    @Test
    public void test4DeleteLink() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        inventoryTollfree.clickCreateNewLinkByNumber(1);
        String generatedLink = linksListingPage.getGeneratedLink(7);
        linksListingPage.clickDeleteButton(7);
        String linkAfterDelete = linksListingPage.getGeneratedLink(7);
        linksListingPage.checkingAfterDelete(generatedLink, linkAfterDelete);
    }
}
