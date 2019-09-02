package pages.admin;

import blocks.admin.APITable;
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
}
