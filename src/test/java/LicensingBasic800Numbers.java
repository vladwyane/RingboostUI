import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

/**
 * Created by bigdrop on 6/19/2019.
 */
public class LicensingBasic800Numbers extends TestBase {


    private HomePage homePage;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private BasicIndexPage basicIndexPage;
    private BuyingBasic800Number buyingBasic800Number;

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        basicIndexPage = new BasicIndexPage(app.getDriver());
        buyingBasic800Number = new BuyingBasic800Number(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void orderBasic800Number() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.choose32thNumberFromBasic800List();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Business Pro");
        buyingBasic800Number.enterRingToNumber("8722413731");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_7, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseBasic800Number(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberWithFixedPromoCode() throws InterruptedException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic");
        basicIndexPage.chooseFirstNumberFromBasic800List();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Premium");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_8, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithFixedPromoCode(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberWithHighFixedPromoCode() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800ListAfterLoadMore();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Starter");
        buyingBasic800Number.enterRingToNumber("0668843478");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_9, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithHighFixedPromoCode(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberWithPercentPromoCode() throws InterruptedException {
        homePage.open();
        homePage.clickSubNavItemTollFree("basic");
        basicIndexPage.chooseLastNumberFromBasic800ListAfterLoadMore();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Business Pro");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_8, CreditCards.AMERICAN_EXPRESS_STRIPE, true);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberWithPercentPromoCode(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberAfterRemovePromoCodee() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800ListAfterLoadMore();
        double pricePlan = buyingBasic800Number.choosePickYourMonthlyPlan("Starter");
        buyingBasic800Number.enterRingToNumber("0668843478");
        double priceActivationFee = buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.addPromoCodeAndAfterRemove("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_7, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseBasic800NumberAfterRemovePromoCode(priceActivationFee, pricePlan);
    }

    @Test
    public void orderBasic800NumberPaymentError() throws InterruptedException {
        basicIndexPage.open();
        basicIndexPage.chooseFirstNumberFromBasic800ListAfterLoadMore();
        buyingBasic800Number.choosePickYourMonthlyPlan("Premium");
        buyingBasic800Number.chooseCheckboxMultipleRingToNumber();
        buyingBasic800Number.getPriceActivationFee();
        buyingBasic800Number.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_9, CreditCards.ERROR_INSUFFICIENT_FUNDS_STRIPE, false);
        checkout.checkingPaymentError();
    }

}
