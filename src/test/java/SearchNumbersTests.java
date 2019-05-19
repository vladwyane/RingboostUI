import org.testng.annotations.*;
import pages.*;
import testBase.TestBase;

import java.io.IOException;

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

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        vanityIndexPage = new VanityIndexPage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        tollFreeIndexPage = new TollFreeIndexPage(app.getDriver());
        buyingRegularVanityNumber = new BuyingRegularVanityNumber(app.getDriver());
        vanityCategoryDetail = new VanityCategoryDetail(app.getDriver());
    }


    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void loadingRegularVanityNumbersOnClickLoadMore() {
        homePage.open();
        homePage.clickSubNavItemTollFree("vanity");
        vanityIndexPage.searchTollFreeNumberFromVanityIndexPage("bug");
        vanitySearchResult.checkingClickLoadMore();
    }

    @Test
    public void loadMoreIfAllNumbersIsLoaded() {
        homePage.open();
        homePage.searchTollFreeFromHomePage("bug");
        vanitySearchResult.checkingLoadMoreIfAllNumbersIsLoaded();
    }

    @Test
    public void filterCategoriesOnVanityIndexPage() throws InterruptedException {
        vanityIndexPage.open();
        vanityIndexPage.checkingFilterVanityCatalog("W");
    }


    @Test
    public void buying() {
        homePage.open();
        tollFreeIndexPage.openTollFreeIndexPageFromMainNav();
        tollFreeIndexPage.searchTollFreeNumberFromTollFreeIndexPage("error");
        vanitySearchResult.chooseFirstNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose5000MonthlyMinutes();
        double discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("3 Year");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumber("8001234560");
        buyingRegularVanityNumber.checkingOrderSummary(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void buying2()  {
        vanityIndexPage.open();
        vanityIndexPage.searchTollFreeNumberFromVanityIndexPage("bug");
        vanitySearchResult.chooseLastNumberFromRegularVanityListAfterLoadMore();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose100MonthlyMinutes();
        double discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("Month");
        double priceNumber = buyingRegularVanityNumber.chooseCheckboxMultipleRingToNumber();
        buyingRegularVanityNumber.checkingOrderSummary(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }

    @Test
    public void buying3() {
        homePage.open();
        homePage.searchTollFreeFromHomePage("test");
        vanitySearchResult.choose32thNumberFromRegularVanityList();
        double priceMonthlyMinutes = buyingRegularVanityNumber.choose20000MonthlyMinutes();
        double discountPriceSelectedPlan = buyingRegularVanityNumber.chooseTermLength("1 Year");
        double priceNumber = buyingRegularVanityNumber.enterRingToNumberWithMultipleCheckbox("8332702679");
        buyingRegularVanityNumber.checkingOrderSummary(priceMonthlyMinutes, discountPriceSelectedPlan, priceNumber);
    }




}
