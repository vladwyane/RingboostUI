import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

/**
 * Created by bigdrop on 6/14/2019.
 */
public class BuyingLocalPortNumbers extends TestBase {

    private HomePage homePage;
    private LocalIndexPage localIndexPage;
    private BuyingLocalNumber buyingLocalNumber;
    private LocalSearchResult localSearchResult;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;


    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());

    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void orderLocalPortNumber() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan("Port a Number");
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_7, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumber(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberWithFixedPromoCode() throws InterruptedException {
        homePage.open();
        homePage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan("Port a Number");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_8, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumberWithFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberWithHighFixedPromoCode() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("2413731");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan("Port a Number");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_9, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumberWithHighFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberWithPercentPromoCode() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan("Port a Number");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_8, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchasePortNumberWithPercentPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberAfterRemovePromoCodee() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        double pricePlan = buyingLocalNumber.choosePlan("Port a Number");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_9, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchasePortNumberAfterRemovePromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalPortNumberPaymentError() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Port a Number");
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_7, CreditCards.ERROR_STOLEN_CARD_STRIPE, false);
        checkout.checkingPaymentError();
    }

}
