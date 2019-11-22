package adminPanel.sprint_5_CustomersOrder;

import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 11/18/2019.
 */
public class Customers extends TestBase {


    private Login login;
    private Admin admin;
    private CustomerDetailPage customerDetailPage;
    private CustomersListPage customersListPage;

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        customerDetailPage = new CustomerDetailPage(app.getDriver());
        customersListPage = new CustomersListPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewCustomer() throws InterruptedException, IOException, JSONException {
        admin.clickCustomersLink();
        customersListPage.clickAddNewCustomer();
        customersListPage.createNewCustomer(Users.CUSTOMER);
        customersListPage.clickSaveButton();
        customersListPage.checkingSuccessAlertMessage();
    }

     @Test
    public void test2ErrorCreateNewPatternAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
         admin.clickCustomersLink();
         customersListPage.clickAddNewCustomer();
         customersListPage.clickSaveButton();
         customersListPage.checkingErrorMessagesAllFieldsIsEmpty();
    }

    @Test
    public void test2ErrorCreateNewPatternSameName() throws InterruptedException, IOException, JSONException {
        admin.clickCustomersLink();
        customersListPage.clickAddNewCustomer();
        customersListPage.createNewCustomer(Users.CUSTOMER);
        customersListPage.clickSaveButton();
        customersListPage.checkingErrorMessagesTitleHasBeenUsed();
    }

    @Test
    public void test2ErrorCreateNewPatternOnlyName() throws InterruptedException, IOException, JSONException {
        admin.clickCustomersLink();
        customersListPage.clickAddNewCustomer();
        customersListPage.createNewCustomer(Users.CUSTOMER_ONLY_EMAIL);
        customersListPage.clickSaveButton();
        customersListPage.checkingErrorMessagesTitleHasBeenUsed();
    }

    @Test
    public void test3SearchField() throws InterruptedException, IOException, JSONException {
        admin.clickCustomersLink();
        customersListPage.searchCustomer(Users.CUSTOMER.getEmail());
        customersListPage.checkingEmailFirstCustomer(Users.CUSTOMER);
    }

    @Test
    public void test4ErrorMessageNotVisibleWhenCreateNewCustomer() throws InterruptedException, IOException, JSONException {
        admin.clickCustomersLink();
        customersListPage.clickAddNewCustomer();
        customersListPage.clickSaveButton();
        customersListPage.clickCancelButton();
        customersListPage.clickAddNewCustomer();
        customersListPage.checkingErrorMessagesIsAbsent();
    }
/*
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
    }*/
}
