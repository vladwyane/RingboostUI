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
 * Created by bigdrop on 6/19/2019.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "without-status"))
public class Basic800NumbersBlock extends HtmlElement {

    @Name("ArrayList of numbers basic 800")
    @FindBys( {@FindBy(css = "li")} )
    public List<WebElement> listBasic800Numbers;

    @Name("ArrayList of available numbers basic 800")
    @FindBys( {@FindBy(xpath = "//ul[contains(@class, 'without-status')]//span[contains(@class, 'status') " +
            "and (text()) = 'Available']/ancestor::li//div[@class='number']")} )
    public List<WebElement> listAvailableBasic800Numbers;

}
