package blocks.front;

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
@Block(@FindBy(className = "cards-discount"))
public class TermLengthBlock extends HtmlElement {

    @Name("ArrayList of plane name")
    @FindBys( {@FindBy(css = ".plan-name")} )
    public List<WebElement> listPlaneName;

    @Name("ArrayList of card buttons")
    @FindBys( {@FindBy(css = ".card-button")} )
    public List<WebElement> listCardButtons;

    @Name("ArrayList of discount")
    @FindBys( {@FindBy(css = "h3")} )
    public List<WebElement> listOfDiscount;

    @Name("ArrayList of additional text")
    @FindBys( {@FindBy(css = ".plan-features p")} )
    public List<WebElement> listOfAdText;

}
