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
        priceMatrixPage.createNewLocalTier("V", "1000", "99.9", "11", false);
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
    public void test1AbsentSelectCategoryInNewLocalTierPopup() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickAddPhoneTierButton();
        priceMatrixPage.checkingAbsentCategorySelect();
    }

    @Test
    public void test2ErrorCreatingNewLocalTierSameName() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickAddPhoneTierButton();
        priceMatrixPage.createNewLocalTier("V", "1000", "99.9", "11", false);
        priceMatrixPage.checkingErrorMessagesLevelAlreadyUsed();
    }

    @Test
    public void test3EditLocalTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickEditIcon("V");
        priceMatrixPage.editTier("Vlad", true);
        priceMatrixPage.checkingSuccessEditNewTier("Vlad");
    }

    @Test
    public void test4PresentSelectCategoryInEditTierPopup() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickEditIcon("Vlad");
        priceMatrixPage.checkingPresentCategorySelect();
    }

    @Test
    public void test5DeleteLocalTier() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickPriceMatrixLink();
        priceMatrixPage.clickDeleteIcon("Vlad");
        priceMatrixPage.checkingSuccessDeleted();
    }
}
