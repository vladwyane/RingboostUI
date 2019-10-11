package localFlow;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.*;
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

    @Test
    public void testFilterCategoriesOnVanityIndexPage() throws InterruptedException {
        localIndexPage.open();
        localIndexPage.checkingFilterLocalStates("W");
    }

    @Test
    public void testSelectedStatesFromLocalIndexPage() {
        localIndexPage.open();
        String nameState = "Alabama";
        localIndexPage.chooseLocalState(nameState);
        localStateDetail.checkingSelectedStateFromStateIndexPage(nameState);
    }

    @Test
    public void testLocalStateDetailPageDefaultState() {
        localStateDetail.open();
        localStateDetail.checkingDefaultState();
    }


}
