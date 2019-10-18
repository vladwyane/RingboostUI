package blocks.admin.owners;

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
 * Created by bigdrop on 10/11/2019.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class NewOwnerPopup extends HtmlElement {

    @FindBy(xpath = "//input[@name='company']")
    private TextInput companyField;

    @FindBy(xpath = "//input[@name='name']")
    private TextInput contactNameField;

    @FindBy(xpath = "//input[@name='phone']")
    private TextInput phoneField;

    @FindBy(xpath = "//input[@name='email']")
    private TextInput emailField;

    @FindBy(xpath = "//input[@name='commission']")
    private TextInput commissionField;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    private WebElement buttonSave;

    @Name("List of error message")
    @FindBys( {@FindBy(css = ".error-message")} )
    private List<WebElement> listOfErrorMessage;

}
