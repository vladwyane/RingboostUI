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
@Block(@FindBy(className = "cards-wrapper"))
public class PickYourMonthlyPlanBlock extends HtmlElement {

    @Name("ArrayList of plane name")
    @FindBys( {@FindBy(css = ".plan-name")} )
    public List<WebElement> listPlaneName;

    @Name("ArrayList of card buttons")
    @FindBys( {@FindBy(css = ".card-button")} )
    public List<WebElement> listCardButtons;

    @Name("ArrayList of per month price")
    @FindBys( {@FindBy(xpath = "//*[@class='plan-name']//following-sibling::p")} )
    public List<WebElement> listOfPerMonthPrice;

    @Name("ArrayList of description plan")
    @FindBys( {@FindBy(xpath = "//*[@class='plan-name']//ancestor::div[1]//following-sibling::div//h3")} )
    public List<WebElement> listOfPlanDescription;

    @Name("ArrayList of additional cost")
    @FindBys( {@FindBy(xpath = "//*[@class='plan-features']//p")} )
    public List<WebElement> listOfAdditionalCost;

    @Name("ArrayList of minutes")
    @FindBys( {@FindBy(xpath = "//*[@class='plan-features']//h3")} )
    public List<WebElement> listOfMinutes;
}
