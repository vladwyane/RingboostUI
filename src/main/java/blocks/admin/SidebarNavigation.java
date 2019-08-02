package blocks.admin;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by bigdrop on 7/31/2019.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "v-navigation-drawer"))
public class SidebarNavigation extends HtmlElement{

    @FindBy(xpath = "//div[contains(text(), 'Inventory')]")
    private WebElement inventoryLink;

    @FindBy(xpath = "//div[contains(text(), 'Tollfree')]")
    private WebElement tollfreeLink;

    @FindBy(xpath = "//div[contains(text(), 'Local')]")
    private WebElement localLink;
}
