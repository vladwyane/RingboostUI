package blocks.admin.patterns;

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
public class NewPatternsPopup extends HtmlElement {

    @FindBy(xpath = "//input[@name='pattern']")
    private TextInput patternNameField;

    @FindBy(xpath = "//div[contains(text(), 'Select pattern type')]/ancestor::div[@class='v-input__control']//div[@class='v-select__slot']")
    private WebElement selectPatternType;

    @Name("List of patterns types")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfPatternType;

    @FindBy(xpath = "//div[contains(text(), 'Select flow type')]/ancestor::div[@class='v-input__control']//div[@class='v-select__slot']")
    private WebElement selectFlowType;

    @Name("List of patterns types")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfFlowType;

    @FindBy(xpath = "//div[contains(text(), 'Select category')]/ancestor::div[@class='v-input__control']//div[@class='v-select__slot']")
    private WebElement selectOfCategory;

    @Name("List of category")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfCategory;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Add Pattern/Vanity')]")
    private WebElement buttonAddPattern;

    @Name("List of error message")
    @FindBys( {@FindBy(css = ".error-message")} )
    private List<WebElement> listOfErrorMessage;
}
