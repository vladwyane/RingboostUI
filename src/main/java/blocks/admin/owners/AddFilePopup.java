package blocks.admin.owners;

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
 * Created by bigdrop on 10/18/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class AddFilePopup extends HtmlElement {

    @FindBy(xpath = "//input[@type='file']")
    private WebElement uploadFile;

    @Name("Data-picker list")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'date-picker-table')]//td")} )
    private List<WebElement> listOfData;

    @FindBy(xpath = "//div[contains(text(), 'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(), 'Save file')]")
    private WebElement buttonSaveFile;
}
