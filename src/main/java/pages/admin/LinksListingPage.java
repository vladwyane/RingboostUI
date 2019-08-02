package pages.admin;

import blocks.admin.ListGeneratedURL;
import blocks.admin.LocalNumberURLGenerator;
import org.openqa.selenium.WebDriver;
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

    public void checkingCorrectRedirect() {

    }

    public String clickCreateNewURLButton() {
        String labelPhoneNumbers = null;
        return labelPhoneNumbers;
    }

    public void generateLinkWithPromocode(String priceOverride) {
        waitUntilElementWillBeClickable(listGeneratedURL.getButtonCreateNewURL());
        listGeneratedURL.getButtonCreateNewURL().click();
        waitUntilElementAppeared(localNumberURLGenerator.getButtonGenerateLink());
        type(localNumberURLGenerator.getPriceOverride(), priceOverride);
        localNumberURLGenerator.getShowPromoCodeCheckbox().click();
        waitUntilElementWillBeClickable(localNumberURLGenerator.getButtonGenerateLink());
        localNumberURLGenerator.getButtonGenerateLink().click();
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
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(0));
        return listGeneratedURL.getListOfActionsURL().get(indexLink * 8).getText();

    }
}
