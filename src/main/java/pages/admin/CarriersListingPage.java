package pages.admin;

import blocks.admin.carriers.CarriersTable;
import blocks.admin.carriers.NewCarrierPopup;
import data.Carriers;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 8/20/2019.
 */
public class CarriersListingPage extends BasePage {

    public CarriersListingPage(WebDriver driver) {
        super(driver);
    }

    CarriersTable carriersTable;
    NewCarrierPopup newCarrierPopup;

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("adminCarriers.url"));
    }

    public void clickAddCarrierButton() {
        waitUntilElementAppeared(carriersTable.getButtonAddCarrier());
        waitUntilElementWillBeClickable(carriersTable.getButtonAddCarrier());
        carriersTable.getButtonAddCarrier().click();
    }

    public void clickSaveButton() {
        waitUntilElementAppeared(newCarrierPopup.getButtonSave());
        waitUntilElementWillBeClickable(newCarrierPopup.getButtonSave());
        newCarrierPopup.getButtonSave().click();
        waiting2seconds();
    }

    public void clickCancelButton() {
        waitUntilElementAppeared(newCarrierPopup.getButtonCancel());
        waitUntilElementWillBeClickable(newCarrierPopup.getButtonCancel());
        newCarrierPopup.getButtonCancel().click();
    }

    public void fillNewCarrierForm(Carriers carriers) {
        waitUntilElementAppeared(newCarrierPopup.getButtonSave());
        type(newCarrierPopup.getCarrierName(), carriers.getCarriers());
        type(newCarrierPopup.getMrcField(), carriers.getMrc());
        type(newCarrierPopup.getNrcField(), carriers.getNrc());
        type(newCarrierPopup.getPerMinuteField(), carriers.getPerMinuteUsage());
        type(newCarrierPopup.getPortInFeesField(), carriers.getPortInFees());
        type(newCarrierPopup.getPortOutFeesField(), carriers.getPortOutFees());
        type(newCarrierPopup.getNotesField(), carriers.getNotes());
    }

    public void fillEditCarrierForm(Carriers carriers) {
        waitUntilElementAppeared(newCarrierPopup.getButtonSave());
        newCarrierPopup.getCarrierName().sendKeys(Keys.CONTROL + "a");
        newCarrierPopup.getCarrierName().sendKeys(Keys.DELETE);
        newCarrierPopup.getMrcField().sendKeys(Keys.CONTROL + "a");
        newCarrierPopup.getMrcField().sendKeys(Keys.DELETE);
        newCarrierPopup.getNrcField().sendKeys(Keys.CONTROL + "a");
        newCarrierPopup.getNrcField().sendKeys(Keys.DELETE);
        newCarrierPopup.getPerMinuteField().sendKeys(Keys.CONTROL + "a");
        newCarrierPopup.getPerMinuteField().sendKeys(Keys.DELETE);
        newCarrierPopup.getPortInFeesField().sendKeys(Keys.CONTROL + "a");
        newCarrierPopup.getPortInFeesField().sendKeys(Keys.DELETE);
        newCarrierPopup.getPortOutFeesField().sendKeys(Keys.CONTROL + "a");
        newCarrierPopup.getPortOutFeesField().sendKeys(Keys.DELETE);
        newCarrierPopup.getNotesField().sendKeys(Keys.CONTROL + "a");
        newCarrierPopup.getNotesField().sendKeys(Keys.DELETE);
        type(newCarrierPopup.getCarrierName(), carriers.getCarriers());
        type(newCarrierPopup.getMrcField(), carriers.getMrc());
        type(newCarrierPopup.getNrcField(), carriers.getNrc());
        type(newCarrierPopup.getPerMinuteField(), carriers.getPerMinuteUsage());
        type(newCarrierPopup.getPortInFeesField(), carriers.getPortInFees());
        type(newCarrierPopup.getPortOutFeesField(), carriers.getPortOutFees());
        type(newCarrierPopup.getNotesField(), carriers.getNotes());
    }

    public void createNewCarrier(Carriers carriers) {
        fillNewCarrierForm(carriers);
        waitUntilElementWillBeClickable(newCarrierPopup.getButtonSave());
        newCarrierPopup.getButtonSave().click();
    }

    public void editCarrierInfo(Carriers carriers) {
        fillEditCarrierForm(carriers);
        waitUntilElementWillBeClickable(newCarrierPopup.getButtonSave());
        newCarrierPopup.getButtonSave().click();
    }

    public void searchCarrier (Carriers carriers) {
        waiting2seconds();
        carriersTable.getSearchField().sendKeys(Keys.CONTROL + "a");
        carriersTable.getSearchField().sendKeys(Keys.DELETE);
        type(carriersTable.getSearchField(), carriers.getCarriers());
        waiting2seconds();
    }

    public void clickEditIconFirstCarriers() {
        waitUntilElementAppeared(carriersTable.getListOfActionsCarriers().get(0));
        waitUntilElementWillBeClickable(carriersTable.getListOfActionsCarriers().get(0));
        carriersTable.getListOfActionsCarriers().get(0).click();
    }

    public void clickDeleteIconFirstCarriers() {
        waitUntilElementAppeared(carriersTable.getListOfActionsCarriers().get(1));
        waitUntilElementWillBeClickable(carriersTable.getListOfActionsCarriers().get(1));
        carriersTable.getListOfActionsCarriers().get(1).click();
        waiting2seconds();
        carriersTable.getButtonDelete().click();
        waiting2seconds();
    }

    public void clickColumnheaderOfTable(String titleTh) {
        waitUntilElementAppeared(carriersTable.getListColumnHeader().get(0));
        waitUntilElementWillBeClickable(carriersTable.getListColumnHeader().get(0));
        for (int i = 0; i < carriersTable.getListColumnHeader().size(); i++) {
            if(carriersTable.getListColumnHeader().get(i).getText().contains(titleTh)){
                carriersTable.getListColumnHeader().get(i).click();
                return;
            }
        }
    }

    public void checkingSuccessAlertMessage() {
        waitUntilElementAppeared(carriersTable.getSuccessAlert());
        boolean result = isElementInvisible(carriersTable.getSuccessAlert());
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void checkingErrorMessages() {
        waitUntilElementAppeared(newCarrierPopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(newCarrierPopup.getListOfErrorMessage().get(0).getText(), "The title field is required.");
        softAssert.assertEquals(newCarrierPopup.getListOfErrorMessage().get(1).getText(), "The mrc must be a number.");
        softAssert.assertEquals(newCarrierPopup.getListOfErrorMessage().get(2).getText(), "The nrc must be a number.");
        softAssert.assertEquals(newCarrierPopup.getListOfErrorMessage().get(3).getText(), "The per minute must be a number.");
        softAssert.assertEquals(newCarrierPopup.getListOfErrorMessage().get(4).getText(), "The port in fees must be a number.");
        softAssert.assertEquals(newCarrierPopup.getListOfErrorMessage().get(5).getText(), "The port out fees must be a number.");
        softAssert.assertAll();
    }

    public void checkingErrorMessagesIsAbsent() {
        waitUntilElementAppeared(newCarrierPopup.getButtonSave());
        waiting2seconds();
        softAssert.assertTrue(isElementInvisible(newCarrierPopup.getListOfErrorMessage().get(0)));
        softAssert.assertAll();
    }

    public void checkingErrorMessagesTitleHasBeenUsed() {
        waitUntilElementAppeared(newCarrierPopup.getButtonSave());
        waiting2seconds();
        softAssert.assertEquals(newCarrierPopup.getListOfErrorMessage().get(0).getText(), "The title has already been taken.");
        softAssert.assertAll();
    }

    public void checkingTitleFirstCarrier(Carriers carriers) {
        softAssert.assertEquals(carriersTable.getListTd().get(0).getText(), carriers.getCarriers());
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted() {
        waitUntilElementAppeared(carriersTable.getSuccessAlert());
        boolean result = isElementInvisible(carriersTable.getSuccessAlert());
        softAssert.assertFalse(result);
        softAssert.assertEquals(carriersTable.getListTd().get(0).getText(), "No matching records found");
        softAssert.assertAll();
    }

    public void checkingCorrectSorting(String headingColumn) {
        int indexColumn = 0;
        boolean result = false;
        for (int i = 0; i < carriersTable.getListColumnHeader().size(); i++) {
            if(carriersTable.getListColumnHeader().get(i).getText().contains(headingColumn)){
                indexColumn = i;
                break;
            }
        }
        Double topValue = Double.parseDouble(carriersTable.getListTd().get(indexColumn).getText().replaceAll("\\D+",""));
        for (int i = indexColumn; i < carriersTable.getListTd().size(); i += carriersTable.getListColumnHeader().size()) {
            if(Double.parseDouble(carriersTable.getListTd().get(i).getText().replaceAll("\\D+","")) <= topValue) {
                topValue = Double.parseDouble(carriersTable.getListTd().get(i).getText().replaceAll("\\D+",""));
                result =  true;
            }
            else {
                result = false;
                break;
            }
        }
        softAssert.assertTrue(result);
        softAssert.assertAll();

    }

}
