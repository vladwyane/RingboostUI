package adminPanel.sprint_4_OwnersAndPriceMatrix;

import data.CategoriesData;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.CategoryTollFree;
import pages.admin.Login;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/31/2019.
 */
public class CategoriesTollFree extends TestBase {

    private Login login;
    private CategoryTollFree categoryTollFree;


    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        categoryTollFree = new CategoryTollFree(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewCategory() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.clickAddNewCategoryButton();
        categoryTollFree.createNewCategory(CategoriesData.QUALITY);
        categoryTollFree.checkingSuccessAlertMessage();
    }

    @Test
    public void test2ErrorCreateNewCategoryAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.clickAddNewCategoryButton();
        categoryTollFree.clickAddCategoryButton();
        categoryTollFree.checkingErrorMessagesAllFieldsIsEmpty();
    }

    @Test
    public void test2ErrorCreateNewCategorySameName() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.clickAddNewCategoryButton();
        categoryTollFree.createNewCategory(CategoriesData.QUALITY);
        categoryTollFree.checkingErrorMessagesNameHasBeenUsed();
    }

    @Test
    public void test3SearchField() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.searchCategory(CategoriesData.QUALITY.getName());
        categoryTollFree.checkingNameFirstCategory(CategoriesData.QUALITY);
    }

    @Test
    public void test4ErrorMessageNotVisibleWhenCreateNewOwner() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.clickAddNewCategoryButton();
        categoryTollFree.clickAddCategoryButton();
        categoryTollFree.clickCancelButton();
        categoryTollFree.clickAddNewCategoryButton();
        categoryTollFree.checkingErrorMessagesIsAbsent();
    }

    @Test
    public void test4CorrectSortingNameColumn() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.clickColumnHeaderOfTable("Name");
        String valueBeforeSorting = categoryTollFree.getFistTdByColumn("Name");
        categoryTollFree.clickColumnHeaderOfTable("Name");
        String valueAfterSorting = categoryTollFree.getFistTdByColumn("Name");
        categoryTollFree.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingTypeColumn() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.clickColumnHeaderOfTable("Type");
        String valueBeforeSorting = categoryTollFree.getFistTdByColumn("Type");
        categoryTollFree.clickColumnHeaderOfTable("Type");
        String valueAfterSorting = categoryTollFree.getFistTdByColumn("Type");
        categoryTollFree.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingOrderColumn() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.clickColumnHeaderOfTable("Order");
        String valueBeforeSorting = categoryTollFree.getFistTdByColumn("Order");
        categoryTollFree.clickColumnHeaderOfTable("Order");
        String valueAfterSorting = categoryTollFree.getFistTdByColumn("Order");
        categoryTollFree.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test4CorrectSortingUpdateColumn() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.clickColumnHeaderOfTable("Updated at");
        String valueBeforeSorting = categoryTollFree.getFistTdByColumn("Updated at");
        categoryTollFree.clickColumnHeaderOfTable("Updated at");
        String valueAfterSorting = categoryTollFree.getFistTdByColumn("Updated at");
        categoryTollFree.checkingSuccessSorting(valueBeforeSorting, valueAfterSorting);
    }

    @Test
    public void test5EditCategory() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.searchCategory(CategoriesData.QUALITY.getName());
        categoryTollFree.clickEditIconFirstCategory();
        categoryTollFree.editCategory(CategoriesData.UPDATE_QUALITY);
        categoryTollFree.searchCategory(CategoriesData.UPDATE_QUALITY.getName());
        categoryTollFree.checkingNameFirstCategory(CategoriesData.UPDATE_QUALITY);
    }

    @Test
    public void test6SDeleteButton() throws InterruptedException, IOException, JSONException {
        categoryTollFree.open();
        categoryTollFree.searchCategory(CategoriesData.UPDATE_QUALITY.getName());
        categoryTollFree.clickDeleteIconFirstCategory();
        categoryTollFree.checkingSuccessDeleted();
    }
}
