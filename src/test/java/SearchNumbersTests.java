import org.testng.annotations.*;
import pages.HomePage;
import pages.VanityIndexPage;
import pages.VanitySearchResult;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 3/14/2019.
 */
public class SearchNumbersTests extends TestBase{

    private HomePage homePage;
    private VanityIndexPage vanityIndexPage;
    private VanitySearchResult vanitySearchResult;

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        vanityIndexPage = new VanityIndexPage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
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
    public void filterCategoriesOnVanityIndexPage() throws InterruptedException, IOException {
        vanityIndexPage.open();
        vanityIndexPage.checkingFilterVanityCatalog("W");
    }



}
