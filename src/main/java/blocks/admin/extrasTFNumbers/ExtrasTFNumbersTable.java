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

    @FindBy(xpath = "//div[contains(text(),'Add Tier')] ")
    private WebElement addTierButton;

    @Name("List of column header")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'v-window-item') and not(contains(@style,'display:none')) " +
            "and not(contains(@style,'display: none'))]//th[@role='columnheader']")} )
    private List<WebElement> listColumnHeader;

    @Name("List of Td")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'v-window-item') and not(contains(@style,'display:none')) " +
            "and not(contains(@style,'display: none'))]//td")} )
    private List<WebElement> listTd;

    @Name("List of actions")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'v-window-item') and not(contains(@style,'display:none')) " +
            "and not(contains(@style,'display: none'))]//button[contains(@class, 'v-btn--icon')]")} )
    private List<WebElement> listOfActions;

    @FindBy(xpath = "//div[contains(@class, 'v-window-item') and not(contains(@style,'display:none')) " +
            "and not(contains(@style,'display: none'))]//div[contains(text(),'Add Term')]")
    private WebElement addTermButton;

    @FindBy(xpath = "//div[contains(text(),'Add price minutes')]")
    private WebElement addPriceMinutesButton;

    @FindBy(xpath = "//div[contains(text(),'Add price multiple areas')]")
    private WebElement addPriceMultipleAreasButton;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(@class, 'v-dialog--active')]//div[contains(text(), 'Delete')]")
    private WebElement buttonDelete;

    @FindBy(css = ".v-alert.success")
    private WebElement successAlert;

}
