package blocks;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by bigdrop on 3/14/2019.
 */
@Data
@NoArgsConstructor
@Block(@FindBy(className = "main-search-block"))
public class SearchBlock extends HtmlElement {

    @FindBy(css = ".tollfree .search-input")
    private TextInput tollFreeSearchField;

    @FindBy(css= "input.button.filled")
    private Button buttonFindNumber;

    @FindBy(id= "area-code")
    private TextInput areaCodeField;

    @FindBy(id= "keyword")
    private TextInput localSearchField;

    @FindBy(xpath= "//div[@class='switcher']//span[contains(text(), 'local')]")
    private WebElement switcherLocalPart;

    @FindBy(xpath= "//div[@class='switcher']//span[contains(text(), 'toll-free')]")
    private WebElement switcherTollFreePart;

    @FindBy(css= "h1")
    private WebElement titleH1;

    @FindBy(css = ".search-block-helper button")
    private WebElement buttonOpenCatalog;



}
