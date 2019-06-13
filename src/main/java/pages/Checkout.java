package pages;

import blocks.CheckoutSidebar;
import blocks.CheckoutSteps;
import data.CreditCards;
import data.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Checkout extends BasePage{

    public Checkout(WebDriver driver) {
        super(driver);
    }

    private CheckoutSteps checkoutSteps;
    private CheckoutSidebar checkoutSidebar;

    @Override
    public void open() {

    }

    public void fillCheckoutInformationForm(Users users) {
        waitUntilElementAppeared(checkoutSteps.getButtonProceed());
        type(checkoutSteps.getFirstNameField(), users.getFirstName());
        type(checkoutSteps.getLastNameField(), users.getLastName());
        type(checkoutSteps.getEmailField(), users.getEmail());
        waitUntilElementWillBeClickable(checkoutSteps.getButtonProceed());
        checkoutSteps.getButtonProceed().click();
    }

    public void fillCreditCardInformationForm(CreditCards creditCards) throws InterruptedException {
        waitUntilElementAppeared(checkoutSteps.getButtonPlaceOrder());
        type(checkoutSteps.getCardNameField(), creditCards.getCardName());
        WebElement cardNumberField = driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name='__privateStripeFrame5']")))
                .findElement(By.cssSelector("input[name='cardnumber']"));
        sendKeysSlowly(cardNumberField, creditCards.getCardNumber());
        driver.switchTo().defaultContent();
        WebElement expDateField = driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name='__privateStripeFrame7']")))
                .findElement(By.cssSelector("input[name='exp-date']"));
        sendKeysSlowly(expDateField, creditCards.getCardExpDate());
        driver.switchTo().defaultContent();
        WebElement cvvField = driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[name='__privateStripeFrame6']")))
                .findElement(By.cssSelector("input[name='cvc']"));
        sendKeysSlowly(cvvField, creditCards.getCardCVV());
        driver.switchTo().defaultContent();
    }

    public void fillCheckoutBillingAddressForm(Users users) {
        type(checkoutSteps.getBillingFirstNameField(), users.getFirstName());
        type(checkoutSteps.getBillingLastNameField(), users.getLastName());
        type(checkoutSteps.getStreetAddressField(), users.getStreetAddress());
        type(checkoutSteps.getAptSuiteField(), users.getAptSuite());
        type(checkoutSteps.getCityField(), users.getCity());
        type(checkoutSteps.getZipCodeField(), users.getZipCode());
        type(checkoutSteps.getPhoneNumberField(), users.getPhone());
        chooseState(users.getState());
    }

    public void fillCheckout(Users users, CreditCards creditCards, boolean subscribe) throws InterruptedException {
        fillCheckoutInformationForm(users);
        fillCreditCardInformationForm(creditCards);
        fillCheckoutBillingAddressForm(users);
        clickCheckboxIAgreeToTheTerms();
        if(subscribe)
            clickCheckboxIWouldLikeToReceiveEmail();
        waitUntilElementWillBeClickable(checkoutSteps.getButtonPlaceOrder());
        checkoutSteps.getButtonPlaceOrder().click();
    }

    public void addPromoCode(String promocode) throws InterruptedException {
        checkoutSidebar.getLinkHavePromoCode().click();
        waitUntilElementAppeared(checkoutSidebar.getButtonApply());
        type(checkoutSidebar.getInputPromoCode(), promocode);
        waitUntilElementWillBeClickable(checkoutSidebar.getButtonApply());
        checkoutSidebar.getButtonApply().click();
    }

    public void addPromoCodeAndAfterRemove(String promocode) throws InterruptedException {
        checkoutSidebar.getLinkHavePromoCode().click();
        waitUntilElementAppeared(checkoutSidebar.getButtonApply());
        type(checkoutSidebar.getInputPromoCode(), promocode);
        waitUntilElementWillBeClickable(checkoutSidebar.getButtonApply());
        checkoutSidebar.getButtonApply().click();
        waitUntilElementAppeared(checkoutSidebar.getPriceAfterAppliedPromoCode());
        checkoutSidebar.getLinkRemovePromoCode().click();
    }

    public void clickCheckboxIAgreeToTheTerms() {
        waitUntilElementAppeared(checkoutSteps.getCheckboxIAgreeToTheTerms());
        scrollToElement(checkoutSteps.getCheckboxIAgreeToTheTerms());
        Actions action = new Actions(driver);
        Action actionClickLeftSide = action.moveToElement(checkoutSteps.getCheckboxIAgreeToTheTerms(), 2, 2).click().build();
        actionClickLeftSide.perform();
    }

    public void clickCheckboxIWouldLikeToReceiveEmail() {
        waitUntilElementAppeared(checkoutSteps.getCheckboxIWouldLikeReceiveEmail());
        scrollToElement(checkoutSteps.getCheckboxIWouldLikeReceiveEmail());
        Actions action = new Actions(driver);
        Action actionClickLeftSide = action.moveToElement(checkoutSteps.getCheckboxIWouldLikeReceiveEmail(), 2, 2).click().build();
        actionClickLeftSide.perform();
    }
    public void chooseState(String stateName) {
        waitUntilElementAppeared(checkoutSteps.getStateSelectField());
        scrollToElement(checkoutSteps.getStateSelectField());
        checkoutSteps.getStateSelectField().click();
        waitUntilElementAppeared(checkoutSteps.getListStates().get(0));
        for(WebElement element : checkoutSteps.getListStates()) {
            if (element.getText().equals(stateName.toUpperCase()) || element.equals(checkoutSteps.getListStates().get(checkoutSteps.getListStates().size() - 1))) {
                element.click();
                return;
            }
        }
    }

    public void checkingPaymentError() {
        waitUntilElementAppeared(checkoutSteps.getPaymentError());
        softAssert.assertTrue(isElementPresent(checkoutSteps.getPaymentError()), "Error message is absent");
        softAssert.assertAll();
    }
}
