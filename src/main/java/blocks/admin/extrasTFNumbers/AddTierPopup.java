package blocks.admin.extrasTFNumbers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by bigdrop on 9/12/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class AddTierPopup extends HtmlElement {

    @FindBy(xpath = "//div[contains(text(),'Save')]")
    private WebElement buttonSave;

    @FindBy(css = "input[name='']")
    private TextInput tierName;

    @FindBy(css = "input[name='']")
    private TextInput tierValue;

}