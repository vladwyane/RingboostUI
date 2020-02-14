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

/**
 * Created by bigdrop on 6/14/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "all-numbers-wrapper"))
public class LocalNumbersBlock extends HtmlElement {

    @Name("ArrayList of available local numbers")
    //@FindBys( {@FindBy(xpath = "//li[@class='available']/a[@class='phone-card']")} )
    @FindBys( {@FindBy(xpath = "//a[@class='phone-card']")} )
    public List<WebElement> listOfAvailableLocalNumbers;

    @Name("ArrayList of local numbers li")
    @FindBys( {@FindBy(css = "li")} )
    public List<WebElement> listLocalNumbersLi;

    @Name("ArrayList of price local numbers")
    @FindBys( {@FindBy(css = ".price")} )
    public List<WebElement> listPricesLocalNumbers;

    @Name("ArrayList of make offer links")
    @FindBys( {@FindBy(css = ".make-offer")} )
    public List<WebElement> listMakeOfferLinks;
}
