package blocks.admin.carriers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

/**
 * Created by bigdrop on 8/20/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class CarriersTable extends HtmlElement {
    @FindBy(xpath = "//input[@aria-label='Search']")
    private TextInput searchField;

    @FindBy(xpath = "//div[contains(text(), 'Add Carrier')]")
    private WebElement buttonAddCarrier;

    @Name("List of active actions")
    @FindBys( {@FindBy(xpath = "//td/button[contains(@class, 'v-btn--icon') and not(contains(@disabled, 'disabled'))]")} )
    private List<WebElement> listOfActionsCarriers;

    @Name("List of column header")
    @FindBys( {@FindBy(xpath = "//th[@role='columnheader']")} )
    private List<WebElement> listColumnHeader;

    @Name("List of Td")
    @FindBys( {@FindBy(css = "tbody tr td")} )
    private List<WebElement> listTd;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Delete')]")
    private WebElement buttonDelete;

    @FindBy(css = ".v-alert.success")
    private WebElement successAlert;
}
