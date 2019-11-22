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
 * Created by bigdrop on 5/15/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = ".all-numbers-wrapper"))
public class RegularVanityNumbersBlock extends HtmlElement {

    @FindBy(css= "h2")
    private WebElement titleSection;

    @Name("ArrayList of available regular vanity numbers")
    @FindBys( {@FindBy(xpath = "//div[@class='all-numbers-wrapper']//span[contains(@class, 'status') " +
            "and (text()) = 'Available']/ancestor::li//div[@class='number']")} )
    public List<WebElement> listRegularVanityNumbers;

    @Name("ArrayList of status vanity numbers")
    @FindBys( {@FindBy(css = ".status")} )
    public List<WebElement> listStatusNumbers;

    @Name("ArrayList of all regular vanity numbers")
    @FindBys( {@FindBy(xpath = "//div[@class='number']")} )
    public List<WebElement> fullListRegularVanityNumbers;



}
