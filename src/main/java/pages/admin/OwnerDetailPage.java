package pages.admin;

import blocks.admin.owners.AddFilePopup;
import blocks.admin.owners.NewOwnerPopup;
import data.OwnersData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.BasePage;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

/**
 * Created by bigdrop on 10/17/2019.
 */
public class OwnerDetailPage extends BasePage {

    AddFilePopup addFilePopup;
    NewOwnerPopup newOwnerPopup;

    public OwnerDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    @Name("List of Td in table files")
    @FindBys( {@FindBy(xpath = "//div[contains(text(), 'Files')]/ancestor::nav/following-sibling::div//td")} )
    private List<WebElement> listTdFile;

    @FindBy(css = "textarea")
    private TextInput notes;

    @FindBy(xpath = "//div[@data-placeholder='Insert text here ...']")
    private WebElement editorAgreement;

    @FindBy(xpath = "//form//input[@type='checkbox']/ancestor::div[1]/following-sibling::label")
    private WebElement labelCheckboxAgreement;

    @FindBy(xpath = "//div[contains(text(), 'Add file')]")
    private WebElement buttonAddFile;

    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    private WebElement buttonSave;

    @FindBy(css = ".v-alert.success")
    private WebElement successAlert;

    @Name("List of tabs")
    @FindBys( {@FindBy(css = ".v-tabs__div a")} )
    private List<WebElement> listOfTabs;

    public void clickTab(String tabName) {
        waitUntilElementAppeared(listOfTabs.get(0));
        for(WebElement element : listOfTabs) {
            if (element.getText().equals(tabName.toUpperCase())) {
                element.click();
                return;
            }
        }
        listOfTabs.get(0).click();
    }

    public void editOwnerInfo(OwnersData ownersData) {
        fillEditOwnerForm(ownersData);
        waitUntilElementWillBeClickable(buttonSave);
        scrollToElement(buttonSave);
        buttonSave.click();
    }

    public void fillEditOwnerForm(OwnersData ownersData) {
        waitUntilElementAppeared(newOwnerPopup.getButtonSave());
        newOwnerPopup.getCompanyField().sendKeys(Keys.CONTROL + "a");
        newOwnerPopup.getCompanyField().sendKeys(Keys.DELETE);
        newOwnerPopup.getContactNameField().sendKeys(Keys.CONTROL + "a");
        newOwnerPopup.getContactNameField().sendKeys(Keys.DELETE);
        newOwnerPopup.getPhoneField().sendKeys(Keys.CONTROL + "a");
        newOwnerPopup.getPhoneField().sendKeys(Keys.DELETE);
        newOwnerPopup.getEmailField().sendKeys(Keys.CONTROL + "a");
        newOwnerPopup.getEmailField().sendKeys(Keys.DELETE);
        newOwnerPopup.getCommissionField().sendKeys(Keys.CONTROL + "a");
        newOwnerPopup.getCommissionField().sendKeys(Keys.DELETE);
        type(newOwnerPopup.getCompanyField(), ownersData.getCompany());
        type(newOwnerPopup.getContactNameField(), ownersData.getContactName());
        type(newOwnerPopup.getPhoneField(), ownersData.getPhone());
        type(newOwnerPopup.getEmailField(), ownersData.getEmail());
        type(newOwnerPopup.getCommissionField(), ownersData.getCommission());
    }

    public void clickAddFileButton() {
        waitUntilElementAppeared(buttonAddFile);
        scrollToElement(buttonAddFile);
        buttonAddFile.click();
    }

    public void addFile1(String contractDate) {
        waitUntilElementAppeared(addFilePopup.getButtonSaveFile());
        addFilePopup.getUploadFile().sendKeys("F:\\projects\\RingboostUI\\src\\main\\resources\\ordersDetail.json");
        chooseDateFromDatePicker(contractDate);
        waitUntilElementWillBeClickable(addFilePopup.getButtonSaveFile());
        addFilePopup.getButtonSaveFile().click();
        waiting2seconds();
    }

    public void addFile2(String contractDate) {
        waitUntilElementAppeared(addFilePopup.getButtonSaveFile());
        addFilePopup.getUploadFile().sendKeys("F:\\projects\\RingboostUI\\src\\main\\resources\\TestAgreement");
        chooseDateFromDatePicker(contractDate);
        waitUntilElementWillBeClickable(addFilePopup.getButtonSaveFile());
        addFilePopup.getButtonSaveFile().click();
        waiting2seconds();
    }

    public void chooseDateFromDatePicker(String data) {
        waitUntilElementAppeared(addFilePopup.getListOfData().get(0));
        for(WebElement element : addFilePopup.getListOfData()) {
            if (element.getText().equals(data)) {
                element.click();
                return;
            }
        }
    }

    public void clickCheckboxActiveAgreement() {
        waitUntilElementAppeared(labelCheckboxAgreement);
        waitUntilElementWillBeClickable(labelCheckboxAgreement);
        labelCheckboxAgreement.click();
    }

    public void fillNotes(String text) {
        waiting2seconds();
        type(notes, text);
        scrollToElement(buttonSave);
        buttonSave.click();
    }

    public void fillAgreementText(String agreementText) throws InterruptedException {
        waiting2seconds();
        scrollToElement(editorAgreement);
        sendKeysSlowly(editorAgreement, agreementText);
        scrollToElement(buttonSave);
        buttonSave.click();
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(newOwnerPopup.getButtonSave());
        waitUntilElementWillBeClickable(newOwnerPopup.getButtonSave());
        scrollToElement(newOwnerPopup.getButtonSave());
        newOwnerPopup.getButtonSave().click();
        waiting2seconds();
    }

    public String getNameLastFile() {
        waitUntilElementAppeared(buttonAddFile);
        waitUntilElementWillBeClickable(buttonAddFile);
        return listTdFile.get(listTdFile.size() - 3).getText();
    }

    public void checkingAddedMoreThan2Files() {
        waitUntilElementAppeared(buttonAddFile);
        waitUntilElementWillBeClickable(buttonAddFile);
        softAssert.assertTrue(listTdFile.size() > 3, "Amount of files less 2");
        softAssert.assertAll();
    }

    public void checkingCorrectFilledCustomAgreement(String agreement) {
        waitUntilElementAppeared(buttonAddFile);
        waitUntilElementWillBeClickable(buttonAddFile);
        softAssert.assertEquals(editorAgreement.getText(), agreement);
        softAssert.assertEquals("Owner's agreement", labelCheckboxAgreement.getText());
        softAssert.assertAll();
    }

    public void checkingSuccessAlertMessage() {
        waitUntilElementAppeared(successAlert);
        boolean result = isElementContainsAttributeValue(successAlert, "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }


}
