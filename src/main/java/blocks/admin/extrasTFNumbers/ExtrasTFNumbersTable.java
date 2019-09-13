package blocks.admin.extrasTFNumbers;

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
 * Created by bigdrop on 9/12/2019.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class ExtrasTFNumbersTable extends HtmlElement {

    @Name("List of tabs")
    @FindBys( {@FindBy(css = ".v-tabs__div a")} )
    private List<WebElement> listOfTabs;

    @FindBy(css = " ")
    private WebElement addTierButton;

    @FindBy(css = " ")
    private WebElement addRuleButton;

    @Name("List of states")
    @FindBys( {@FindBy(css = ".v-tabs__div a")} )
    private List<WebElement> listOfStates;

    @FindBy(css = " ")
    private WebElement addAreaCodes;
}
