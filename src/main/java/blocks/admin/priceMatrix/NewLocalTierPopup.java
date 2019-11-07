package blocks.admin.priceMatrix;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

/**
 * Created by bigdrop on 10/21/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class NewLocalTierPopup extends HtmlElement {

    @FindBy(xpath = "//input[@name='tier']")
    private TextInput tierField;

    @FindBy(xpath = "//input[@aria-label='Premium']")
    private TextInput premiumPriceField;

    @FindBy(xpath = "//input[@aria-label='Pattern']")
    private TextInput patternPriceField;

    @FindBy(xpath = "//input[@aria-label='Random']")
    private TextInput randomPriceField;

    @Name("List of call for price checkbox")
    @FindBys( {@FindBy(xpath = "//label[contains(text(), 'call for price')]")} )
    private List<WebElement> listCallForPriceCheckbox;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    private WebElement buttonSave;

    @FindBy(xpath = "//div[contains(text(), 'Phone categories')]/ancestor::div[@class='v-input__control']//div[@class='v-select__slot']")
    private WebElement selectCategory;

    @Name("List of error message")
    @FindBys( {@FindBy(css = ".error-message")} )
    private List<WebElement> listOfErrorMessage;
}
