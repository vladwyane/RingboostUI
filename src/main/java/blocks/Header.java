package blocks;

import lombok.Data;
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
@Data
@NoArgsConstructor
@Block(@FindBy(className = "header"))
public class Header extends HtmlElement {

    @Name("ArrayList of sub-menu tollfree items")
    @FindBys( {@FindBy(css = ".navigation-numbers .sub-menu a")} )
    public List<WebElement> listSubMenuTollFree;

    @FindBy(css = ".main-navigation a[href='/toll-free-numbers']")
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
