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
 * Created by bigdrop on 10/15/2019.
 */
public class Owners extends TestBase {

    private Login login;
    private Admin admin;
    private OwnersListingPage ownersListingPage;
    private OwnerDetailPage ownerDetailPage;

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        ownersListingPage = new OwnersListingPage(app.getDriver());
        ownerDetailPage = new OwnerDetailPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewOwner() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.clickAddNewOwnerButton();
        ownersListingPage.createNewOwner(OwnersData.JAMES);
        ownersListingPage.checkingSuccessAlertMessage();
    }

    @Test
    public void test2ErrorCreateNewOwnerAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.clickAddNewOwnerButton();
        ownersListingPage.clickSaveButton();
        ownersListingPage.checkingErrorMessagesAllFieldsIsEmpty();
    }

    @Test
    public void test3SearchField() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.JAMES.getEmail());
        ownersListingPage.checkingEmailFirstOwner(OwnersData.JAMES);
    }

    @Test
    public void test3AddFile() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.JAMES.getEmail());
        ownersListingPage.clickEditIconFirstOwner();
        ownerDetailPage.fillNotes("NotesText");
        ownerDetailPage.clickCheckboxActiveAgreement();
        ownerDetailPage.fillAgreementText("Agreement text");
        ownerDetailPage.clickAddFileButton();
        ownerDetailPage.addFile("17");
        ownerDetailPage.clickSaveButton();
    }

    @Test
    public void test4ErrorMessageNotVisibleWhenCreateNewOwner() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.clickAddNewOwnerButton();
        ownersListingPage.clickSaveButton();
        ownersListingPage.clickCancelButton();
        ownersListingPage.clickAddNewOwnerButton();
        ownersListingPage.checkingErrorMessagesIsAbsent();
    }

    @Test
    public void test5EditButton() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.JAMES.getEmail());
        ownersListingPage.clickEditIconFirstOwner();
        ownersListingPage.editOwnerInfo(OwnersData.VLADYSLAV);
        ownersListingPage.searchOwner(OwnersData.VLADYSLAV.getEmail());
        ownersListingPage.checkingEmailFirstOwner(OwnersData.VLADYSLAV);
    }

    @Test
    public void test6SDeleteButton() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.VLADYSLAV.getEmail());
        ownersListingPage.clickDeleteIconFirstOwner();
        ownersListingPage.checkingSuccessDeleted();
    }



}
