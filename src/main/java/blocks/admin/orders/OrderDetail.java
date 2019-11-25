package blocks.admin.orders;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * Created by bigdrop on 11/20/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class OrderDetail extends HtmlElement {

    @Name("List of tabs")
    @FindBys( {@FindBy(css = ".v-tabs__div a")} )
    private List<WebElement> listOfTabs;

    @Name("List of tabs")
    @FindBys( {@FindBy(css = ".v-subheader")} )
    private List<WebElement> listOfSubHeaders;

    @FindBy(xpath = "//div[contains(text(), 'Public ID')]/following::div[1]/strong")
    private WebElement publicID;

    @FindBy(xpath = "//div[contains(text(), 'Status')]/following::div[1]/strong")
    private WebElement status;

    @FindBy(xpath = "//div[contains(text(), 'Customer price')]/following::div[1]/strong")
    private WebElement customerPrice;

    @FindBy(xpath = "//div[contains(text(), 'Subscription price')]/following::div[1]/strong")
    private WebElement subscriptionPrice;

    @FindBy(xpath = "//div[contains(text(), 'Price plan')]/following::div[1]/strong")
    private WebElement pricePlan;

    @FindBy(xpath = "//div[contains(text(), 'Price plan duration')]/following::div[1]/strong")
    private WebElement pricePlanDuration;

    @FindBy(xpath = "//div[contains(text(), 'Price plan additional cost')]/following::div[1]/strong")
    private WebElement pricePlanAdditionalCost;

    @FindBy(xpath = "//div[contains(text(), 'Price plan activation fee')]/following::div[1]/strong")
    private WebElement pricePlanActivationFee;

    @FindBy(xpath = "//div[contains(text(), 'Price subscription payment')]/following::div[1]/strong")
    private WebElement priceSubscriptionPayment;

    @FindBy(xpath = "//div[contains(text(), 'Phone')]/following::div[1]/strong")
    private WebElement phone;

    @FindBy(xpath = "//div[contains(text(), 'Vanity')]/following::div[1]/strong")
    private WebElement vanity;

    @FindBy(xpath = "//div[contains(text(), 'Category')]/following::div[1]/strong")
    private WebElement category;

    @FindBy(xpath = "//div[contains(text(), 'Phone type')]/following::div[1]/strong")
    private WebElement phoneType;

    @FindBy(xpath = "//div[contains(text(), 'Flow type')]/following::div[1]/strong")
    private WebElement flowType;

    @FindBy(xpath = "//div[contains(text(), 'Sub type')]/following::div[1]/strong")
    private WebElement subType;

    @FindBy(xpath = "//div[contains(text(), 'Ring To Phone Number')]/following::div[1]/strong")
    private WebElement ringToPhoneNumber;

    @FindBy(xpath = "//div[contains(text(), 'Bearer')]/following::div[1]/strong")
    private WebElement bearer;

    @FindBy(xpath = "//div[contains(text(), 'API partner')]/following::div[1]/strong")
    private WebElement apiPartner;

    @FindBy(xpath = "//div[contains(text(), 'API partner commission %')]/following::div[1]/strong")
    private WebElement apiPartnerCommission;

    @FindBy(xpath = "//div[contains(text(), 'Owner')]/following::div[1]/strong")
    private WebElement owner;

    @FindBy(xpath = "//div[contains(text(), 'Owner commission %')]/following::div[1]/strong")
    private WebElement ownerCommission;

    @FindBy(xpath = "//div[contains(text(), 'Carrier')]/following::div[1]/strong")
    private WebElement carrier;

    @FindBy(xpath = "//div[contains(text(), 'Promo Code')]/following::div[1]/strong")
    private WebElement promoCode;

    @FindBy(xpath = "//div[contains(text(), 'Discount value')]/following::div[1]/strong")
    private WebElement discountValue;

    @FindBy(xpath = "//div[contains(text(), 'Nationwide')]/following::div[1]/strong")
    private WebElement nationwide;

    @FindBy(xpath = "//div[contains(text(), 'Regional')]/following::div[1]/strong")
    private WebElement regional;

    @FindBy(xpath = "//div[contains(text(), 'Auction')]/following::div[1]/strong")
    private WebElement auction;

    @FindBy(xpath = "//div[contains(text(), 'Feature')]/following::div[1]/strong")
    private WebElement feature;

    @FindBy(xpath = "//div[contains(text(), 'Premium')]/following::div[1]/strong")
    private WebElement premium;

    @FindBy(xpath = "//div[contains(text(), 'Country ISO')]/following::div[1]/strong")
    private WebElement countryISO;

    @FindBy(xpath = "//div[contains(text(), 'Completed at')]/following::div[1]/strong")
    private WebElement completedAt;

    @FindBy(xpath = "//div[contains(text(), 'Failed at')]/following::div[1]/strong")
    private WebElement failedAt;

}
