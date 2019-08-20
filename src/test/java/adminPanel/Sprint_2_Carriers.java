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
    public void test1CreateNewCarrier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickCarriersLink();
        carriersListingPage.clickAddCarrierButton();
        carriersListingPage.createNewCarrier(Carriers.VODAFONE);
        carriersListingPage.checkingSuccessAlertMessage();
    }

}
