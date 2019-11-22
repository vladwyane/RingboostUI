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
@Block(@FindBy(className = "premium-numbers-wrapper"))
public class PremiumVanityNumbersBlock extends HtmlElement {

    @FindBy(css= "h2")
    private WebElement titleSection;

    @Name("ArrayList of premium vanity numbers")
    @FindBys( {@FindBy(css = "li .number")} )
    public List<WebElement> listPremiumVanityNumbers;

    @Name("ArrayList of premium status")
    @FindBys( {@FindBy(xpath = "//span[contains(@class, 'status')]")} )
    private List<WebElement> listStatusPremiumVanityNumbers;


}
