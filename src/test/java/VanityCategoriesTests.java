import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

public class VanityCategoriesTests extends TestBase {

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
    public void testFilterCategoriesOnVanityIndexPage() throws InterruptedException {
        vanityIndexPage.open();
        vanityIndexPage.checkingFilterVanityCatalog("W");
    }

    @Test
    public void testSelectedCategoryFromCategoryIndexPage() {
        vanityIndexPage.open();
        String nameCategory = "Banking";
        vanityIndexPage.chooseVanityCategory(nameCategory);
        vanityCategoryDetail.checkingSelectedCategoryFromCategoryIndexPage(nameCategory);
    }

    @Test
    public void testCategoriesDetailPageDefaultState() {
        vanityCategoryDetail.open();
        vanityCategoryDetail.checkingDefaultState();
    }

    @Test
    public void testButtonsClearAllFilters() {
        vanityIndexPage.open();
        String nameCategory = "Quality Assurance";
        vanityIndexPage.chooseVanityCategory(nameCategory);
        vanityCategoryDetail.clickButtonClearAllFilters();
        vanityCategoryDetail.checkingDefaultState();
    }

    @Test
    public void testFilterNumbersByCategories() {
        vanityIndexPage.open();
        String nameCategoryInSelect = "VOIP";
        String firstNumberInListBefore = vanityIndexPage.chooseVanityCategory("Banking");
        String firstNumberInListAfter = vanityCategoryDetail.chooseCategoryInSelect(nameCategoryInSelect);
        vanityCategoryDetail.checkingSelectCategories(firstNumberInListBefore, firstNumberInListAfter, nameCategoryInSelect);
    }

    @Test
    public void testFilterNumbersByPrefix() {
        vanityCategoryDetail.open();
        String prefix = "866";
        String firstNumberInListBefore = vanityCategoryDetail.choosePrefixInSelect(prefix);
        vanityCategoryDetail.checkingSelectPrefix(firstNumberInListBefore, prefix);
    }

    @Test
    public void testLoadMoreOnCategoriesDetailPage() {
        vanityCategoryDetail.open();
        vanityCategoryDetail.checkingLoadMore();
    }
}
