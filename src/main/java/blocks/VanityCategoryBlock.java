package blocks;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import sun.plugin.dom.html.HTMLElement;

import java.util.List;

/**
 * Created by bigdrop on 5/15/2019.
 */
@Data
@NoArgsConstructor
@Block(@FindBy(className = "category-catalog"))
public class VanityCategoryBlock extends HtmlElement {

    @Name("ArrayList of letters options")
    @FindBys( {@FindBy(css = ".options li")} )
    public List<WebElement> listLettersOptions;

    @Name("ArrayList of vanity categories")
    @FindBys( {@FindBy(css = ".categories li")} )
    public List<WebElement> listVanityCategories;

}
