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

    @FindBy(xpath = "//a[@href='/admin/inventory/toll-free']")
    private WebElement tollfreeLink;

    @FindBy(xpath = "//div[contains(text(), 'Local')]")
    private WebElement localLink;

    @FindBy(xpath = "//div[contains(text(), 'Extras')]")
    private WebElement extraslLink;

    @FindBy(xpath = "//div[contains(text(), 'Carriers')]")
    private WebElement carriersLink;

    @FindBy(xpath = "//div[contains(text(), 'Settings')]")
    private WebElement settingsLink;

    @FindBy(xpath = "//div[contains(text(), 'API')]")
    private WebElement apiLink;

    @FindBy(xpath = "//div[contains(text(), 'Pricing(Global)')]")
    private WebElement pricingGlobalLink;

    @FindBy(xpath = "//a[@href='/admin/pricing/toll-free']")
    private WebElement tollFreePriceLink;

    
}
