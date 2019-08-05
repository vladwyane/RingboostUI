package blocks.admin;

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
 * Created by bigdrop on 8/1/2019.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(css = "form"))
public class PremiumNumberURLGenerator extends HtmlElement {

    @FindBy(xpath = "//div[contains(text(),'Generate link')]")
    private WebElement buttonGenerateLink;

    @FindBy(css = " ")
    private WebElement labelPhoneNumber;

    @FindBy(xpath = "//input[@aria-label='Price for area codes']")
    private TextInput priceForAreaCodes;

    @FindBy(xpath = "//input[@aria-label='Displayed phone number']")
    private TextInput displayedNumberOnFE;

    @FindBy(xpath = "//label[contains(text(), 'Show promocode on checkout page?')]")
    private WebElement showPromoCodeCheckbox;

    @FindBy(xpath = "//input[@aria-label='Select state']/ancestor::div[@class='v-select__selections']")
    private WebElement selectOfStates;

    @Name("List of states")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfStates;

    @FindBy(xpath = "//input[@aria-label='Select area codes']/ancestor::div[@class='v-select__selections']")
    private WebElement multiSelectAreaCodes;

    @Name("List of Area Codes")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//a")} )
    private List<WebElement> listOfAreaCodes;

    @FindBy(xpath = "//input[@aria-label='Select term length']/ancestor::div[@class='v-select__selections']")
    private WebElement selectTermLength;

    @Name("List of Term Length")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOTermLength;

    @FindBy(css= ".v-slider__thumb")
    private WebElement bulletOfSlider;

    @FindBy(css= ".vue-slider-dot-tooltip-text.price")
    private WebElement tooltipPrice;

    @FindBy(xpath= "//*[@class='vue-slider-dot-tooltip-text'][1]")
    private WebElement tooltipMinute;

    @FindBy(css= ".vue-slider-dot-tooltip")
    private WebElement sliderTooltip;


}
