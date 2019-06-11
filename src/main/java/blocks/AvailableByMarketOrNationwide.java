package blocks;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "areas-wrapper"))
public class AvailableByMarketOrNationwide extends HtmlElement {

    @FindBy(xpath= "//h3[contains(text(),'Choose My Areas')]/following::button[1]")
    private WebElement buttonSelectMyAreas;

    @FindBy(xpath= "//h3[contains(text(),'Choose Nationwide')]/following::button[1]")
    private WebElement buttonSelectNationwide;
}
