package pages.front;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 10/7/2019.
 */
public class ContactUsPage extends BasePage {

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Contact Us Page")
    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("contactUs.url"));
    }

    @FindBy(css= "h1")
    private WebElement titleH1;

    @FindBy(css= ".contacts-us-wrap form")
    private WebElement contactUsForm;

    @Step("Checking Correct Redirect to Contact Us Page")
    public void checkingCorrectRedirectToContactUsPage() throws InterruptedException {
        waiting2seconds();
        softAssert.assertEquals(driver.getCurrentUrl(), ConfigProperties.getProperty("contactUs.url"));
        softAssert.assertEquals(titleH1.getText(), "We Want to Hear from You");
        softAssert.assertTrue(isElementPresent(contactUsForm), "Contact us form is absent");
        softAssert.assertAll();
    }

}
