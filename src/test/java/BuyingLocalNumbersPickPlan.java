import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

public class BuyingLocalNumbersPickPlan extends TestBase {

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
    public void orderLocalNumberPickPlan() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick a Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Preffered");
        buyingLocalNumber.enterRingToNumber("8722413731");
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_4, CreditCards.VISA_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumber(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanWithFixedPromoCode() throws InterruptedException {
        homePage.open();
        homePage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick a Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Premium");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("springsale");
        checkout.fillCheckout(Users.VLADYSLAV_5, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanWithHighFixedPromoCode() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("2413731");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick a Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Starter");
        buyingLocalNumber.enterRingToNumber("0668843478");
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("summersale");
        checkout.fillCheckout(Users.VLADYSLAV_6, CreditCards.MASTERCART_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithHighFixedPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanWithPercentPromoCode() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick a Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Preffered");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCode("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_5, CreditCards.AMERICAN_EXPRESS_STRIPE, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberWithPercentPromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanAfterRemovePromoCodee() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        double priceNumber = buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick a Plan");
        double pricePlan = buyingLocalNumber.choosePickYourMonthlyPlan("Premium");
        buyingLocalNumber.chooseCheckboxMultipleRingToNumber();
        buyingLocalNumber.goToCheckout();
        checkout.addPromoCodeAndAfterRemove("wintersale");
        checkout.fillCheckout(Users.VLADYSLAV_5, CreditCards.JCB, false);
        orderConfirmationPage.checkingYourPurchaseParkNumberAfterRemovePromoCode(priceNumber, pricePlan);
    }

    @Test
    public void orderLocalNumberPickPlanPaymentError() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.searchLocalNumbers("");
        localSearchResult.chooseFirstNumberFromLocalNumbersList();
        buyingLocalNumber.getPriceNumber();
        buyingLocalNumber.choosePlan("Pick a Plan");
        buyingLocalNumber.choosePickYourMonthlyPlan("Starter");
        buyingLocalNumber.enterRingToNumber("0668843478");
        buyingLocalNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV_4, CreditCards.ERROR_PROCESSING_STRIPE, false);
        checkout.checkingPaymentError();
    }
}
