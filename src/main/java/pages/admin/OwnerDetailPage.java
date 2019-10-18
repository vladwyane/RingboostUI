package pages.admin;

import blocks.admin.owners.AddFilePopup;
import blocks.admin.owners.NewOwnerPopup;
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

    @FindBy(xpath = "//input[@aria-label='Default agreement']//following-sibling::div[1]")
    private WebElement checkboxActiveAgreement;

    @FindBy(xpath = "//div[contains(text(), 'Add file')]")
    private WebElement buttonAddFile;

    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    private WebElement buttonSave;

    public void clickAddFileButton() {
        waitUntilElementAppeared(buttonAddFile);
        scrollToElement(buttonAddFile);
        buttonAddFile.click();
    }

    public void addFile(String contractDate) {
        waitUntilElementAppeared(addFilePopup.getButtonSaveFile());
        addFilePopup.getUploadFile().sendKeys("F:\\projects\\RingboostUI\\src\\main\\resources\\ordersDetail.json");
        chooseDateFromDatePicker(contractDate);
        waitUntilElementWillBeClickable(addFilePopup.getButtonSaveFile());
        addFilePopup.getButtonSaveFile().click();
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
        waitUntilElementAppeared(checkboxActiveAgreement);
        waitUntilElementWillBeClickable(checkboxActiveAgreement);
        checkboxActiveAgreement.click();
    }

    public void fillNotes(String text) {
        waiting2seconds();
        type(notes, text);
    }

    public void fillAgreementText(String agreementText) throws InterruptedException {
        waiting2seconds();
        scrollToElement(editorAgreement);
        sendKeysSlowly(editorAgreement, agreementText);
        waitUntilElementAppeared(newOwnerPopup.getButtonSave());
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(newOwnerPopup.getButtonSave());
        waitUntilElementWillBeClickable(newOwnerPopup.getButtonSave());
        scrollToElement(newOwnerPopup.getButtonSave());
        newOwnerPopup.getButtonSave().click();
        waiting2seconds();
    }

}
