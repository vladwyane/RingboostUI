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
@Block(@FindBy(className = "related-vanity"))
public class RelatedVanityBlock extends HtmlElement {

    @FindBy(css= "h2")
    private WebElement titleSection;

    @Name("ArrayList of related vanity numbers")
    @FindBys( {@FindBy(css = "li")} )
    public List<WebElement> lisRelatedVanityNumbers;


}
