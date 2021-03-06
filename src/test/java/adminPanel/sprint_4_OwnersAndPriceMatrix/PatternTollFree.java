package adminPanel.sprint_4_OwnersAndPriceMatrix;

import data.PatternsData;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.Login;
import pages.admin.PatternsTollFreePage;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 11/1/2019.
 */
public class PatternTollFree extends TestBase {

    private Login login;
    private PatternsTollFreePage patternsTollFreePage;

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        patternsTollFreePage = new PatternsTollFreePage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewPattern() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickAddNewPatternButton();
        patternsTollFreePage.createNewPattern(PatternsData.RED_BULL_MUSIC);
        patternsTollFreePage.checkingSuccessAlertMessage();
    }

    @Test
    public void test2ErrorCreateNewPatternAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickAddNewPatternButton();
        patternsTollFreePage.clickAddPatternButton();
        patternsTollFreePage.checkingErrorMessagesAllFieldsIsEmpty();
    }

    @Test
    public void test2ErrorCreateNewPatternSameName() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickAddNewPatternButton();
        patternsTollFreePage.createNewPattern(PatternsData.RED_BULL_ACCIDENT);
        patternsTollFreePage.checkingErrorMessagesNameHasBeenUsed();
    }

    @Test
    public void test2ErrorCreateNewPatternOnlyName() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickAddNewPatternButton();
        patternsTollFreePage.fillNewPatternFormOnlyName(PatternsData.RED_BULL_ACCIDENT);
        patternsTollFreePage.clickAddPatternButton();
        patternsTollFreePage.checkingErrorMessagesCategoryFieldIsRequired();
    }

    @Test
    public void test2SuccessCreateNewPatternSameNameButDifferentType() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickAddNewPatternButton();
        patternsTollFreePage.createNewPattern(PatternsData.RED_BULL_EASY_DIAL);
        patternsTollFreePage.checkingSuccessAlertMessage();
    }

    @Test
    public void test3SearchField() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.searchPattern(PatternsData.RED_BULL_MUSIC.getNamePatterns());
        patternsTollFreePage.checkingNameFirstPattern(PatternsData.RED_BULL_MUSIC);
    }

    @Test
    public void test4ErrorMessageNotVisibleWhenCreateNewPattern() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickAddNewPatternButton();
        patternsTollFreePage.clickAddPatternButton();
        patternsTollFreePage.clickCancelButton();
        patternsTollFreePage.clickAddNewPatternButton();
        patternsTollFreePage.checkingErrorMessagesIsAbsent();
    }

    @Test
    public void test4CorrectSortingPatternsColumn() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickColumnHeaderOfTable("Pattern");
        String valueBeforeSorting = patternsTollFreePage.getFistTdByColumn("Pattern");
        patternsTollFreePage.clickColumnHeaderOfTable("Pattern");
        String valueAfterSorting = patternsTollFreePage.getFistTdByColumn("Pattern");
        patternsTollFreePage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingCategoryColumn() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickColumnHeaderOfTable("Category");
        String valueBeforeSorting = patternsTollFreePage.getFistTdByColumn("Category");
        patternsTollFreePage.clickColumnHeaderOfTable("Category");
        String valueAfterSorting = patternsTollFreePage.getFistTdByColumn("Category");
        patternsTollFreePage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingCategoryTypeColumn() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickColumnHeaderOfTable("Category type");
        String valueBeforeSorting = patternsTollFreePage.getFistTdByColumn("Category type");
        patternsTollFreePage.clickColumnHeaderOfTable("Category type");
        String valueAfterSorting = patternsTollFreePage.getFistTdByColumn("Category type");
        patternsTollFreePage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Ignore
    @Test
    public void test4CorrectSortingTypeColumn() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickColumnHeaderOfTable("Type");
        String valueBeforeSorting = patternsTollFreePage.getFistTdByColumn("Type");
        patternsTollFreePage.clickColumnHeaderOfTable("Type");
        String valueAfterSorting = patternsTollFreePage.getFistTdByColumn("Type");
        patternsTollFreePage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingUpdateColumn() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.clickColumnHeaderOfTable("Updated at");
        String valueBeforeSorting = patternsTollFreePage.getFistTdByColumn("Updated at");
        patternsTollFreePage.clickColumnHeaderOfTable("Updated at");
        String valueAfterSorting = patternsTollFreePage.getFistTdByColumn("Updated at");
        patternsTollFreePage.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test5SDeleteButton() throws InterruptedException, IOException, JSONException {
        patternsTollFreePage.open();
        patternsTollFreePage.searchPattern(PatternsData.RED_BULL_MUSIC.getNamePatterns());
        patternsTollFreePage.clickDeleteIconFirstPattern();
        patternsTollFreePage.clickDeleteIconFirstPattern();
        patternsTollFreePage.checkingSuccessDeleted();
    }
}
