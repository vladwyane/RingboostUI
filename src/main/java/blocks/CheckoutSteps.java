package blocks;

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

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "checkout-steps"))
public class CheckoutSteps extends HtmlElement {

    //User information form
    @FindBy(xpath= "//input[@name='first_name']")
    private TextInput firstNameField;

    @FindBy(xpath= "//input[@name='last_name']")
    private TextInput lastNameField;

    @FindBy(xpath= "//input[@name='email']")
    private TextInput emailField;

    @FindBy(xpath= "//span[contains(text(),'Step 1')]//ancestor::div[@class='heading']//following-sibling::form//button")
    private WebElement buttonProceed;

    //Credit Card information form
    @FindBy(css= "input[name='name_on_card']")
    private TextInput cardNameField;

    //Billing Address information form
    @FindBy(css= "input[name='first_name']")
    private TextInput billingFirstNameField;

    @FindBy(css= "input[name='last_name']")
    private TextInput billingLastNameField;

    @FindBy(css= "input[name='street_address']")
    private TextInput streetAddressField;

    @FindBy(css= "input[name='apt_suite']")
    private TextInput aptSuiteField;

    @FindBy(css= "input[name='city']")
    private TextInput cityField;

    @FindBy(css= "input[name='zip_code']")
    private TextInput zipCodeField;

    @FindBy(css= "input[name='phone_number']")
    private TextInput phoneNumberField;

    @FindBy(css= "div[name='state-select']")
    private WebElement stateSelectField;

    @Name("Drop down states")
    @FindBys( {@FindBy(css = ".dropdown-menu li")} )
    private List<WebElement> listStates;


    @FindBy(xpath= "//*[@class='agreementList']//span[contains(text(),'I agree to the terms')]")
    private WebElement checkboxIAgreeToTheTerms;

    @FindBy(xpath= "//*[@class='agreementList']//span[contains(text(),'I would like to receive email')]")
    private WebElement checkboxIWouldLikeReceiveEmail;

    @FindBy(css= ".place-order")
    private WebElement buttonPlaceOrder;


}
