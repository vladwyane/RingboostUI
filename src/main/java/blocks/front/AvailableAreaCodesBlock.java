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
@Block(@FindBy(className = "drag"))
public class AvailableAreaCodesBlock extends HtmlElement {

    @FindBy(css = ".drag-box-title")
    private WebElement dragBoxTitle;

    @Name("List Area Codes Or Clusters")
    @FindBys( {@FindBy(css = ".drag-box-content .drag-item")} )
    private List<WebElement> listAreaCodes;

    @Name("List Prices Area Codes Or Clusters")
    @FindBys( {@FindBy(css = ".drag-box-content .price")} )
    private List<WebElement> listPricesAreaCodes;


}
