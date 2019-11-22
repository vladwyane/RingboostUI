package blocks.admin.orders;

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
 * Created by bigdrop on 11/20/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class OrdersTable extends HtmlElement {

    @Name("List of tabs")
    @FindBys( {@FindBy(css = ".v-tabs__div a")} )
    private List<WebElement> listOfTabs;

    @Name("List of active actions")
    @FindBys( {@FindBy(css = "td .v-btn--icon")} )
    private List<WebElement> listOfActions;

    @Name("List of column header")
    @FindBys( {@FindBy(xpath = "//th[@role='columnheader']")} )
    private List<WebElement> listColumnHeader;

    @Name("List of Td")
    @FindBys( {@FindBy(css = "tbody tr td")} )
    private List<WebElement> listTd;

    @FindBy(css = ".v-alert.success")
    private WebElement successAlert;
}
