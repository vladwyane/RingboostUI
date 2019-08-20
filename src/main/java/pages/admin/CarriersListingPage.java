package pages.admin;

import blocks.admin.CarriersTable;
import blocks.admin.NewCarrierPopup;
import data.Carriers;
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

    public void createNewCarrier(Carriers carriers) {
        fillNewCarrierForm(carriers);
        waitUntilElementWillBeClickable(newCarrierPopup.getButtonSave());
        newCarrierPopup.getButtonSave().click();
    }

    public void checkingSuccessAlertMessage() {
        waitUntilElementAppeared(carriersTable.getSuccessAlert());
        boolean result = isElementInvisible(carriersTable.getSuccessAlert());
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

}
