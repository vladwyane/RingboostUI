package blocks;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "checkout-steps"))
public class CheckoutSteps extends HtmlElement {

    @FindBy(xpath= "//span[contains(text(),'Step 1')]//ancestor::div[@class='heading']//following-sibling::form//input[@name='first_name']")
    private TextInput firstNameField;

    @FindBy(xpath= "//span[contains(text(),'Step 1')]//ancestor::div[@class='heading']//following-sibling::form//input[@name='last_name']")
    private TextInput lastNameField;

    @FindBy(xpath= "//span[contains(text(),'Step 1')]//ancestor::div[@class='heading']//following-sibling::form//input[@name='email']")
    private TextInput emailField;

    @FindBy(xpath= "//span[contains(text(),'Step 1')]//ancestor::div[@class='heading']//following-sibling::form//button")
    private WebElement buttonProceed;

    @FindBy(xpath= "//*[@class='agreementList']//span[contains(text(),'I agree to the terms')]")
    private WebElement checkboxIAgreeToTheTerms;

    @FindBy(xpath= "//*[@class='agreementList']//span[contains(text(),'I would like to receive email')]")
    private WebElement checkboxIWouldLikeReceiveEmail;

}
