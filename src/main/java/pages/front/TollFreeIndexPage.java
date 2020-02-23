package pages.front;


import blocks.front.SearchBlock;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;
import utils.ConfigProperties;

public class TollFreeIndexPage extends BasePage {

    public TollFreeIndexPage(WebDriver driver) {
        super(driver);
    }

    private SearchBlock searchBlock;

    @Step("Open Toll-Free Index Page")
    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("tollFreeIndex.url"));
    }

    @Step("Open Toll-Free Index Page from Main Nav")
    public void openTollFreeIndexPageFromMainNav() {
        waitUntilElementWillBeClickable( headerBlock.getTollFreeLinInMainNav());
        headerBlock.getTollFreeLinInMainNav().click();
    }

    @Step("Search toll-free number with request: {0}")
    public void searchTollFreeNumbers(String request) {
        waitUntilTextInElementAppear(searchBlock.getTitleH1(), "Find Your Toll-Free Number");
        type(searchBlock.getTollFreeSearchField(), request);
        searchBlock.getButtonFindNumber().click();
    }
}
