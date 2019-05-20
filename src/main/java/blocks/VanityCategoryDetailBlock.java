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

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "categories-wrapper"))
public class VanityCategoryDetailBlock extends HtmlElement {

    @FindBy(css = "h1")
    private WebElement titleH1;

    @FindBy(css = "div[name='category-select']")
    private WebElement selectCategory;

    @FindBy(css = "div[name='category-select'] input")
    private WebElement placeholderSelectCategory;

    @FindBy(css = "div[name='prefix-select']")
    private WebElement selectPrefix;

    @FindBy(css = "div[name='prefix-select'] input")
    private WebElement placeholderSelectPrefix;

    @FindBy(css = ".clear-filters")
    private WebElement buttonClearAllFilters;

    @Name("ArrayList of plane name")
    @FindBys( {@FindBy(css = "div[name='category-select'] .dropdown-menu li")} )
    private List<WebElement> listOfCategoryInSelectDropDown;

    @Name("ArrayList of plane name")
    @FindBys( {@FindBy(css = "div[name='prefix-select'] .dropdown-menu li")} )
    private List<WebElement> listOfPrefixInSelectDropDown;


}
