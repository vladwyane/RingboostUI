package blocks.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by bigdrop on 8/1/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class LocalNumberURLGenerator extends HtmlElement{

    @FindBy(xpath = "//div[contains(text(),'generate link')]")
    private WebElement buttonGenerateLink;

    @FindBy(css = " ")
    private WebElement labelPhoneNumber;

    @FindBy(xpath = "//input[@aria-label='Price']")
    private TextInput priceOverride;

    @FindBy(xpath = "//input[@aria-label='Displayed phone number']")
    private TextInput displayedNumberOnFE;

    @FindBy(xpath = "//label[contains(text(), 'Show promocode on checkout page?')]")
    private WebElement showPromoCodeCheckbox;
}
