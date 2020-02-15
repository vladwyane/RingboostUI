package frontSite.localFlow;

import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.front.HomePage;
import pages.front.LocalIndexPage;
import pages.front.LocalStateDetail;
import testBase.TestBase;

/**
 * Created by bigdrop on 7/12/2019.
 */
public class LocalStatesTests extends TestBase {

    private HomePage homePage;
    private LocalIndexPage localIndexPage;
    private LocalStateDetail localStateDetail;

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        localStateDetail = new LocalStateDetail(app.getDriver());
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test(description = "Filter Categories On Vanity Index Page")
    @Story("Filter Categories On Vanity Index Page")
    public void test1FilterCategoriesOnVanityIndexPage() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.checkingFilterLocalStates("W");
    }

    @Test(description = "Selected States From Local Index Page")
    @Story("Selected States From Local Index Page")
    public void test2SelectedStatesFromLocalIndexPage() {
        localIndexPage.open();
        String nameState = "Alabama";
        localIndexPage.chooseLocalState(nameState);
        localStateDetail.checkingSelectedStateFromStateIndexPage(nameState);
    }

    @Test(description = "Local State Detail Page Default State")
    @Story("Local State Detail Page Default State")
    public void test3LocalStateDetailPageDefaultState() {
        localStateDetail.open();
        localStateDetail.checkingDefaultState();
    }

    @Test(description = "Local State Detail Filter By Area Code")
    @Story("Local State Detail Filter By Area Code")
    public void test4LocalStateDetailFilterByAreaCode() {
        localIndexPage.open();
        String nameState = "Texas";
        localIndexPage.chooseLocalState(nameState);
        String areaCode = "254";
        localStateDetail.chooseAreaCodeInSelect(areaCode);
        localStateDetail.checkingCorrectFilterByState(nameState, areaCode);
    }

    @Test(description = "Local State Detail Filter By Patterns")
    @Story("Local State Detail Filter By Patterns")
    public void test5LocalStateDetailFilterByPatterns() {
        localStateDetail.open();
        String pattern = "xxxx";
        localStateDetail.choosePatternInSelect(pattern);
        localStateDetail.checkingCorrectFilterByPattern(pattern);
    }

    @Test(description = "Load More Is Absent After Filtration")
    @Story("Load More Is Absent After Filtration")
    public void test6LoadMoreIsAbsentAfterFiltration() {
        localIndexPage.open();
        String nameState = "Indiana";
        localIndexPage.chooseLocalState(nameState);
        String areaCode = "574";
        localStateDetail.chooseAreaCodeInSelect(areaCode);
        String pattern = "000X";
        localStateDetail.choosePatternInSelect(pattern);
        localStateDetail.checkingLoadMoreIsAbsentAfterFiltration(nameState, areaCode, pattern);
    }

    @Test(description = "Load More on Local Detail Page")
    @Story("Load More on Local Detail Page")
    public void test7LoadMoreOnLocalDetailPage() {
        localStateDetail.open();
        localStateDetail.checkingLoadMore();
    }

    @Test(description = "Load More on Local Detail Page")
    @Story("Load More on Local Detail Page")
    public void test8SortBySelect() {
        localStateDetail.open();
        String nameState = "Alaska";
        localStateDetail.chooseStateInSelect(nameState);
        localStateDetail.chooseSortByInSelect("Price: High - Low");
        localStateDetail.checkingCorrectSortBy(nameState);
    }


}
