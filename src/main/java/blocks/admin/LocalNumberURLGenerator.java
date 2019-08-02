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
 * Created by bigdrop on 8/1/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = " "))
public class LocalNumberURLGenerator extends HtmlElement{

    @FindBy(css = " ")
    private WebElement buttonGenerateLink;

    @FindBy(css = " ")
    private WebElement labelPhoneNumber;

    @FindBy(css = " ")
    private TextInput priceOverride;

    @FindBy(css = " ")
    private TextInput displayedNumberOnFE;

    @FindBy(css = " ")
    private WebElement showPromoCodeCheckbox;
}
