package blocks.admin.areaCodes;

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
 * Created by bigdrop on 10/7/2019.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class AreaCodesTable extends HtmlElement {

    @FindBy(xpath = "//div[contains(text(),'Add Area Code(s)')]")
    private WebElement addAreaCodesButton;

    @Name("List of active actions")
    @FindBys( {@FindBy(xpath = "//td/button[contains(@class, 'v-btn--icon') and not(contains(@disabled, 'disabled'))]")} )
    private List<WebElement> listOfActions;

    @Name("List of cell in the table")
    @FindBys( {@FindBy(css = "tbody tr td")} )
    private List<WebElement> listTd;

    @Name("List of generated URL")
    @FindBys( {@FindBy(css = "tbody tr")} )
    private List<WebElement> listOfLinks;

    @Name("List of column header")
    @FindBys( {@FindBy(xpath = "//th[@role='columnheader']")} )
    private List<WebElement> listColumnHeader;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Delete')]")
    private WebElement buttonDelete;

    @FindBy(css = ".v-overlay")
    private WebElement overlay;

    @FindBy(css = ".v-alert.success")
    private WebElement successAlert;
}
