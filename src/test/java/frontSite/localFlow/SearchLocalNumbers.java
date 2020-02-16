package frontSite.localFlow;

import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.front.*;
import testBase.TestBase;

public class SearchLocalNumbers extends TestBase {

    private HomePage homePage;
    private LocalIndexPage localIndexPage;
    private LocalStateDetail localStateDetail;
    private LocalSearchResult localSearchResult;

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        localStateDetail = new LocalStateDetail(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
    }


    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test(description = "Search Enter Seven Digits")
    @Story("Search Enter Seven Digits")
    public void test1SearchEnterSevenDigits() throws InterruptedException {
        homePage.open();
        String request = "9009000";
        homePage.searchLocalNumbers(request);
        localSearchResult.checkingSearchResultSevenSymbols(request);
    }

   @Test
    public void testSearchEnterEightSLetters() throws InterruptedException {
        localIndexPage.open();
        String request = "AbcJklJA";
        localIndexPage.searchLocalNumbers(request);
        localSearchResult.checkingSearchResultEightLetters(request);
    }
/*
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
        String request = "TestBallet More Than 14 Symbols";
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

    @Test
    public void testLoadMoreInSearchResult() {
        homePage.open();
        homePage.searchTollFreeNumbers("");
        vanitySearchResult.checkingLoadMore();
    }
*/
}
