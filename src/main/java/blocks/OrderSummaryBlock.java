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
@Block(@FindBy(className = "order-summary"))
public class OrderSummaryBlock extends HtmlElement {

    @FindBy(xpath= "//*[contains(text(),'Market Areas')]/following::div[1]")
    private WebElement priceNumber;

    @FindBy(xpath= "//div[contains(text(),'Monthly Minutes')]/following::div[1]")
    private WebElement priceMonthlyMinutes;

    @FindBy(xpath= "//*[contains(text(),'Selected Plan')]/following::div[1]")
    private WebElement discountPriceSelectedPlan;

    @FindBy(xpath= "//*[contains(text(),'Recurring Monthly')]/following::div[1]")
    private WebElement priceRecurringMonthly;

    @FindBy(css= "h3")
    private WebElement titleOrderSummary;

    @FindBy(css= ".proceed button")
    private WebElement buttonProceedToCheckout;
}
