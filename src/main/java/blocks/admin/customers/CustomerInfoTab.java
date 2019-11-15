package blocks.admin.customers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by bigdrop on 11/14/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "main"))
public class CustomerInfoTab extends HtmlElement {

    @FindBy(xpath = "//div[contains(text(), 'Edit info')]")
    private WebElement buttonEditInfo;
}
