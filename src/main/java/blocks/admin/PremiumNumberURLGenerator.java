package blocks.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

/**
 * Created by bigdrop on 8/1/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = " "))
public class PremiumNumberURLGenerator extends HtmlElement {

    @FindBy(css = " ")
    private WebElement buttonGenerateLink;

    @FindBy(css = " ")
    private WebElement labelPhoneNumber;

    @FindBy(css = " ")
    private TextInput displayedNumberOnFE;

    @FindBy(css = " ")
    private WebElement showPromoCodeCheckbox;

    @FindBy(css = " ")
    private WebElement selectOfStates;

    @Name("List of states")
    @FindBys( {@FindBy(css = " ")} )
    private List<WebElement> listOfStates;

    @FindBy(css = " ")
    private WebElement multiSelectAreaCodes;

    @Name("List of Area Codes")
    @FindBys( {@FindBy(css = " ")} )
    private List<WebElement> listOfAreaCodes;

    @Name("List of minutes value")
    @FindBys( {@FindBy(css = " ")} )
    private List<WebElement> listMinutes;

    @FindBy(css = " ")
    private WebElement selectTermLength;

    @Name("List of Term Length")
    @FindBys( {@FindBy(css = " ")} )
    private List<WebElement> listOTermLength;

}
