package adminPanel.sprint_4_OwnersAndPriceMatrix;

import data.OwnersData;
import org.json.JSONException;
import org.testng.annotations.*;
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

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        ownersListingPage = new OwnersListingPage(app.getDriver());
        ownerDetailPage = new OwnerDetailPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
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
        ownerDetailPage.clickTab("Files");
        ownerDetailPage.clickAddFileButton();
        ownerDetailPage.addFile1("17");
        ownerDetailPage.clickAddFileButton();
        ownerDetailPage.addFile2("21");
        ownerDetailPage.checkingAddedMoreThan2Files();
    }

    @Test
    public void test4CheckingCorrectNameFileOnListingPage() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.JAMES.getEmail());
        ownersListingPage.clickEditIconFirstOwner();
        ownerDetailPage.clickTab("Files");
        String nameLastAddedFile = ownerDetailPage.getNameLastFile();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.JAMES.getEmail());
        ownersListingPage.checkingCorrectNameFile(nameLastAddedFile);
    }

    @Test
    public void test4CorrectFilledNotesField() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.JAMES.getEmail());
        ownersListingPage.clickEditIconFirstOwner();
        ownerDetailPage.fillNotes("New comment");
        ownerDetailPage.checkingSuccessAlertMessage();
    }

    @Test
    public void test5ActivateCustomAgreement() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.JAMES.getEmail());
        ownersListingPage.clickEditIconFirstOwner();
        ownerDetailPage.clickCheckboxActiveAgreement();
        ownerDetailPage.fillAgreementText("Custom Agreement");
        ownerDetailPage.refreshPage();
        ownerDetailPage.checkingCorrectFilledCustomAgreement("Custom Agreement");
    }

    @Test
    public void test5ErrorMessageNotVisibleWhenCreateNewOwner() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.clickAddNewOwnerButton();
        ownersListingPage.clickSaveButton();
        ownersListingPage.clickCancelButton();
        ownersListingPage.clickAddNewOwnerButton();
        ownersListingPage.checkingErrorMessagesIsAbsent();
    }

    @Test
    public void test6EditSaveFunctional() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.JAMES.getEmail());
        ownersListingPage.clickEditIconFirstOwner();
        ownerDetailPage.editOwnerInfo(OwnersData.VLADYSLAV);
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.VLADYSLAV.getEmail());
        ownersListingPage.checkingEmailFirstOwner(OwnersData.VLADYSLAV);
    }

    @Test
    public void test7SDeleteButton() throws InterruptedException, IOException, JSONException {
        login.open();
        admin.clickOwnersLink();
        ownersListingPage.searchOwner(OwnersData.VLADYSLAV.getEmail());
        ownersListingPage.clickDeleteIconFirstOwner();
        ownersListingPage.checkingSuccessDeleted();
    }

}
