package pages;


import blocks.SearchBlock;
import org.openqa.selenium.WebDriver;
import utils.ConfigProperties;

public class TollFreeIndexPage extends BasePage {

    public TollFreeIndexPage(WebDriver driver) {
        super(driver);
    }

    private SearchBlock searchBlock;

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("tollFreeIndex.url"));
    }

    public void openTollFreeIndexPageFromMainNav() {
        waitUntilElementWillBeClickable( headerBlock.getTollFreeLinInMainNav());
        headerBlock.getTollFreeLinInMainNav().click();
    }

    public void searchTollFreeNumber(String request) {
        waitUntilTextInElementAppear(searchBlock.getTitleH1(), "Find Your Toll-Free Number");
        type(searchBlock.getTollFreeSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }
}
