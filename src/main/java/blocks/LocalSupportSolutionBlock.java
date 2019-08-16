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

/**
 * Created by bigdrop on 6/14/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "support-solutions-list"))
public class LocalSupportSolutionBlock extends HtmlElement {

    @Name("ArrayList of Titles Support Solution Items")
    @FindBys( {@FindBy(css = ".support-solutions-item .item-title")} )
    public List<WebElement> listTitlesSolutionItems;

    @Name("ArrayList of Buttons Support Solution Items")
    @FindBys( {@FindBy(css = ".support-solutions-item button")} )
    public List<WebElement> listButtonsSolutionItems;

    @Name("ArrayList of Prices Support Solution Items")
    @FindBys( {@FindBy(css = ".support-solutions-item .price")} )
    public List<WebElement> listPricesSolutionItems;

    @FindBy(css = ".to-checkout a")
    private WebElement linkToCheckout;
}
