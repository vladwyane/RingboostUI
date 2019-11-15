package pages.admin;

import blocks.admin.customers.CustomersTable;
import blocks.admin.customers.NewCustomerPopup;
import data.OwnersData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by bigdrop on 11/15/2019.
 */
public class CustomersListPage extends BasePage {

    public CustomersListPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    CustomersTable customersTable;
    NewCustomerPopup newCustomerPopup;

    public void clickAddNewCustomer() {
        waitUntilElementAppeared(customersTable.getButtonNewCustomer());
        waitUntilElementWillBeClickable(customersTable.getButtonNewCustomer());
        customersTable.getButtonNewCustomer().click();
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(newCustomerPopup.getButtonSave());
        waitUntilElementWillBeClickable(newCustomerPopup.getButtonSave());
        newCustomerPopup.getButtonSave().click();
        waiting2seconds();
    }

    public void clickCancelButton() {
        waitUntilElementAppeared(newCustomerPopup.getButtonCancel());
        waitUntilElementWillBeClickable(newCustomerPopup.getButtonCancel());
        newCustomerPopup.getButtonCancel().click();
    }

    public void fillNewCustomerForm(OwnersData ownersData) {
        waitUntilElementAppeared(newCustomerPopup.getButtonSave());
        type(newCustomerPopup.getFirstNameField(), ownersData.getContactName());
        type(newCustomerPopup.getLastNameField(), ownersData.getPhone());
        type(newCustomerPopup.getEmailField(), ownersData.getEmail());
        type(newCustomerPopup.getCompanyField(), ownersData.getCompany());
    }

    public void createNewCustomer(OwnersData ownersData) {
        fillNewCustomerForm(ownersData);
        waitUntilElementWillBeClickable(newCustomerPopup.getButtonSave());
        newCustomerPopup.getButtonSave().click();
    }

    public void searchCustomer (String searchRequest) {
        waiting2seconds();
        customersTable.getSearchField().sendKeys(Keys.CONTROL + "a");
        customersTable.getSearchField().sendKeys(Keys.DELETE);
        type(customersTable.getSearchField(), searchRequest);
        waiting2seconds();
    }

    public void clickEditIconFirstCustomer() {
        waitUntilElementAppeared(customersTable.getListOfActions().get(0));
        waitUntilElementWillBeClickable(customersTable.getListOfActions().get(0));
        customersTable.getListOfActions().get(0).click();
    }

    public void clickDeleteIconFirstOwner() {
        waitUntilElementAppeared(customersTable.getListOfActions().get(1));
        waitUntilElementWillBeClickable(customersTable.getListOfActions().get(1));
        customersTable.getListOfActions().get(1).click();
        waiting2seconds();
        customersTable.getButtonDelete().click();
        waiting2seconds();
    }

    public void clickColumnheaderOfTable(String titleTh) {
        waitUntilElementAppeared(customersTable.getListColumnHeader().get(0));
        waitUntilElementWillBeClickable(customersTable.getListColumnHeader().get(0));
        for (int i = 0; i < customersTable.getListColumnHeader().size(); i++) {
            if(customersTable.getListColumnHeader().get(i).getText().contains(titleTh)){
                customersTable.getListColumnHeader().get(i).click();
                return;
            }
        }
    }

    public void checkingSuccessAlertMessage() {
        waitUntilElementAppeared(customersTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(customersTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAllFieldsIsEmpty() {
        waitUntilElementAppeared(newCustomerPopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(newCustomerPopup.getListOfErrorMessage().get(0).getText(), "The name must be a string.The name field is required.");
        softAssert.assertEquals(newCustomerPopup.getListOfErrorMessage().get(1).getText(), "The email must be a valid email address.The email field is required.");
        softAssert.assertEquals(newCustomerPopup.getListOfErrorMessage().get(2).getText(), "The phone must be a string.");
        softAssert.assertEquals(newCustomerPopup.getListOfErrorMessage().get(3).getText(), "The company must be a string.The company field is required.");
        softAssert.assertEquals(newCustomerPopup.getListOfErrorMessage().get(4).getText(), "The commission must be an integer.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesIsAbsent() {
        waitUntilElementAppeared(newCustomerPopup.getButtonSave());
        waiting2seconds();
        softAssert.assertTrue(isElementInvisible(newCustomerPopup.getListOfErrorMessage().get(0)));
        softAssert.assertAll();
    }

    public void checkingErrorMessagesTitleHasBeenUsed() {
        waitUntilElementAppeared(newCustomerPopup.getButtonSave());
        waiting2seconds();
        softAssert.assertEquals(newCustomerPopup.getListOfErrorMessage().get(0).getText(), "The title has already been taken.");
        softAssert.assertAll();
    }

    public void checkingEmailFirstCustomer(OwnersData ownersData) {
        softAssert.assertEquals(customersTable.getListTd().get(3).getText(), ownersData.getEmail());
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted() {
        waitUntilElementAppeared(customersTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(customersTable.getSuccessAlert(), "style", "display");;
        softAssert.assertFalse(result);
        softAssert.assertEquals(customersTable.getListTd().get(0).getText(), "No matching records found");
        softAssert.assertAll();
    }

/*    public void checkingCorrectNameFile(String nameLastAddedFile) {
        waitUntilElementAppeared(customersTable.getButtonAddNeC());
        waitUntilElementWillBeClickable(customersTable.getButtonAddNewOwner());
        int index = 0;
        for (int i = 0; i < ownersListTable.getListColumnHeader().size(); i++) {
            if(ownersListTable.getListColumnHeader().get(i).getText().contains("Files")){
                index = i;
                break;
            }
        }
        softAssert.assertEquals(ownersListTable.getListTd().get(index).getText(), nameLastAddedFile);
        softAssert.assertAll();
    }*/
}
