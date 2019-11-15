package blocks.admin.customers;

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
 * Created by bigdrop on 11/14/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class NewCustomerPopup extends HtmlElement {

    @FindBy(xpath = "//input[@aria-label='First Name']")
    private TextInput firstNameField;

    @FindBy(xpath = "//input[@aria-label='Last Name']")
    private TextInput lastNameField;

    @FindBy(xpath = "//input[@aria-label='Email']")
    private TextInput emailField;

    @FindBy(xpath = "//input[@aria-label='Company']")
    private TextInput companyField;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    private WebElement buttonSave;

    @FindBy(xpath = "//div[contains(text(), 'Save info')]")
    private WebElement buttonSaveInfo;

    @Name("List of error message")
    @FindBys( {@FindBy(css = ".error-message")} )
    private List<WebElement> listOfErrorMessage;
}
