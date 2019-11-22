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

import javax.swing.text.html.HTML;
import java.util.List;

/**
 * Created by bigdrop on 7/17/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "auction-numbers-wrapper"))
public class AuctionNumbersBlock extends HtmlElement {

    @FindBy(css= "h2")
    private WebElement titleSection;

    @Name("ArrayList of auction vanity numbers")
    @FindBys( {@FindBy(css = "li")} )
    public List<WebElement> listAuctionVanityNumbers;
}
