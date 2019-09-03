package pages.admin;

import blocks.admin.APITable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 8/28/2019.
 */
public class APIListingPage extends BasePage {

    public APIListingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("adminAPI.url"));
    }

    APITable apiTable;

    public void clickEditApi(String apiName) {
        int indexColumn = 0;
        for (int i = 0; i < apiTable.getListColumnHeader().size(); i++) {
            if(apiTable.getListColumnHeader().get(i).getText().equals("Name")){
                indexColumn = i;
                break;
            }
        }
        int counter = 0;
        for (int i = indexColumn; i < apiTable.getListTd().size(); i += apiTable.getListColumnHeader().size() - 1) {
            String rt = apiTable.getListTd().get(i).getText();
            if(apiTable.getListTd().get(i).getText().equals(apiName)) {
                break;
            }
            counter ++;
        }
        apiTable.getListOfActions().get(counter * 3).click();
    }

    public void fillEditPopup(String carrier, boolean calculateDIDs,
                              boolean idPremium, boolean isNationwide, boolean isRegional, String typeTollFree) {
/*        waitUntilElementAppeared(premiumNumberURLGenerator.getButtonGenerateLink());
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
        return enteredText;*/
    }
}
