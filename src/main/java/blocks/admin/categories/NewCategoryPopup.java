package blocks.admin.categories;

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
 * Created by bigdrop on 10/31/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class NewCategoryPopup extends HtmlElement {

    @FindBy(xpath = "//input[@name='name']")
    private TextInput nameField;

    @FindBy(xpath = "//input[@name='slug']")
    private TextInput slugField;

    @FindBy(xpath = "//input[@name='order']")
    private TextInput orderField;

    @FindBy(xpath = "//div[contains(text(), 'Select phone type')]/ancestor::div[@class='v-input__control']//div[@class='v-select__slot']")
    private WebElement selectPhoneType;

    @Name("List of phone types")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfPhoneTypes;

    @FindBy(xpath = "//div[contains(text(), 'Select tier')]/ancestor::div[@class='v-input__control']//div[@class='v-select__slot']")
    private WebElement selectTier;

    @Name("List of phone tiers")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfTiers;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonAddCategory;

    @Name("List of error message")
    @FindBys( {@FindBy(css = ".error-message")} )
    private List<WebElement> listOfErrorMessage;
}
