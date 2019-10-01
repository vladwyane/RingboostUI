package pages.admin;

import blocks.admin.extrasTFNumbers.*;
import data.Carriers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Created by bigdrop on 9/12/2019.
 */
public class ExtrasTFNumbers extends BasePage {

    public ExtrasTFNumbers(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    AddAreaCodesPopup addAreaCodesPopup;
    AddMinutesPopup addMinutesPopup;
    AddMultipleAreaPopup addMultipleAreaPopup;
    AddTermPremiumPopup addTermPremiumPopup;
    AddTermSparePopup addTermSparePopup;
    AddTierPopup addTierPopup;
    TermBasic800Popup termBasic800Popup;
    ExtrasTFNumbersTable extrasTFNumbersTable;

/*    public void fillAddTierForm(String name, String value) {
        waitUntilElementAppeared(addTierPopup.getButtonSave());
        type(addTierPopup.getTierName(), name);
        type(addTierPopup.getTierValue(), value);
    }

    public void clickTab(String nameTab) {
        waitUntilElementAppeared(extrasTFNumbersTable.getListOfTabs().get(0));
        for(WebElement element : extrasTFNumbersTable.getListOfTabs()) {
            if (element.getText().equals(nameTab)) {
                element.click();
            }
        }
    }

    public void clickAddTierButton() {
        waitUntilElementAppeared(extrasTFNumbersTable.getAddTierButton());
        extrasTFNumbersTable.getAddTierButton().click();
    }

    public void clickAddRuleButton() {
        waitUntilElementAppeared(extrasTFNumbersTable.getAddRuleButton());
        extrasTFNumbersTable.getAddRuleButton().click();
    }*/

/*    public void chooseLocation(String location) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getMultiSelectAreaCodes());
        premiumNumberURLGenerator.getMultiSelectAreaCodes().click();
        waiting2seconds();
        for (int i = 0; i < premiumNumberURLGenerator.getListOfAreaCodes().size(); i++) {
            if(i == amountAreaCodes)
                return;
            premiumNumberURLGenerator.getListOfAreaCodes().get(i).click();
        }
    }

    public void chooseAreaCodes(int amountAreaCodes) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getMultiSelectAreaCodes());
        premiumNumberURLGenerator.getMultiSelectAreaCodes().click();
        waiting2seconds();
        for (int i = 0; i < premiumNumberURLGenerator.getListOfAreaCodes().size(); i++) {
            if(i == amountAreaCodes)
                return;
            premiumNumberURLGenerator.getListOfAreaCodes().get(i).click();
        }
    }*/
public void clickSave() {
    addTierPopup.getButtonSave().click();
}


}
