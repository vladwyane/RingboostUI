package blocks.front;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "checkout-sidebar-wrapper"))
public class CheckoutSidebar extends HtmlElement {

    @FindBy(xpath= "//*[contains(text(),'Recurring Monthly')]/following::div[1]")
    private WebElement priceRecurringMonthly;

    @FindBy(xpath= "//*[contains(text(),'Total Due Today')]/following::div[1]")
    private WebElement priceTotalDueToday;

    @FindBy(xpath= "//*[contains(text(),'Pay Today')]/following::div[1]")
    private WebElement pricePayToday;

    @FindBy(xpath= "//*[contains(text(),'Promo Code')]/following::div[1]")
    private WebElement discountFromPromoCode;

    @FindBy(xpath= "//*[contains(text(),'Take')]/following::div[1]")
    private WebElement discountPriceAmountAreaCodes;

    @FindBy(css= ".switcher-title")
    private WebElement switchTitle;

    @FindBy(css= ".have-promocode")
    private WebElement linkHavePromoCode;

    @FindBy(css= ".discount")
    private WebElement priceAfterAppliedPromoCode;

    @FindBy(css= ".remove-promo-code")
    private WebElement linkRemovePromoCode;

    @FindBy(css= ".promocode-input")
    private TextInput inputPromoCode;

    @FindBy(xpath= "//button[contains(text(),'Apply')]")
    private WebElement buttonApply;


}
