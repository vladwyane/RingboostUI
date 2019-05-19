import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testBase.TestBase;

import java.io.IOException;

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


    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
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
        vanityCategoryDetail.checkingCategoriesDetailPageDefaultState();
    }

    @Test
    public void testButtonsClearAllFilters() {
        vanityIndexPage.open();
        String nameCategory = "Accident";
        vanityIndexPage.chooseVanityCategory(nameCategory);
        vanityCategoryDetail.clickButtonClearAllFilters();
        vanityCategoryDetail.checkingCategoriesDetailPageDefaultState();
    }
}
