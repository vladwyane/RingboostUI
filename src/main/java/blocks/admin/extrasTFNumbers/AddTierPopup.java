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

    @FindBy(xpath = "//span[contains(text(),'New Price Tier')]/ancestor::form//div[contains(text(),'Save')]")
    private WebElement buttonSave;

    @FindBy(xpath = "//span[contains(text(),'New Price Tier')]/ancestor::form//input[@name='name']")
    private TextInput tierName;

    @FindBy(xpath= "//span[contains(text(),'New Price Tier')]/ancestor::form//input[@name='value']")
    private TextInput tierValue;

}