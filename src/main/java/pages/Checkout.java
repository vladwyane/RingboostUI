package pages;

import blocks.CheckoutSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Checkout extends BasePage{

    public Checkout(WebDriver driver) {
        super(driver);
    }

    private CheckoutSteps checkoutSteps;

    @Override
    public void open() {

    }

    public void fillCheckoutInformationForm(String firstName, String lastName, String email) {
        waitUntilElementAppeared(checkoutSteps.getButtonProceed());
        type(checkoutSteps.getFirstNameField(), firstName);
        type(checkoutSteps.getLastNameField(), lastName);
        type(checkoutSteps.getEmailField(), email);
        waitUntilElementWillBeClickable(checkoutSteps.getButtonProceed());
        checkoutSteps.getButtonProceed().click();
    }

    public void clickCheckboxIAgreeToTheTerms() {
        waitUntilElementAppeared(checkoutSteps.getCheckboxIAgreeToTheTerms());
        scrollToElement(checkoutSteps.getCheckboxIAgreeToTheTerms());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(checkoutSteps.getCheckboxIAgreeToTheTerms(), 2, 0).build();
        actionFirstBull.perform();
    }
}
