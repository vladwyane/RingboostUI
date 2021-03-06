package pages.admin;

import blocks.admin.Breadcrumbs;
import blocks.admin.generatorLinks.ListGeneratedURL;
import blocks.admin.generatorLinks.LocalNumberURLGenerator;
import blocks.admin.generatorLinks.PremiumNumberURLGenerator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    Breadcrumbs breadcrumbs;

    public void checkingCorrectRedirect() {

    }

    public int getTdSize() {
        waiting2seconds();
        return listGeneratedURL.getListTd().size();
    }

    public void clickBreadcrunbsLink(String nameLink) {
        isElementInvisible(listGeneratedURL.getOverlay());
        waitUntilElementAppeared(breadcrumbs.getListOfBreadcrumbsLink().get(0));
        for(WebElement element : breadcrumbs.getListOfBreadcrumbsLink()) {
            if (element.getText().equals(nameLink)) {
                element.click();
            }
        }
    }

    public void goToGeneratedLink(String link) {
        driver.get(link);
    }

    public void goToGeneratedLinkAfterCopyPaste(int index) {
        waiting2seconds();
        listGeneratedURL.getListOfActionsURL().get(index * 4).click();
        localNumberURLGenerator.getDisplayedNumberOnFE().sendKeys(Keys.CONTROL + "a");
        localNumberURLGenerator.getDisplayedNumberOnFE().sendKeys(Keys.DELETE);
        premiumNumberURLGenerator.getDisplayedNumberOnFE().sendKeys(Keys.CONTROL + "v");
        String copyPaste = premiumNumberURLGenerator.getDisplayedNumberOnFE().getEnteredText();
        driver.get(copyPaste);
    }


    public void clickEditButton(int indexOfLink) {
        waiting2seconds();
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(indexOfLink * 3));
        listGeneratedURL.getListOfActionsURL().get(indexOfLink * 3).click();
    }

    public void clickRenewButton(int indexNumber) {
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(0));
        listGeneratedURL.getListOfActionsURL().get(indexNumber * 4 + 1).click();
    }

    public void clickDeleteButton(int indexNumber) {
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(0));
        listGeneratedURL.getListOfActionsURL().get(indexNumber * 3 + 1).click();
        waiting2seconds();
        listGeneratedURL.getButtonDelete().click();
        waiting2seconds();
    }

    public void deleteAllLink() {
        waitUntilElementAppeared(listGeneratedURL.getListOfActionsURL().get(0));
        while (listGeneratedURL.getListOfActionsURL().size() > 0) {
            listGeneratedURL.getListOfActionsURL().get(2).click();
            waiting2seconds();
            listGeneratedURL.getButtonDelete().click();
            waiting2seconds();
        }

    }

    public void checkingAfterDelete(String linkBefore, String linkAfter) {
        softAssert.assertNotEquals (linkBefore, linkAfter);
        softAssert.assertAll();
    }

    public void clickCopyButton(int indexNumber) {
        waiting2seconds();
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

    public void chooseMonthlyMinutes(String minutes) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getSelectMinutes());
        waiting2seconds();
        premiumNumberURLGenerator.getSelectMinutes().click();
        waiting2seconds();
        for(WebElement element : premiumNumberURLGenerator.getListOfMinutes()) {
            if (element.getText().equals(minutes)) {
                element.click();
                return;
            }
        }
        premiumNumberURLGenerator.getListOfMinutes().get(0).click();
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

    public void clickCreateNewURLButton() {
        //isElementInvisible(listGeneratedURL.getOverlay());
        waiting2seconds();
        waitUntilElementWillBeClickable(listGeneratedURL.getButtonCreateNewURL());
        listGeneratedURL.getButtonCreateNewURL().click();
    }

    public String generateLinkWithPromoCodePremiumFlow(String priceOverride, String state, int amountAreaCodes,
                                                       String termLength, String minutes, String displayNamed) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        String filledDisplayName = premiumNumberURLGenerator.getDisplayedNumberOnFE().getEnteredText();
        premiumNumberURLGenerator.getDisplayedNumberOnFE().sendKeys(Keys.CONTROL + "a");
        premiumNumberURLGenerator.getDisplayedNumberOnFE().sendKeys(Keys.DELETE);
        premiumNumberURLGenerator.getPriceForAreaCodes().sendKeys(Keys.CONTROL + "a");
        premiumNumberURLGenerator.getPriceForAreaCodes().sendKeys(Keys.DELETE);
        type(premiumNumberURLGenerator.getPriceForAreaCodes(), priceOverride);
        type(premiumNumberURLGenerator.getDisplayedNumberOnFE(), replaceDisplayNameFieldToLetters(filledDisplayName) + displayNamed);
        chooseMonthlyMinutes(minutes);
        chooseState(state);
        chooseAreaCodes(amountAreaCodes);
        premiumNumberURLGenerator.getSelectOfCounties().click();
        chooseTermLength(termLength);
        premiumNumberURLGenerator.getShowPromoCodeCheckbox().click();
        String enteredText = premiumNumberURLGenerator.getDisplayedNumberOnFE().getEnteredText();
        return enteredText;
    }

    public double getSumOldPriceChosenAreaCodes() {
        double sum = 0.0;
        for (int i = 0; i < premiumNumberURLGenerator.getListOfChosenAreaCodes().size(); i++) {
            double priceAreaCode = Double.parseDouble(premiumNumberURLGenerator.getListOfChosenAreaCodes().get(i).getText()
                            .substring(premiumNumberURLGenerator.getListOfChosenAreaCodes().get(i).getText().indexOf("$") + 1));
            sum = sum + priceAreaCode;
        }
        return sum;
    }

    public int getAmountOfMinute() {
        return Integer.parseInt(premiumNumberURLGenerator.getSelectMinutes().getText());
    }

    public double getPriceMonthlyMinute() {
        return Double.parseDouble(premiumNumberURLGenerator.getPriceMonthlyMinutes().getText()
                .substring(premiumNumberURLGenerator.getPriceMonthlyMinutes().getText().indexOf("$") + 1));
    }

    public double getSaleSelectedPlan() {
        return Double.parseDouble(premiumNumberURLGenerator.getSaleSelectedPlan().getText()
                .substring(premiumNumberURLGenerator.getSaleSelectedPlan().getText().indexOf("$") + 2));
    }

    public int getPricePlanDuration(String termLength) {
        int duration;
        switch(termLength) {
            case "1 year":
                duration = 12;
                break;
            case "2 years":
                duration = 24;
                break;
            case "3 years":
                duration = 36;
                break;
            default:
                duration = 0;
        }
        return duration;
    }

    public String generateLinkWithoutPromoCodePremiumFlow(String priceOverride, String state,
                                                          int amountAreaCodes, String termLength, String minutes) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        type(premiumNumberURLGenerator.getPriceForAreaCodes(), priceOverride);
        chooseMonthlyMinutes(minutes);
        chooseState(state);
        chooseAreaCodes(amountAreaCodes);
        premiumNumberURLGenerator.getSelectOfCounties().click();
        chooseTermLength(termLength);
        String labelNumber = premiumNumberURLGenerator.getLabelPhoneNumber().getText();
        return labelNumber;
    }

    public String generateLinkWithoutPromoCodeRegularFlow(String priceOverride) {
        waitUntilElementAppeared(localNumberURLGenerator.getButtonGenerateLink());
        type(localNumberURLGenerator.getPriceOverride(), priceOverride);
        String labelNumber = localNumberURLGenerator.getLabelPhoneNumber().getText();
        return labelNumber;
    }

    public String generateLinkWithPromoCodeRegularFlow(String priceOverride, String displayedName) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        String filledDisplayName = localNumberURLGenerator.getDisplayedNumberOnFE().getEnteredText();
        localNumberURLGenerator.getDisplayedNumberOnFE().sendKeys(Keys.CONTROL + "a");
        localNumberURLGenerator.getDisplayedNumberOnFE().sendKeys(Keys.DELETE);
        localNumberURLGenerator.getPriceOverride().sendKeys(Keys.CONTROL + "a");
        localNumberURLGenerator.getPriceOverride().sendKeys(Keys.DELETE);
        type(localNumberURLGenerator.getPriceOverride(), priceOverride);
        type(localNumberURLGenerator.getDisplayedNumberOnFE(), replaceDisplayNameFieldToLetters(filledDisplayName) + displayedName);
        premiumNumberURLGenerator.getShowPromoCodeCheckbox().click();
        String enteredText = premiumNumberURLGenerator.getDisplayedNumberOnFE().getEnteredText();
        return enteredText;
    }

    public double clickGenerateLinkButtonRegularFlow() {
        double price;
        if(isElementPresent(premiumNumberURLGenerator.getButtonGenerateLink())) {
            waitUntilElementWillBeClickable(premiumNumberURLGenerator.getButtonGenerateLink());
            premiumNumberURLGenerator.getButtonGenerateLink().click();
        }
        else {
            waitUntilElementWillBeClickable(premiumNumberURLGenerator.getButtonSave());
            premiumNumberURLGenerator.getButtonSave().click();
        }
        price = Double.parseDouble(localNumberURLGenerator.getPriceOverride().getEnteredText());
        return price;
    }

    public double getOldPrice() {
        waitUntilElementAppeared(localNumberURLGenerator.getOldPriceField());
        double price = Double.parseDouble(localNumberURLGenerator.getOldPriceField().getAttribute("placeholder").substring(1));
        return price;
    }

    public double clickGenerateLinkButtonPremiumFlow() {
        double price;
        if(isElementPresent(premiumNumberURLGenerator.getButtonGenerateLink())) {
            waitUntilElementWillBeClickable(premiumNumberURLGenerator.getButtonGenerateLink());
            premiumNumberURLGenerator.getButtonGenerateLink().click();
        }
        else {
            waitUntilElementWillBeClickable(premiumNumberURLGenerator.getButtonSave());
            premiumNumberURLGenerator.getButtonSave().click();
        }
        price = Double.parseDouble(premiumNumberURLGenerator.getPayTodayPrice().getText().replaceAll("\\$", ""));
        return price;
    }


    public void generateLinkWithChangeDisplayedInfo(String priceOverride, String displayedName) {
        waitUntilElementAppeared(localNumberURLGenerator.getButtonGenerateLink());
        type(localNumberURLGenerator.getPriceOverride(), priceOverride);
        type(localNumberURLGenerator.getDisplayedNumberOnFE(), displayedName);
        waitUntilElementWillBeClickable(localNumberURLGenerator.getButtonGenerateLink());
        localNumberURLGenerator.getButtonGenerateLink().click();
    }

    public String getGeneratedLink(int indexLink) {
        try {
            waitUntilElementAppeared(listGeneratedURL.getListTd().get(0));
            waiting2seconds();
            return listGeneratedURL.getListTd().get(indexLink * 5).getText();
        }
        catch(Exception e) {
            return null;
        }
    }

    public void checkingInvisibleCreateNewURL() {
        softAssert.assertFalse(isElementInvisible(listGeneratedURL.getButtonCreateNewURL()));
        softAssert.assertAll();
    }

    public String replaceDisplayNameFieldToLetters(String displayName) {
        String newName = displayName.replace("2", "A").replaceAll("5", "k").replaceAll("9","Z");
        return newName;
    }

    public int returnIndexLastGeneratedLink() {
        waiting2seconds();
        int index = listGeneratedURL.getListOfLinks().size();
        if (index == 0)
            return 0;
        else return index - 1;
    }

    public String returnCellOfTable(String headingColumn, int indexOfListLinks) {
        int indexColumn = 0;
        for (int i = 0; i < listGeneratedURL.getListColumnHeader().size(); i++) {
            if(listGeneratedURL.getListColumnHeader().get(i).getText().equals(headingColumn)){
                indexColumn = i;
                break;
            }
        }
        int counter = 0;
        for (int i = indexColumn; i < listGeneratedURL.getListTd().size(); i += listGeneratedURL.getListColumnHeader().size()) {
            if(counter == indexOfListLinks) {
                return listGeneratedURL.getListTd().get(i).getText();
            }
            counter ++;
        }
        return listGeneratedURL.getListTd().get(indexColumn).getText();
    }

    public void checkingStatusComplete(String headingColumn, int indexOfListLinks) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        softAssert.assertEquals(returnCellOfTable(headingColumn, indexOfListLinks), "Completed");
        softAssert.assertAll();
    }

    public void checkingStatusCompleteOfAllLinks(String headingColumn) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        for (int i = listGeneratedURL.getListOfLinks().size() - 1; i >= 0 ; i--) {
            softAssert.assertEquals(returnCellOfTable(headingColumn, i), "Completed");
        }
        softAssert.assertAll();
    }

    public void checkingStatusDeactivate(String headingColumn, int indexOfListLinks) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        softAssert.assertEquals(returnCellOfTable(headingColumn, indexOfListLinks), "Deactivated");
        softAssert.assertAll();
    }

    public void checkingStatusDeactivateOfAllLinks(String headingColumn) {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        if (listGeneratedURL.getListOfLinks().size() > 1) {
            for (int i = listGeneratedURL.getListOfLinks().size() - 2; i >= 0; i--) {
                softAssert.assertEquals(returnCellOfTable(headingColumn, i), "Deactivated");
            }
            softAssert.assertAll();
            return;
        }
        softAssert.assertTrue(listGeneratedURL.getListOfLinks().size() > 1, "error");
        softAssert.assertAll();
    }

    public void checkingDisabledGenerateLinkButtonPremiumFlow() {
        softAssert.assertTrue(isElementContainsAttributeValue(premiumNumberURLGenerator.getButtonGenerateLink(), "class", "v-btn--disabled"),
                "button is clickable");
        softAssert.assertAll();
    }

    public void checkingErrorMessageNumberIsSold() {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        waiting2seconds();
        softAssert.assertEquals(premiumNumberURLGenerator.getListOfErrorMessage().get(1).getText(), "Phone already sold!");
        softAssert.assertAll();
    }

    public void checkingErrorMessageNumberIsLicenced() {
        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
        waiting2seconds();
        softAssert.assertEquals(premiumNumberURLGenerator.getListOfErrorMessage().get(1).getText(), "Phone already licensed!");
        softAssert.assertAll();
    }







}
