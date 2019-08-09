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
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage.generateLinkWithoutPromoCodeRegularFlow("10");
        double price = linksListingPage.clickGenerateLinkButton();
        String generatedLink = linksListingPage.getGeneratedLink(0);
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 Year");
        buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        boolean isPromocode = checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_20, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithoutPromoCodeRegularFlow(priceMonthlyMinutes, discountPriceSelectedPlan, price, isPromocode);
    }

    @Test
    public void test2GenerateLinkWithPromoCode() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        // inventoryTollfree.searchNumber(0, "8335897464");
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("101", "888-WWW-8709-HI");
        double price = linksListingPage.clickGenerateLinkButton();
        String generatedLink = linksListingPage.getGeneratedLink(1);
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose250MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 Years");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_21, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithFixedPromoCodeRegularFlow(priceMonthlyMinutes, discountPriceSelectedPlan, price, displayedName);
    }

    @Test
    public void test3GenerateLinkAndEdit() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithoutPromoCodeRegularFlow("10.01");
        linksListingPage.clickGenerateLinkButton();
        linksListingPage.clickEditButton(2);
        String displayedName = linksListingPage
                .generateLinkWithPromoCodeRegularFlow("111.99", "888-WWW-US-0911");
        double price = linksListingPage.clickGenerateLinkButton();
        String generatedLink = linksListingPage.getGeneratedLink(2);
        linksListingPage.goToGeneratedLink(generatedLink);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("Month");
        buyingRegularVanityNumber.enterRingToNumber("0668843471");
        buyingRegularVanityNumber.goToCheckout();
        boolean isPromocode = checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_21, CreditCards.DISCOVER_STRIPE, false);
        orderConfirmationPage.checkingGeneratedLinkWithPercentPromoCodeRegularFlow(priceMonthlyMinutes, discountPriceSelectedPlan, price, displayedName);
    }


    @Test
    public void test4CopyLink() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        String phoneNumber = inventoryTollfree.clickCreateNewLinkByNumber(1);
        System.out.println(phoneNumber);
        linksListingPage.clickCreateNewURLButton();
        linksListingPage
                .generateLinkWithPromoCodeRegularFlow("11.99", "888-WWW-US-09");
        double price = linksListingPage.clickGenerateLinkButton();
        linksListingPage.clickCopyButton(3);
        linksListingPage.goToGeneratedLinkAfterCopyPaste(3);
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("Month");
        buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.goToCheckout();
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_22, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseWithHighFixedPromoCode(priceMonthlyMinutes, discountPriceSelectedPlan, price);
    }

    @Test
    public void test5DeleteLink() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        inventoryTollfree.clickCreateNewLinkByNumber(1);
        String generatedLink = linksListingPage.getGeneratedLink(2);
        linksListingPage.clickDeleteButton(2);
        String linkAfterDelete = linksListingPage.getGeneratedLink(2);
        linksListingPage.checkingAfterDelete(generatedLink, linkAfterDelete);
    }
}