package blocks.front;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

import java.util.List;

/**
 * Created by bigdrop on 5/13/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "header"))
public class HeaderBlock extends HtmlElement {

    @Name("ArrayList of sub-menu tollfree items")
    @FindBys( {@FindBy(css = ".navigation-numbers .sub-menu a")} )
    public List<WebElement> listSubMenuTollFree;

    @FindBy(xpath = "//ul[@class='main-navigation navigation-numbers']//a[contains(text(), 'Toll-Free Numbers')]/ancestor::li")
    private WebElement tollFreeLinInMainNav;

    public WebElement chooseItemFromSubMenuTollFree(String nameOfItem) {
        for (int i = 0; i < listSubMenuTollFree.size(); i++) {
            String name = listSubMenuTollFree.get(i).getAttribute("href");
            if (listSubMenuTollFree.get(i).getAttribute("href").contains(nameOfItem)) {
               return listSubMenuTollFree.get(i);
            }
        }
        return this;
    }
}
