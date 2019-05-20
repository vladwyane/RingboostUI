package pages;


import blocks.SearchBlock;
import org.openqa.selenium.WebDriver;

public class TollFreeIndexPage extends BasePage {

    public TollFreeIndexPage(WebDriver driver) {
        super(driver);
    }

    private SearchBlock searchBlock;

    @Override
    public void open() {

    }

    public void openTollFreeIndexPageFromMainNav() {
        waitUntilElementWillBeClickable( headerBlock.getTollFreeLinInMainNav());
        headerBlock.getTollFreeLinInMainNav().click();
    }

    public void searchTollFreeNumber(String request) {
        waitUntilTextInElementAppear(searchBlock.getTitleH1(), "Toll-Free Vanity Numbers");
        type(searchBlock.getTollFreeSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }
}
