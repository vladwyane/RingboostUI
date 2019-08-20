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
 * Created by bigdrop on 8/20/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class NewCarrierPopup extends HtmlElement {

    @FindBy(xpath = "//input[@aria-label='Carrier']")
    private TextInput carrierName;

    @FindBy(xpath = "//input[@aria-label='MRC']")
    private TextInput mrcField;

    @FindBy(xpath = "//input[@aria-label='NRC']")
    private TextInput nrcField;

    @FindBy(xpath = "//input[@aria-label='Per-Minute Usage']")
    private TextInput perMinuteField;

    @FindBy(xpath = "//input[@aria-label='Port-In Fees']")
    private TextInput portInFeesField;

    @FindBy(xpath = "//input[@aria-label='Port-Out Fees']")
    private TextInput portOutFeesField;

    @FindBy(xpath = "//textarea[@aria-label='Notes']")
    private TextInput notesField;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    private WebElement buttonSave;

}
