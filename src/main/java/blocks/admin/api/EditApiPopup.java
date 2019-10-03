package blocks.admin.api;

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

/**
 * Created by bigdrop on 9/3/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class EditApiPopup extends HtmlElement {

    @FindBy(xpath = "//div[contains(text(),'Cancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//div[contains(text(),'Save')]")
    private WebElement buttonSave;

    @FindBy(xpath = "//label[contains(text(), 'Calculate DIDs')]")
    private WebElement calculateDIDsCheckbox;

    @FindBy(xpath = "//label[contains(text(), 'Is premium')]")
    private WebElement isPremiumCheckbox;

    @FindBy(xpath = "//label[contains(text(), 'Is nationwide')]")
    private WebElement isNationwideCheckbox;

    @FindBy(xpath = "//label[contains(text(), 'Is regional')]")
    private WebElement isRegionalCheckbox;

    @FindBy(xpath = "//input[@aria-label='Select carrier']/ancestor::div[@class='v-select__selections']")
    private WebElement selectOfCarriers;

    @FindBy(xpath = "//input[@aria-label='Standard']/ancestor::div[@class='v-select__selections']")
    private WebElement selectOfTollFree;

    @Name("List of carriers")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfCarriers;

    @FindBy(xpath = "//input[@aria-label='Select area codes']/ancestor::div[@class='v-select__selections']")
    private WebElement multiSelectAreaCodes;
}
