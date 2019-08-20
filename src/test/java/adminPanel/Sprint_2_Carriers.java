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
        carriersListingPage.clickSaveButton();
        carriersListingPage.checkingErrorMessages();
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

}
