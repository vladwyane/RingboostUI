package tollFreeFlow;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

public class SearchVanityNumber extends TestBase {

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


    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void testSearchEnterSevenDigits() throws InterruptedException {
        homePage.open();
        String request = "9933396";
        homePage.searchTollFreeNumbers(request);
        vanitySearchResult.checkingSearchResultSevenOrEightSymbols(request);
    }

    @Test
    public void testSearchEnterEightSLetters() throws InterruptedException {
        vanityIndexPage.open();
        String request = "Play Batl";
        vanityIndexPage.searchTollFreeNumbers(request);
        vanitySearchResult.checkingSearchResultSevenOrEightSymbols(request.toUpperCase());
    }

    @Test
    public void testSearchEnterSixDigits() throws InterruptedException {
        homePage.open();
        tollFreeIndexPage.openTollFreeIndexPageFromMainNav();
        String request = "994276";
        tollFreeIndexPage.searchTollFreeNumbers(request);
        vanitySearchResult.checkingSearchResultFourAndMoreSymbols(request.toUpperCase());
    }

    @Test
    public void testSearchEnterFiveLettersWithDigits() throws InterruptedException {
        tollFreeIndexPage.open();
        String request = "5FIVE";
        tollFreeIndexPage.searchTollFreeNumbers(request);
        vanitySearchResult.checkingSearchResultFourAndMoreSymbols(request.toUpperCase());
    }

    @Test
    public void testSearchEnterFourLetters() throws InterruptedException {
        vanityCategoryDetail.open();
        String request = "post";
        vanityCategoryDetail.searchTollFreeNumbers(request);
        vanitySearchResult.clickButtonLoadMore();
        vanitySearchResult.checkingSearchResultFourAndMoreSymbolsFromCategoryDetailPage(request.toUpperCase());
    }

    @Test
    public void testSearchEnterThreeDigits() throws InterruptedException {
        vanityIndexPage.open();
        String request = "911";
        vanityIndexPage.searchTollFreeNumbers(request);
        vanitySearchResult.checkingSearchResult3Symbols(request.toUpperCase());
    }

    @Test
    public void testSearchEnterMoreThan14Symbols() throws InterruptedException {
        vanitySearchResult.open();
        String request = "Test More Than 14 Symbols";
        vanitySearchResult.searchTollFreeNumbers(request);
        vanitySearchResult.checkingSearchResultMoreThen14Symbols(request.toUpperCase());
    }

    @Test
    public void testSearchEnterSpecialSymbols() throws InterruptedException {
        homePage.open();
        homePage.searchTollFreeNumbers("");
        String request = "№;#@ров.,";
        vanitySearchResult.searchTollFreeNumbers(request);
        vanitySearchResult.checkingSearchResultSpecialSymbols(request.toUpperCase());
    }

}
