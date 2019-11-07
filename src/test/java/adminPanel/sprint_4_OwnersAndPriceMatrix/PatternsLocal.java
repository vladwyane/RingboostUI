package adminPanel.sprint_4_OwnersAndPriceMatrix;

import data.PatternsData;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.Login;
import pages.admin.PatternsLocalPage;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/31/2019.
 */
public class PatternsLocal extends TestBase{

    private Login login;
    private PatternsLocalPage patternsLocalPage;


    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        patternsLocalPage = new PatternsLocalPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewPattern() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickAddNewPatternButton();
        patternsLocalPage.createNewPattern(PatternsData.QUALITY);
        patternsLocalPage.checkingSuccessAlertMessage();
    }

    @Test
    public void test2ErrorCreateNewPatternAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickAddNewPatternButton();
        patternsLocalPage.clickAddPatternButton();
        patternsLocalPage.checkingErrorMessagesAllFieldsIsEmpty();
    }

    @Test
    public void test2ErrorCreateNewPatternSameName() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickAddNewPatternButton();
        patternsLocalPage.createNewPattern(PatternsData.QUALITY);
        patternsLocalPage.checkingErrorMessagesNameHasBeenUsed();
    }

    @Test
    public void test2ErrorCreateNewPatternOnlyName() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickAddNewPatternButton();
        patternsLocalPage.fillNewPatternFormOnlyName(PatternsData.RED_BULL_ACCIDENT);
        patternsLocalPage.clickAddPatternButton();
        patternsLocalPage.checkingErrorMessagesCategoryFieldIsRequired();
    }

    @Test
    public void test3SearchField() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.searchPattern(PatternsData.QUALITY.getNamePatterns());
        patternsLocalPage.checkingNameFirstPattern(PatternsData.QUALITY);
    }

    @Test
    public void test4ErrorMessageNotVisibleWhenCreateNewPattern() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickAddNewPatternButton();
        patternsLocalPage.clickAddPatternButton();
        patternsLocalPage.clickCancelButton();
        patternsLocalPage.clickAddNewPatternButton();
        patternsLocalPage.checkingErrorMessagesIsAbsent();
    }

    @Test
    public void test4CorrectSortingPatternsColumn() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickColumnHeaderOfTable("Pattern");
        String valueBeforeSorting = patternsLocalPage.getFistTdByColumn("Pattern");
        patternsLocalPage.clickColumnHeaderOfTable("Pattern");
        String valueAfterSorting = patternsLocalPage.getFistTdByColumn("Pattern");
        patternsLocalPage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingCategoryColumn() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickColumnHeaderOfTable("Category");
        String valueBeforeSorting = patternsLocalPage.getFistTdByColumn("Category");
        patternsLocalPage.clickColumnHeaderOfTable("Category");
        String valueAfterSorting = patternsLocalPage.getFistTdByColumn("Category");
        patternsLocalPage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingTypeColumn() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickColumnHeaderOfTable("Type");
        String valueBeforeSorting = patternsLocalPage.getFistTdByColumn("Type");
        patternsLocalPage.clickColumnHeaderOfTable("Type");
        String valueAfterSorting = patternsLocalPage.getFistTdByColumn("Type");
        patternsLocalPage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingUpdateColumn() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.clickColumnHeaderOfTable("Updated at");
        String valueBeforeSorting = patternsLocalPage.getFistTdByColumn("Updated at");
        patternsLocalPage.clickColumnHeaderOfTable("Updated at");
        String valueAfterSorting = patternsLocalPage.getFistTdByColumn("Updated at");
        patternsLocalPage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test5SDeleteButton() throws InterruptedException, IOException, JSONException {
        patternsLocalPage.open();
        patternsLocalPage.searchPattern(PatternsData.QUALITY.getNamePatterns());
        patternsLocalPage.clickDeleteIconFirstPattern();
        patternsLocalPage.checkingSuccessDeleted();
    }
}
