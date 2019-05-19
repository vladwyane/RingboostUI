package blocks;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "slider-wrapper"))
public class SliderMonthlyMinutes extends HtmlElement {

    @FindBy(css= ".vue-slider-dot-handle")
    private WebElement bulletOfSlider;

    @FindBy(css= ".vue-slider-dot-tooltip-text.price")
    private WebElement tooltipPrice;

    @FindBy(xpath= "//*[@class='vue-slider-dot-tooltip-text'][1]")
    private WebElement tooltipMinute;

    @FindBy(css= ".vue-slider-dot-tooltip")
    private WebElement sliderTooltip;



}
