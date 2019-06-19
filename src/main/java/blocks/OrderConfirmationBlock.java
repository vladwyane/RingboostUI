package blocks;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "order-confirmation"))
public class OrderConfirmationBlock extends HtmlElement {

    @FindBy(xpath= "//a[contains(text(),'Order Details')]")
    private WebElement linkOrderDetails;

    @FindBy(xpath= "//*[contains(text(),'Recurring Monthly')]/following::div[1]")
    private WebElement priceRecurringMonthly;

    @FindBy(xpath= "//*[contains(text(),'Due Today')]/following::div[1]")
    private WebElement priceTotalDueToday;

    @FindBy(xpath= "//*[contains(text(),'Pay Today')]/following::div[1]")
    private WebElement pricePayToday;

    @FindBy(css= ".discount")
    private WebElement priceAfterAppliedPromoCode;

    @FindBy(css= ".order-title")
    private WebElement orderTitle;

}
