package blocks;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "drag-drop-block"))
public class DragAndDropBlock extends HtmlElement {

    @Name("Drop down states")
    @FindBys( {@FindBy(css = ".dropdown-menu li")} )
    private List<WebElement> listStates;

    @FindBy(css = "div[name='pattern-select']")
    private WebElement selectState;
}
