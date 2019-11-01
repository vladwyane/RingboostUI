package adminPanel.sprint_4_OwnersAndPriceMatrix;

import data.OwnersData;
import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/21/2019.
 */
public class PriceMatrix extends TestBase {

    private Login login;
    private Admin admin;
    private PriceMatrixPage priceMatrixPage;

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        priceMatrixPage = new PriceMatrixPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewLocalTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickAddPhoneTierButton();
        priceMatrixPage.createNewLocalTier("V", "1", "99.9", true);
        priceMatrixPage.checkingSuccessAlertMessage();
    }

    @Test
    public void test1ErrorCreatingNewLocalTierAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickAddPhoneTierButton();
        priceMatrixPage.clickSaveButton();
        priceMatrixPage.checkingErrorMessagesAllFieldsIsEmpty();
    }

    @Test
    public void test2ErrorCreatingNewLocalTierSameLevel() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickAddPhoneTierButton();
        priceMatrixPage.createNewLocalTier("V", "1", "99.9", false);
        priceMatrixPage.checkingErrorMessagesLevelAlreadyUsed();
    }

    @Test
    public void test3EditLocalTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickEditIcon("V", "1");
        priceMatrixPage.editTier("P", "2");
        priceMatrixPage.checkingSuccessEditNewTier("P", "2");
    }

    @Test
    public void test4CheckingCallForPriceYesForLocalTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickTierOpenClose("P");
        priceMatrixPage.checkingSuccessChangingCallForPrice("yes");
    }

    @Test
    public void test5DeleteLocalTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickDeleteIcon("P", "2");
        priceMatrixPage.checkingSuccessDeleted();
    }
}
