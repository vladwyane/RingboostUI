package blocks;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by bigdrop on 5/20/2019.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "small-search-block"))
public class SmallSearchBlock extends HtmlElement {

    @FindBy(css = ".search-input")
    private TextInput tollFreeSearchField;

    @FindBy(css= "input.button.filled")
    private Button buttonFindNumber;

    @FindBy(css= "h1")
    private WebElement titleH1;
}
