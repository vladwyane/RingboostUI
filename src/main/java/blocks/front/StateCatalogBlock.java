package blocks.front;

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

/**
 * Created by bigdrop on 7/12/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "states-catalog"))
public class StateCatalogBlock extends HtmlElement {

    @Name("ArrayList of letters options")
    @FindBys( {@FindBy(css = ".options li")} )
    public List<WebElement> listLettersOptions;

    @Name("ArrayList of states")
    @FindBys( {@FindBy(css = ".all-states li a")} )
    public List<WebElement> listStates;
}
