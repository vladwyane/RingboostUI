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

import java.util.List;

/**
 * Created by bigdrop on 8/1/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = " "))
public class ListGeneratedURL extends HtmlElement {

    @FindBy(css = " ")
    private WebElement buttonCreateNewURL;

    @FindBy(css = " ")
    private WebElement labelPhoneNumber;

    @Name("List of generated URL")
    @FindBys( {@FindBy(css = " ")} )
    private List<WebElement> listOfActionsURL;

    @Name("List of generated URL")
    @FindBys( {@FindBy(css = " ")} )
    private List<WebElement> listGeneratedURL;
}
