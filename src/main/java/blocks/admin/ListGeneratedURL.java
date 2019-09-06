package blocks.admin;

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
 * Created by bigdrop on 8/1/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class ListGeneratedURL extends HtmlElement {

    @FindBy(xpath = "//div[contains(text(), 'Create New Price URL')]")
    private WebElement buttonCreateNewURL;

    @FindBy(css = ".v-toolbar__title")
    private WebElement labelPhoneNumber;

    @Name("List of active actions")
    @FindBys( {@FindBy(xpath = "//td/button[contains(@class, 'v-btn--icon') and not(contains(@disabled, 'disabled'))]")} )
    private List<WebElement> listOfActionsURL;

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



}
