package pages.admin;

import blocks.admin.owners.NewOwnerPopup;
import blocks.admin.owners.OwnersListTable;
import data.Carriers;
import data.OwnersData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by bigdrop on 10/15/2019.
 */
public class OwnersListingPage extends BasePage {

    OwnersListTable ownersListTable;
    NewOwnerPopup newOwnerPopup;

    public OwnersListingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void clickAddNewOwnerButton() {
        waitUntilElementAppeared(ownersListTable.getButtonAddNewOwner());
        waitUntilElementWillBeClickable(ownersListTable.getButtonAddNewOwner());
        ownersListTable.getButtonAddNewOwner().click();
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(newOwnerPopup.getButtonSave());
        waitUntilElementWillBeClickable(newOwnerPopup.getButtonSave());
        newOwnerPopup.getButtonSave().click();
        waiting2seconds();
    }

    public void clickCancelButton() {
        waitUntilElementAppeared(newOwnerPopup.getButtonCancel());
        waitUntilElementWillBeClickable(newOwnerPopup.getButtonCancel());
        newOwnerPopup.getButtonCancel().click();
    }

    public void fillNewOwnerForm(OwnersData ownersData) {
        waitUntilElementAppeared(newOwnerPopup.getButtonSave());
        type(newOwnerPopup.getCompanyField(), ownersData.getCompany());
        type(newOwnerPopup.getContactNameField(), ownersData.getContactName());
        type(newOwnerPopup.getPhoneField(), ownersData.getPhone());
        type(newOwnerPopup.getEmailField(), ownersData.getEmail());
        type(newOwnerPopup.getCommissionField(), ownersData.getCommission());
    }

    public void createNewOwner(OwnersData ownersData) {
        fillNewOwnerForm(ownersData);
        waitUntilElementWillBeClickable(newOwnerPopup.getButtonSave());
        newOwnerPopup.getButtonSave().click();
    }

    public void searchOwner (String searchRequest) {
        waiting2seconds();
        ownersListTable.getSearchField().sendKeys(Keys.CONTROL + "a");
        ownersListTable.getSearchField().sendKeys(Keys.DELETE);
        type(ownersListTable.getSearchField(), searchRequest);
        waiting2seconds();
    }

    public void clickEditIconFirstOwner() {
        waitUntilElementAppeared(ownersListTable.getListOfActionsOwners().get(0));
        waitUntilElementWillBeClickable(ownersListTable.getListOfActionsOwners().get(0));
        ownersListTable.getListOfActionsOwners().get(0).click();
    }

    public void clickDeleteIconFirstOwner() {
        waitUntilElementAppeared(ownersListTable.getListOfActionsOwners().get(1));
        waitUntilElementWillBeClickable(ownersListTable.getListOfActionsOwners().get(1));
        ownersListTable.getListOfActionsOwners().get(1).click();
        waiting2seconds();
        ownersListTable.getButtonDelete().click();
        waiting2seconds();
    }

    public void clickColumnheaderOfTable(String titleTh) {
        waitUntilElementAppeared(ownersListTable.getListColumnHeader().get(0));
        waitUntilElementWillBeClickable(ownersListTable.getListColumnHeader().get(0));
        for (int i = 0; i < ownersListTable.getListColumnHeader().size(); i++) {
            if(ownersListTable.getListColumnHeader().get(i).getText().contains(titleTh)){
                ownersListTable.getListColumnHeader().get(i).click();
                return;
            }
        }
    }

    public void checkingSuccessAlertMessage() {
        waitUntilElementAppeared(ownersListTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(ownersListTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAllFieldsIsEmpty() {
        waitUntilElementAppeared(newOwnerPopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(newOwnerPopup.getListOfErrorMessage().get(0).getText(), "The name must be a string.The name field is required.");
        softAssert.assertEquals(newOwnerPopup.getListOfErrorMessage().get(1).getText(), "The email must be a valid email address.The email field is required.");
        softAssert.assertEquals(newOwnerPopup.getListOfErrorMessage().get(2).getText(), "The phone must be a string.");
        softAssert.assertEquals(newOwnerPopup.getListOfErrorMessage().get(3).getText(), "The company must be a string.The company field is required.");
        softAssert.assertEquals(newOwnerPopup.getListOfErrorMessage().get(4).getText(), "The commission must be an integer.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesIsAbsent() {
        waitUntilElementAppeared(newOwnerPopup.getButtonSave());
        waiting2seconds();
        softAssert.assertTrue(isElementInvisible(newOwnerPopup.getListOfErrorMessage().get(0)));
        softAssert.assertAll();
    }

    public void checkingErrorMessagesTitleHasBeenUsed() {
        waitUntilElementAppeared(newOwnerPopup.getButtonSave());
        waiting2seconds();
        softAssert.assertEquals(newOwnerPopup.getListOfErrorMessage().get(0).getText(), "The title has already been taken.");
        softAssert.assertAll();
    }

    public void checkingEmailFirstOwner(OwnersData ownersData) {
        softAssert.assertEquals(ownersListTable.getListTd().get(3).getText(), ownersData.getEmail());
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted() {
        waitUntilElementAppeared(ownersListTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(ownersListTable.getSuccessAlert(), "style", "display");;
        softAssert.assertFalse(result);
        softAssert.assertEquals(ownersListTable.getListTd().get(0).getText(), "No matching records found");
        softAssert.assertAll();
    }

    public void checkingCorrectNameFile(String nameLastAddedFile) {
        waitUntilElementAppeared(ownersListTable.getButtonAddNewOwner());
        waitUntilElementWillBeClickable(ownersListTable.getButtonAddNewOwner());
        int index = 0;
        for (int i = 0; i < ownersListTable.getListColumnHeader().size(); i++) {
            if(ownersListTable.getListColumnHeader().get(i).getText().contains("Files")){
                index = i;
                break;
            }
        }
        softAssert.assertEquals(ownersListTable.getListTd().get(index).getText(), nameLastAddedFile);
        softAssert.assertAll();
    }

}
