package adminPanel.sprint_3_PricingTollFree;

import data.AreaCodesData;
import org.json.JSONException;
import org.testng.annotations.*;
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
    }

    @BeforeMethod
    public void login() {
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessAddingNewAreaCode() throws InterruptedException, IOException, JSONException {
  //      login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewAreaCode(AreaCodesData.AREA_CODES_ALASKA);
        areaCodesPage.checkingSuccessAddingNewAreaCode(AreaCodesData.AREA_CODES_ALASKA);
    }

    @Test
    public void test1ErrorAddingNewAreaCodeAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
 //       login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.clickSaveButton();
        areaCodesPage.checkingErrorMessagesAddingAreaCodeEmptyFields();
    }

    @Test
    public void test1ErrorAddingNewAreaCodeLessThan200() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewAreaCode(AreaCodesData.AREA_CODES_ALASKA_199);
        areaCodesPage.checkingErrorMessagesAddingAreaCodeLessThan200();
    }

    @Test
    public void test1ErrorAddingNewAreaCodeMoreThan999() throws InterruptedException, IOException, JSONException {
     //   login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewAreaCode(AreaCodesData.AREA_CODES_ALASKA_1000);
        areaCodesPage.checkingErrorMessagesAddingAreaCodeMoreThan999();
    }

    @Test
    public void test2ErrorAddingNewAreaCodeAlreadyUsed() throws InterruptedException, IOException, JSONException {
     //   login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewAreaCode(AreaCodesData.AREA_CODES_ALASKA);
        areaCodesPage.checkingErrorMessagesAddingAreaCodeAlreadyUsed(AreaCodesData.AREA_CODES_ALASKA);
    }

    @Test
    public void test3SuccessAddingNewBundleAreaCodes() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewBundleAreaCodes(AreaCodesData.AREA_CODES_ALASKA);
        areaCodesPage.checkingSuccessAddingNewBundle(AreaCodesData.AREA_CODES_ALASKA);
    }

    @Test
    public void test3SuccessEditAreaCodes() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickEditIcon(AreaCodesData.AREA_CODES_905);
        areaCodesPage.editAreaCode(AreaCodesData.AREA_CODES_ALASKA_UPDATE);
        areaCodesPage.checkingSuccessEditName(AreaCodesData.AREA_CODES_ALASKA_UPDATE);
    }

    @Test
    public void test4ErrorAddingUsedBundleAreaCodes() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickAddAreaCodesButton();
        areaCodesPage.addNewBundleAreaCodes(AreaCodesData.AREA_CODES_ALASKA);
        areaCodesPage.checkingErrorMessagesAddingBundleAlreadyUsed(AreaCodesData.AREA_CODES_ALASKA);
    }

    @Test
    public void test4ErrorEditBundleWithUsedAreaCodes() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickEditIcon(AreaCodesData.AREA_CODES_ALASKA);
        areaCodesPage.editBundle(AreaCodesData.AREA_CODES_ALASKA);
        areaCodesPage.checkingErrorMessagesAddingAreaCodeAlreadyUsed(AreaCodesData.AREA_CODES_ALASKA);
    }

    @Test
    public void test5SuccessDeleteBundle() throws InterruptedException, IOException, JSONException {
        //  login.open();
        admin.clickAreaCodesLink();
        areaCodesPage.chooseState("Alaska");
        areaCodesPage.clickDeleteIcon(AreaCodesData.AREA_CODES_ALASKA);
        areaCodesPage.clickDeleteIcon(AreaCodesData.AREA_CODES_ALASKA_UPDATE);
        areaCodesPage.checkingSuccessDeleted(AreaCodesData.AREA_CODES_ALASKA_UPDATE);
    }
}
