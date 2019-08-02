package blocks.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by bigdrop on 7/31/2019.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class LoginForm extends HtmlElement {

    @FindBy(css = "input[name='login']")
    private TextInput loginField;

    @FindBy(css = "input[name='password']")
    private TextInput passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

}
