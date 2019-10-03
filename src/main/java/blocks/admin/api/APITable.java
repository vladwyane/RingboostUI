package blocks.admin.api;

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
 * Created by bigdrop on 8/28/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class APITable extends HtmlElement {

    @Name("List of active actions")
    @FindBys( {@FindBy(xpath = "//td/button[contains(@class, 'v-btn') and not(contains(@disabled, 'disabled'))]")} )
    private List<WebElement> listOfActions;

    @Name("List of column header")
    @FindBys( {@FindBy(xpath = "//th")} )
    private List<WebElement> listColumnHeader;

    @Name("List of cells")
    @FindBys( {@FindBy(css = "tbody tr td")} )
    private List<WebElement> listTd;
}
