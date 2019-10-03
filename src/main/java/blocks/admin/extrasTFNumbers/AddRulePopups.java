package blocks.admin.extrasTFNumbers;

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
 * Created by bigdrop on 9/12/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class AddRulePopups extends HtmlElement {

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='name']")
    private TextInput nameField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='value']")
    private TextInput valueField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='description']")
    private TextInput descriptionField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@aria-label='Discount']")
    private TextInput discountField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='duration']")
    private TextInput durationField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='additional_text']")
    private TextInput additionalTextField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@aria-label='Select country']" +
            "/ancestor::div[@class='v-select__selections']")
    private WebElement selectOfCounties;

    @Name("List of country")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfCountry;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='additional_cost']")
    private TextInput additionalCoastField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='activation_fee']")
    private TextInput activationFeeField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='price']")
    private TextInput priceField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='price_per_minute']")
    private TextInput pricePerMinuteField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//input[@name='number']")
    private TextInput numberField;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//label[contains(text(), 'Is popular')]")
    private WebElement isPopularCheckbox;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//label[contains(text(), 'Is active')]")
    private WebElement isActiveCheckbox;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//label[contains(text(), 'Contact us')]")
    private WebElement contactUsCheckbox;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//div[contains(text(), 'Save')]")
    private WebElement buttonSave;

    @FindBy(css = ".v-overlay")
    private WebElement overlay;

    @Name("List of error message")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//span[contains(@class, 'error-message')]")} )
    private List<WebElement> listOfErrorMessage;

}