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
    public void test2SearchEnterEightSLetters() throws InterruptedException {
        localIndexPage.open();
        String request = "AbcJklJA";
        localIndexPage.searchLocalNumbers(request);
        localSearchResult.checkingSearchResultEightLetters(request);
    }

    @Test
    public void test3SearchEnterSixDigits() throws InterruptedException {
        localStateDetail.open();
        String request = "994276";
        localStateDetail.searchLocalNumbers(request);
        localSearchResult.checkingSearchResultSixSymbols(request);
    }

    @Test
    public void test4SearchEnterFiveLettersWithDigits() throws InterruptedException {
        homePage.open();
        String request = "5FIVE";
        homePage.searchLocalNumbers(request);
        localSearchResult.checkingSearchResultFiveSymbolsSymbols(request);
    }

    @Test
    public void test5SearchEnterFourLetters() throws InterruptedException {
        localSearchResult.open();
        String request = "post";
        localSearchResult.searchLocalNumbers(request);
        localSearchResult.clickButtonLoadMore();
        localSearchResult.checkingSearchResultFourSymbolsSymbols(request);
    }

    @Test
    public void test6SearchEnterThreeDigits() throws InterruptedException {
        localIndexPage.open();
        String request = "911";
        localIndexPage.searchLocalNumbers(request);
        localSearchResult.checkingSearchResultThreeSymbolsSymbols(request);
    }

    @Test
    public void test7SearchEnterSpecialSymbols() throws InterruptedException {
        localSearchResult.open();
        String request = "№;#@ров.,";
        localSearchResult.searchLocalNumbers(request);
        localSearchResult.checkingSearchResultSpecialSymbols(request);
    }

    @Test
    public void test8LoadMoreInSearchResult() {
        homePage.open();
        homePage.searchLocalNumbers("");
        localSearchResult.checkingLoadMore();
    }

}
