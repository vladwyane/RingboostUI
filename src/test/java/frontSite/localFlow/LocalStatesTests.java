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
    public void testFilterCategoriesOnVanityIndexPage() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.checkingFilterLocalStates("W");
    }

    @Test(description = "Selected States From Local Index Page")
    @Story("Selected States From Local Index Page")
    public void testSelectedStatesFromLocalIndexPage() {
        localIndexPage.open();
        String nameState = "Alabama";
        localIndexPage.chooseLocalState(nameState);
        localStateDetail.checkingSelectedStateFromStateIndexPage(nameState);
    }

    @Test(description = "Local State Detail Page Default State")
    @Story("Local State Detail Page Default State")
    public void testLocalStateDetailPageDefaultState() {
        localStateDetail.open();
        localStateDetail.checkingDefaultState();
    }


}
