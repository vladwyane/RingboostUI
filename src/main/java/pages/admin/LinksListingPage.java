package pages.admin;

import blocks.admin.ListGeneratedURL;
import blocks.admin.LocalNumberURLGenerator;
import blocks.admin.PremiumNumberURLGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;

/**
 * Created by bigdrop on 8/2/2019.
 */
public class LinksListingPage extends BasePage {

    public LinksListingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    ListGeneratedURL listGeneratedURL;
    LocalNumberURLGenerator localNumberURLGenerator;
    PremiumNumberURLGenerator premiumNumberURLGenerator;

    public void checkingCorrectRedirect() {

    }

    public String clickCreateNewURLButton() {
        String labelPhoneNumbers = null;
        return labelPhoneNumbers;
    }

    public void goToGeneratedLink(String link) {
        driver.get(link);
    }

    public void clickEditButton(int indexNumber) {
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(0));
        listGeneratedURL.getListOfActionsURL().get(indexNumber * 4).click();
    }

    public void clickRenewButton(int indexNumber) {
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(0));
        listGeneratedURL.getListOfActionsURL().get(indexNumber * 4 + 1).click();
    }

    public void clickDeleteButton(int indexNumber) {
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(0));
        listGeneratedURL.getListOfActionsURL().get(indexNumber * 4 + 2).click();
    }

    public void clickCopyButton(int indexNumber) {
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(0));
        listGeneratedURL.getListOfActionsURL().get(indexNumber * 4 + 3).click();
    }

    public void chooseState(String stateName) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getSelectOfStates());
        premiumNumberURLGenerator.getSelectOfStates().click();
        waiting2seconds();
        for(WebElement element : premiumNumberURLGenerator.getListOfStates()) {
            if (element.getText().equals(stateName)) {
                element.click();
                return;
            }
        }
        premiumNumberURLGenerator.getListOfStates().get(0).click();
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
    }

    public void chooseMonthlyMinutes() {
        waitUntilElementWillBeClickable(premiumNumberURLGenerator.getBulletOfSlider());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(premiumNumberURLGenerator.getBulletOfSlider(), 100, 0).build();
        actionFirstBull.perform();
    }

    public void chooseTermLength(String termLength) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getSelectTermLength());
        premiumNumberURLGenerator.getSelectTermLength().click();
        waiting2seconds();
        for(WebElement element : premiumNumberURLGenerator.getListOTermLength()) {
            if (element.getText().equals(termLength)) {
                element.click();
                return;
            }
        }
        premiumNumberURLGenerator.getListOTermLength().get(0).click();
    }

    public void generateLinkWithPromoCode(String priceOverride, String state, int amountAreaCodes, String termLength) {
        waitUntilElementWillBeClickable(listGeneratedURL.getButtonCreateNewURL());
        listGeneratedURL.getButtonCreateNewURL().click();
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        chooseMonthlyMinutes();
        chooseState(state);
        chooseAreaCodes(amountAreaCodes);
        chooseTermLength(termLength);
        type(premiumNumberURLGenerator.getPriceForAreaCodes(), priceOverride);
        premiumNumberURLGenerator.getShowPromoCodeCheckbox().click();
        waitUntilElementWillBeClickable(premiumNumberURLGenerator.getButtonGenerateLink());
        premiumNumberURLGenerator.getButtonGenerateLink().click();
    }

    public void generateLinkWithChangeDisplayedInfo(String priceOverride, String displayedName) {
        waitUntilElementWillBeClickable(listGeneratedURL.getButtonCreateNewURL());
        listGeneratedURL.getButtonCreateNewURL().click();
        waitUntilElementAppeared(localNumberURLGenerator.getButtonGenerateLink());
        type(localNumberURLGenerator.getPriceOverride(), priceOverride);
        type(localNumberURLGenerator.getDisplayedNumberOnFE(), displayedName);
        waitUntilElementWillBeClickable(localNumberURLGenerator.getButtonGenerateLink());
        localNumberURLGenerator.getButtonGenerateLink().click();
    }

    public String getGeneratedLink(int indexLink) {
       waitUntilElementAppeared(listGeneratedURL.getListTd().get(0));
        waiting2seconds();
        return listGeneratedURL.getListTd().get(indexLink * 2).getText();
    }
}
