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
 * Created by bigdrop on 5/15/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "all-numbers-wrapper"))
public class RegularVanityNumbersBlock extends HtmlElement {

    @FindBy(css= "h2")
    private WebElement titleSection;

    @Name("ArrayList of regular vanity numbers")
    @FindBys( {@FindBy(css = "li")} )
    public List<WebElement> listRegularVanityNumbers;

}