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
 * Created by bigdrop on 7/15/2019.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "form-filters"))
public class FiltersBlock extends HtmlElement {

    @FindBy(css = "div[name='states-select']")
    private WebElement selectStates;

    @FindBy(css = "div[name='states-select'] input")
    private WebElement placeholderSelectState;

    @FindBy(css = "div[name='area-select']")
    private WebElement selectAreaCode;

    @FindBy(css = "div[name='area-select'] input")
    private WebElement placeholderSelectAreaCode;

    @FindBy(css = "div[name='pattern-select']")
    private WebElement selectPattern;

    @FindBy(css = "div[name='pattern-select'] input")
    private WebElement placeholderSelectPattern;

    @FindBy(css = ".clear-filters")
    private WebElement buttonClearAllFilters;

    @Name("ArrayList of states")
    @FindBys( {@FindBy(css = "div[name='states-select'] .dropdown-menu li")} )
    private List<WebElement> listOfStatesInSelectDropDown;

    @Name("ArrayList of area codes")
    @FindBys( {@FindBy(css = "div[name='area-select'] .dropdown-menu li")} )
    private List<WebElement> listOfAreaCodesInSelectDropDown;

    @Name("ArrayList of patterns")
    @FindBys( {@FindBy(css = "div[name='pattern-select'] .dropdown-menu li")} )
    private List<WebElement> listOfPatternsInSelectDropDown;

    @FindBy(css = "div[name='sort-select']")
    private WebElement selectSortBy;

    @FindBy(xpath = "(//span[@class='vue-slider-dot-tooltip-text'])[1]")
    private WebElement rangeTooltipStart;

    @FindBy(xpath = "(//span[@class='vue-slider-dot-tooltip-text'])[2]")
    private WebElement rangeTooltipFinish;
}
