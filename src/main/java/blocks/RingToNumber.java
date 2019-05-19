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
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "rings-to-info"))
public class RingToNumber extends HtmlElement {

    @Name("ArrayList of input fields RingToNumber")
    @FindBys( {@FindBy(css = ".phone-input input")} )
    public List<TextInput> listInputRingToNumber;

    @FindBy(css = "input[type='checkbox']")
    private WebElement checkboxMultipleRingToNumber;



}
