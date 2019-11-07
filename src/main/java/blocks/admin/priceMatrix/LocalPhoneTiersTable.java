package blocks.admin.priceMatrix;

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
 * Created by bigdrop on 10/21/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class LocalPhoneTiersTable extends HtmlElement {

    @FindBy(xpath = "//div[contains(text(), 'Add Phone Tier')]")
    private WebElement buttonAddPhoneTier;

    @Name("List of active actions")
    @FindBys( {@FindBy(css = "td .v-btn--icon")} )
    private List<WebElement> listOfActionsTiers;

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
