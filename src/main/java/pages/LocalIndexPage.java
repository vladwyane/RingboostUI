package pages;

import blocks.SearchBlock;
import org.openqa.selenium.WebDriver;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 6/14/2019.
 */
public class LocalIndexPage extends BasePage {

    public LocalIndexPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("localIndex.url"));
    }

    private SearchBlock searchBlock;

    public void searchLocalNumbers(String request) {
        waitUntilTextInElementAppear(searchBlock.getTitleH1(), "Buy a Local Phone Number");
        type(searchBlock.getLocalSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }
}
