package pages;

import blocks.SearchBlock;
import org.openqa.selenium.WebDriver;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 3/14/2019.
 */
public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    SearchBlock searchBlock;

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("home.url"));
    }

    public void searchTollFreeNumbers(String request) {
        type(searchBlock.getTollFreeSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }

    public void searchLocalNumbers(String request) {
        searchBlock.getSwitcherLocalPart().click();
        type(searchBlock.getLocalSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }



    public void switchLocalField() {
        searchBlock.getSwitcherLocalPart().click();
    }

    public void switchTollFreeField() {
        searchBlock.getSwitcherTollFreePart().click();
    }


}
