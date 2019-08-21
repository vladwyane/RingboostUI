package adminPanel;

import data.Carriers;
import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 8/20/2019.
 */
public class Sprint_2_Carriers extends TestBase {

    private Login login;
    private Admin admin;
    private CarriersListingPage carriersListingPage;

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        carriersListingPage = new CarriersListingPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewCarrier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickCarriersLink();
        carriersListingPage.clickAddCarrierButton();
        carriersListingPage.createNewCarrier(Carriers.VODAFONE);
        carriersListingPage.checkingSuccessAlertMessage();
    }

    @Test
    public void test2ErrorCreateNewCarrierAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickCarriersLink();
        carriersListingPage.clickAddCarrierButton();
        carriersListingPage.clickSaveButton();
        carriersListingPage.checkingErrorMessages();
    }

    @Test
    public void test3SearchField() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickCarriersLink();
        carriersListingPage.clickAddCarrierButton();
        carriersListingPage.createNewCarrier(Carriers.ALPHABET);
        carriersListingPage.searchCarrier(Carriers.ALPHABET);
        carriersListingPage.checkingTitleFirstCarrier(Carriers.ALPHABET);
    }

    @Test
    public void test4SErrorMessageNotVisibleWhenCreateNewCarrier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickCarriersLink();
        carriersListingPage.clickAddCarrierButton();
        carriersListingPage.clickSaveButton();
        carriersListingPage.clickCancelButton();
        carriersListingPage.clickAddCarrierButton();
        carriersListingPage.checkingErrorMessagesIsAbsent();
    }

    @Test
    public void test5EditButton() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickCarriersLink();
        carriersListingPage.searchCarrier(Carriers.ALPHABET);
        carriersListingPage.clickEditIconFirstCarriers();
        carriersListingPage.editCarrierInfo(Carriers.ABC);
        carriersListingPage.searchCarrier(Carriers.ABC);
        carriersListingPage.checkingTitleFirstCarrier(Carriers.ABC);
    }

    @Test
    public void test6ErrorEditWithSameCarriersName() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickCarriersLink();
        carriersListingPage.clickAddCarrierButton();
        carriersListingPage.editCarrierInfo(Carriers.VODAFONE);
        carriersListingPage.checkingErrorMessagesTitleHasBeenUsed();
    }

    @Test
    public void test7SortingByMRCColumn() throws InterruptedException, IOException, JSONException {
        carriersListingPage.open();
        carriersListingPage.clickColumnheaderOfTable("Per-Minute Usage");
        carriersListingPage.clickColumnheaderOfTable("Per-Minute Usage");
        carriersListingPage.checkingCorrectSorting("Per-Minute Usage");
    }

    @Test
    public void test8SDeleteButton() throws InterruptedException, IOException, JSONException {
        carriersListingPage.open();
        carriersListingPage.searchCarrier(Carriers.ABC);
        carriersListingPage.clickDeleteIconFirstCarriers();
        carriersListingPage.checkingSuccessDeleted();
    }

}
