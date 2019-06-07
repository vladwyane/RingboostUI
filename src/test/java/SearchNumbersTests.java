import data.CreditCards;
import data.Users;
import org.testng.annotations.*;
import pages.*;
import testBase.TestBase;

/**
 * Created by bigdrop on 3/14/2019.
 */
public class SearchNumbersTests extends TestBase{

    private HomePage homePage;
    private VanityIndexPage vanityIndexPage;
    private VanitySearchResult vanitySearchResult;
    private TollFreeIndexPage tollFreeIndexPage;
    private BuyingRegularVanityNumber buyingRegularVanityNumber;
    private VanityCategoryDetail vanityCategoryDetail;
    private Checkout checkout;

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        vanityIndexPage = new VanityIndexPage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        tollFreeIndexPage = new TollFreeIndexPage(app.getDriver());
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
        vanityCategoryDetail = new VanityCategoryDetail(app.getDriver());
        checkout = new Checkout(app.getDriver());
    }


    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void loadingRegularVanityNumbersOnClickLoadMore() {
        vanitySearchResult.open();
        vanitySearchResult.searchTollFreeNumbers("bu");
        vanitySearchResult.checkingClickLoadMore();
    }

    @Test
    public void loadMoreIfAllNumbersIsLoaded() {
        homePage.open();
        homePage.clickSubNavItemTollFree("vanity");
        vanityIndexPage.searchTollFreeNumbers("bu");
        vanitySearchResult.checkingLoadMoreIfAllNumbersIsLoaded();
    }


    @Test
    public void buying() {
        homePage.open();
        tollFreeIndexPage.openTollFreeIndexPageFromMainNav();
        tollFreeIndexPage.searchTollFreeNumber("er");
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 Year");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.checkingOrderSummary(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void buying2()  {
        vanityIndexPage.open();
        vanityIndexPage.searchTollFreeNumbers("bug");
        vanitySearchResult.chooseLastNumberFromRegularVanityListAfterLoadMore();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("Month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.checkingOrderSummary(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void buying3() {
        homePage.open();
        homePage.searchTollFreeNumbers("te");
        vanitySearchResult.choose32thNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose500MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 Year");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumberWithMultipleCheckbox("8332702679");
        buyingRegularVanityNumber.checkingOrderSummary(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void order() throws InterruptedException {
        homePage.open();
        tollFreeIndexPage.openTollFreeIndexPageFromMainNav();
        tollFreeIndexPage.searchTollFreeNumber("er");
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        int discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("2 Year");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.goToCheckout();
        checkout.fillCheckout(Users.VLADYSLAV, CreditCards.VISA_STRIPE, true);

    }




}
