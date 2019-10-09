package adminPanel.sprint_3_PricingTollFree;

import data.AreaCodesData;
import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.admin.Admin;
import pages.admin.AreaCodesPage;
import pages.admin.Login;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/7/2019.
 */
public class AreaCodes extends TestBase {

    private Login login;
    private Admin admin;
    private AreaCodesPage areaCodesPage;


    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        areaCodesPage = new AreaCodesPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessAddingNewAreaCode() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewAreaCode(AreaCodesData.AREA_CODES_FLORIDA);
        areaCodesPage.checkingSuccessAddingNewAreaCode(AreaCodesData.AREA_CODES_FLORIDA);
    }

    @Test
    public void test1ErrorAddingNewAreaCodeAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.clickSaveButton();
        areaCodesPage.checkingErrorMessagesAddingAreaCodeEmptyFields();
    }

    @Test
    public void test1ErrorAddingNewAreaCodeLessThan200() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewAreaCode(AreaCodesData.AREA_CODES_FLORIDA_199);
        areaCodesPage.checkingErrorMessagesAddingAreaCodeLessThan200();
    }

    @Test
    public void test1ErrorAddingNewAreaCodeMoreThan999() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewAreaCode(AreaCodesData.AREA_CODES_FLORIDA_1000);
        areaCodesPage.checkingErrorMessagesAddingAreaCodeMoreThan999();
    }

    @Test
    public void test2ErrorAddingNewAreaCodeAlreadyUsed() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewAreaCode(AreaCodesData.AREA_CODES_FLORIDA);
        areaCodesPage.checkingErrorMessagesAddingAreaCodeAlreadyUsed(AreaCodesData.AREA_CODES_FLORIDA);
    }

    @Test
    public void test3SuccessAddingNewBundleAreaCodes() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewBundleAreaCodes(AreaCodesData.AREA_CODES_FLORIDA);
        areaCodesPage.checkingSuccessAddingNewBundle(AreaCodesData.AREA_CODES_FLORIDA);
    }

    @Test
    public void test3SuccessEditAreaCodes() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickEditIcon(AreaCodesData.AREA_CODES_239);
        areaCodesPage.editAreaCode(AreaCodesData.AREA_CODES_FLORIDA_UPDATE);
        areaCodesPage.checkingSuccessEditName(AreaCodesData.AREA_CODES_FLORIDA_UPDATE);
    }

    @Test
    public void test4ErrorAddingUsedBundleAreaCodes() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewBundleAreaCodes(AreaCodesData.AREA_CODES_FLORIDA);
        areaCodesPage.checkingErrorMessagesAddingBundleAlreadyUsed(AreaCodesData.AREA_CODES_FLORIDA);
    }

    @Test
    public void test4ErrorEditBundleWithUsedAreaCodes() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickEditIcon(AreaCodesData.AREA_CODES_FLORIDA);
        areaCodesPage.editBundle(AreaCodesData.AREA_CODES_FLORIDA);
        areaCodesPage.checkingErrorMessagesAddingAreaCodeAlreadyUsed(AreaCodesData.AREA_CODES_FLORIDA);
    }

    @Test
    public void test5SuccessDeleteBundle() throws InterruptedException, IOException, JSONException {
        areaCodesPage.open();
        areaCodesPage.chooseState("Florida");
        areaCodesPage.clickDeleteIcon(AreaCodesData.AREA_CODES_FLORIDA);
        areaCodesPage.clickDeleteIcon(AreaCodesData.AREA_CODES_FLORIDA_UPDATE);
        areaCodesPage.checkingSuccessDeleted(AreaCodesData.AREA_CODES_FLORIDA_UPDATE);
    }


}
