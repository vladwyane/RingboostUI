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

    @FindBy(xpath = "//label[contains(text(), 'Public ID')]/following::input[1]")
    private WebElement publicID;

    @FindBy(xpath = "//label[contains(text(), 'Status')]/following::input[1]")
    private WebElement status;

    @FindBy(xpath = "//label[contains(text(), 'Country ISO')]/following::input[1]")
    private WebElement countryISO;

    @FindBy(xpath = "//label[contains(text(), 'State')]/following::input[1]")
    private WebElement state;

    @FindBy(xpath = "//label[contains(text(), 'Local Area')]/following::input[1]")
    private WebElement localArea;

    @FindBy(xpath = "//label[contains(text(), 'Carrier')]/following::input[1]")
    private WebElement carrier;

    @FindBy(xpath = "//label[contains(text(), 'Completed at')]/following::input[1]")
    private WebElement timeStamps;

    @FindBy(xpath = "//label[contains(text(), 'Phone')]/following::input[1]")
    private WebElement phoneName;

    @FindBy(xpath = "//label[contains(text(), 'Vanity')]/following::input[1]")
    private WebElement vanityPhoneName;

    @FindBy(xpath = "//label[contains(text(), 'Category')]/following::input[1]")
    private WebElement category;

    @FindBy(xpath = "//label[contains(text(), 'Phone type')]/following::input[1]")
    private WebElement phoneType;

    @FindBy(xpath = "//label[contains(text(), 'Flow type')]/following::input[1]")
    private WebElement flowType;

    @FindBy(xpath = "//label[contains(text(), 'Sub type')]/following::input[1]")
    private WebElement subType;

    @FindBy(xpath = "//label[contains(text(), 'Bearer')]/following::input[1]")
    private WebElement bearer;

    @FindBy(xpath = "//label[contains(text(), 'Payment Price')]/following::input[1]")
    private WebElement paymentPrice;

    @FindBy(xpath = "//label[contains(text(), 'Customer Price')]/following::input[1]")
    private WebElement customerPrice;

    @FindBy(xpath = "//label[contains(text(), 'Customer Subscription Price')]/following::input[1]")
    private WebElement customerSubscriptionPrice;

    @FindBy(xpath = "//label[contains(text(), 'Pay Today')]/following::input[1]")
    private WebElement payToday;

    @FindBy(xpath = "//label[contains(text(), 'Phone Upsell Name')]/following::input[1]")
    private WebElement phoneUpsellName;

    @FindBy(xpath = "//label[contains(text(), 'Phone Upsell Price')]/following::input[1]")
    private WebElement phoneUpsellPrice;

    @FindBy(xpath = "//label[contains(text(), 'Price Plan')]/following::input[1]")
    private WebElement pricePlan;

    @FindBy(xpath = "//label[contains(text(), 'Price subscription payment')]/following::input[1]")
    private WebElement priceSubscriptionPayment;

    @FindBy(xpath = "//label[contains(text(), 'Price Plan Duration')]/following::input[1]")
    private WebElement pricePlanDuration;

    @FindBy(xpath = "//label[contains(text(), 'Price Plan Discount')]/following::input[1]")
    private WebElement pricePlanDiscount;

    @FindBy(xpath = "//label[contains(text(), 'Price Plan Additional Cost')]/following::input[1]")
    private WebElement pricePlanAdditionalCost;

    @FindBy(xpath = "//label[contains(text(), 'Price Plan Activation Fee')]/following::input[1]")
    private WebElement pricePlanActivationFee;

    @FindBy(xpath = "//label[contains(text(), 'Subscription price')]/following::input[1]")
    private WebElement subscriptionPrice;

    @FindBy(xpath = "//label[contains(text(), 'Coupon')]/following::input[1]")
    private WebElement promoCodeName;

    @FindBy(xpath = "//label[contains(text(), 'Discount value')]/following::input[1]")
    private WebElement discountValue;

    @FindBy(xpath = "//label[contains(text(), 'Discount type')]/following::input[1]")
    private WebElement discountType;

    @FindBy(xpath = "//label[contains(text(), 'API partner')]/following::input[1]")
    private WebElement apiPartner;

    @FindBy(xpath = "//label[contains(text(), 'API partner commission')]/following::input[1]")
    private WebElement apiPartnerCommission;

    @FindBy(xpath = "//label[contains(text(), 'Owner')]/following::input[1]")
    private WebElement owner;

    @FindBy(xpath = "//label[contains(text(), 'Owner commission')]/following::input[1]")
    private WebElement ownerCommission;

    @FindBy(xpath = "//label[contains(text(), 'Minutes')]/following::input[1]")
    private WebElement minutes;

    @FindBy(xpath = "//label[contains(text(), 'Minutes Price')]/following::input[1]")
    private WebElement minutesPrice;

    @FindBy(xpath = "//label[contains(text(), 'Price Per Minute')]/following::input[1]")
    private WebElement pricePerMinute;

    @FindBy(xpath = "//label[contains(text(), 'Ring To Phone Number')]/following::input[1]")
    private WebElement ringToNumber;

    @FindBy(xpath = "//label[contains(text(), 'Zendesk Task ID')]/following::input[1]")
    private WebElement zendeskId;

    @FindBy(xpath = "//label[contains(text(), 'Nationwide')]/following::input[1]")
    private WebElement isNationwide;

    @FindBy(xpath = "//label[contains(text(), 'Regional')]/following::input[1]")
    private WebElement isRegional;

    @FindBy(xpath = "//label[contains(text(), 'Feature')]/following::input[1]")
    private WebElement isFeature;

    @FindBy(xpath = "//label[contains(text(), 'Auction')]/following::input[1]")
    private WebElement isAuction;

    @FindBy(xpath = "//label[contains(text(), 'Premium')]/following::input[1]")
    private WebElement isPremium;

}
