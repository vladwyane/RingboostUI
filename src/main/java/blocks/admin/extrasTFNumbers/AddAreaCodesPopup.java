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
public class AddAreaCodesPopup extends HtmlElement{

    @FindBy(css = "input[name='']")
    private TextInput codes;

    @FindBy(css = "input[name='']")
    private TextInput multiplier;

    @FindBy(css = "input[name='']")
    private TextInput groupName;
    
    @FindBy(xpath = "//div[contains(text(),'Save')]")
    private WebElement buttonSave;
}
