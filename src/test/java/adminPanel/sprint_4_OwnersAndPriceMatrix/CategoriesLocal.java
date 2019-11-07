package adminPanel.sprint_4_OwnersAndPriceMatrix;

import data.CategoriesData;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/31/2019.
 */
public class CategoriesLocal extends TestBase {

    private Login login;
    private CategoryLocal categoryLocal;


    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        categoryLocal = new CategoryLocal(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewCategory() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.clickAddNewCategoryButton();
        categoryLocal.createNewCategory(CategoriesData.QUALITY);
        categoryLocal.checkingSuccessAlertMessage();
    }

    @Test
    public void test2ErrorCreateNewCategoryAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.clickAddNewCategoryButton();
        categoryLocal.clickAddCategoryButton();
        categoryLocal.checkingErrorMessagesAllFieldsIsEmpty();
    }

    @Test
    public void test2ErrorCreateNewCategorySameName() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.clickAddNewCategoryButton();
        categoryLocal.createNewCategory(CategoriesData.QUALITY);
        categoryLocal.checkingErrorMessagesNameHasBeenUsed();
    }

    @Test
    public void test3SearchField() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.searchCategory(CategoriesData.QUALITY.getName());
        categoryLocal.checkingNameFirstCategory(CategoriesData.QUALITY);
    }

    @Test
    public void test4ErrorMessageNotVisibleWhenCreateNewOwner() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.clickAddNewCategoryButton();
        categoryLocal.clickAddCategoryButton();
        categoryLocal.clickCancelButton();
        categoryLocal.clickAddNewCategoryButton();
        categoryLocal.checkingErrorMessagesIsAbsent();
    }

    @Test
    public void test4CorrectSortingNameColumn() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.clickColumnHeaderOfTable("Name");
        String valueBeforeSorting = categoryLocal.getFistTdByColumn("Name");
        categoryLocal.clickColumnHeaderOfTable("Name");
        String valueAfterSorting = categoryLocal.getFistTdByColumn("Name");
        categoryLocal.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingTierColumn() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.clickColumnHeaderOfTable("Tier");
        String valueBeforeSorting = categoryLocal.getFistTdByColumn("Tier");
        categoryLocal.clickColumnHeaderOfTable("Tier");
        String valueAfterSorting = categoryLocal.getFistTdByColumn("Tier");
        categoryLocal.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingOrderColumn() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.clickColumnHeaderOfTable("Order");
        String valueBeforeSorting = categoryLocal.getFistTdByColumn("Order");
        categoryLocal.clickColumnHeaderOfTable("Order");
        String valueAfterSorting = categoryLocal.getFistTdByColumn("Order");
        categoryLocal.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingUpdateColumn() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.clickColumnHeaderOfTable("Updated at");
        String valueBeforeSorting = categoryLocal.getFistTdByColumn("Updated at");
        categoryLocal.clickColumnHeaderOfTable("Updated at");
        String valueAfterSorting = categoryLocal.getFistTdByColumn("Updated at");
        categoryLocal.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test5EditCategory() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.searchCategory(CategoriesData.QUALITY.getName());
        categoryLocal.clickEditIconFirstCategory();
        categoryLocal.editCategory(CategoriesData.UPDATE_QUALITY);
        categoryLocal.searchCategory(CategoriesData.UPDATE_QUALITY.getName());
        categoryLocal.checkingNameFirstCategory(CategoriesData.UPDATE_QUALITY);
    }

    @Test
    public void test6SDeleteButton() throws InterruptedException, IOException, JSONException {
        categoryLocal.open();
        categoryLocal.searchCategory(CategoriesData.UPDATE_QUALITY.getName());
        categoryLocal.clickDeleteIconFirstCategory();
        categoryLocal.checkingSuccessDeleted();
    }
}
