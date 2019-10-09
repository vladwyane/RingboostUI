package blocks.admin.areaCodes;

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
 * Created by bigdrop on 10/7/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class AreaCodePopup extends HtmlElement {

    @FindBy(xpath = "//input[@aria-label='Area Code(s)']")
    private TextInput areaCodesField;

    @FindBy(xpath = "//input[@name='multiplier']")
    private TextInput multiplierField;

    @FindBy(xpath = "//input[@name='name']")
    private TextInput groupNameField;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    private WebElement buttonSave;

    @Name("List of error message")
    @FindBys( {@FindBy(css = ".error-message")} )
    private List<WebElement> listOfErrorMessage;
}
