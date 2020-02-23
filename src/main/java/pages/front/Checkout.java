package pages.front;

import blocks.front.CheckoutSidebar;
import blocks.front.CheckoutSteps;
import blocks.front.ModalTextAgreement;
import data.CreditCards;
import data.Users;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

public class Checkout extends BasePage {

    public Checkout(WebDriver driver) {
        super(driver);
    }

    private CheckoutSteps checkoutSteps;
    private CheckoutSidebar checkoutSidebar;
    private ModalTextAgreement modalTextAgreement;

    @Override
    public void open() {

    }

    public void fillCheckoutInformationForm(Users users) {
        waitUntilElementAppeared(checkoutSteps.getButtonProceed());
        type(checkoutSteps.getFirstNameField(), users.getFirstName());
        type(checkoutSteps.getLastNameField(), users.getLastName());
        type(checkoutSteps.getEmailField(), users.getEmail());
        type(checkoutSteps.getCompanyField(), users.getCompany());
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
        waiting2seconds();
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

    @Step("Fill checkout forms users: {0}, credit card: {1}, subscribe checkbox: {2}")
    public void fillCheckout(Users users, CreditCards creditCards, boolean subscribe) throws InterruptedException {
        fillCheckoutInformationForm(users);
        fillCreditCardInformationForm(creditCards);
        fillCheckoutBillingAddressForm(users);
        clickCheckboxIAgreeToTheTerms();
        if(!subscribe)
            clickCheckboxIWouldLikeToReceiveEmail();
        waitUntilElementWillBeClickable(checkoutSteps.getButtonPlaceOrder());
        checkoutSteps.getButtonPlaceOrder().click();
    }

    @Step("Add Promo Code: {0}")
    public boolean addPromoCode(String promocode) throws InterruptedException {
        if(isElementPresent(checkoutSidebar.getLinkHavePromoCode())) {
            checkoutSidebar.getLinkHavePromoCode().click();
            waitUntilElementAppeared(checkoutSidebar.getButtonApply());
            type(checkoutSidebar.getInputPromoCode(), promocode);
            waitUntilElementWillBeClickable(checkoutSidebar.getButtonApply());
            checkoutSidebar.getButtonApply().click();
            return true;
        }
        else return false;
    }

    @Step("Checking invisibility coupon field")
    public void checkingDisableCouponField() throws InterruptedException {
        softAssert.assertFalse(isElementPresent(checkoutSidebar.getLinkHavePromoCode()), "Link have promo code is present");
        softAssert.assertAll();
    }

    public String getDiscountPromoCode() {
        return checkoutSidebar.getDiscountFromPromoCode().getText();
    }

    public void wait2SecUntilPromoRemove() {
        waiting2seconds();
    }

    public String getPricePayToday() {
        waitUntilElementAppeared(checkoutSidebar.getPricePayToday());
        if(isElementPresent(checkoutSidebar.getPricePayToday())) {
            return checkoutSidebar.getPricePayToday().getText();
        }
        else return checkoutSidebar.getPriceTotalDueToday().getText();

    }

    public double getDiscountAmountAreacodes() {
        try {
            waitUntilElementAppeared(checkoutSidebar.getSwitchTitle());
            checkoutSidebar.getSwitchTitle().click();
            waitUntilElementAppeared(checkoutSidebar.getDiscountPriceAmountAreaCodes());
            return Double.parseDouble(checkoutSidebar.getDiscountPriceAmountAreaCodes().getText()
                    .substring(checkoutSidebar.getDiscountPriceAmountAreaCodes().getText().indexOf("$") + 1));
        } catch (Exception e) {
            return 0.0;
        }
    }

    @Step("Add Promo Code And After Remove")
    public void addPromoCodeAndAfterRemove(String promocode) throws InterruptedException {
        checkoutSidebar.getLinkHavePromoCode().click();
        waitUntilElementAppeared(checkoutSidebar.getButtonApply());
        type(checkoutSidebar.getInputPromoCode(), promocode);
        waitUntilElementWillBeClickable(checkoutSidebar.getButtonApply());
        checkoutSidebar.getButtonApply().click();
        waitUntilElementAppeared(checkoutSidebar.getLinkRemovePromoCode());
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

    @Step("Checking error message")
    public void checkingPaymentError() {
        waitUntilElementAppeared(checkoutSteps.getPaymentError());
        softAssert.assertTrue(isElementPresent(checkoutSteps.getPaymentError()), "Error message is absent");
        softAssert.assertAll();
    }

    public void checkingCorrectTollfreeAgreement(String customAgreement) {
        waitUntilElementAppeared(modalTextAgreement.getModalContent());
        softAssert.assertEquals(modalTextAgreement.getModalContent(), customAgreement);
        softAssert.assertAll();
    }

    @Step("Checking Correct Info In Sidebar priceNumberFirst: {0}, priceNumberSecond: {1}")
    public void checkingCorrectInfoInSidebar(double priceNumberFirst, double priceNumberLast) {
        waitUntilElementAppeared(checkoutSidebar.getPriceTotalDueToday());
        double priceInSidebar = Double.parseDouble(getNumbersFromString(checkoutSidebar.getPriceTotalDueToday().getText().replaceAll(",","")));
        softAssert.assertNotEquals(priceNumberFirst, priceNumberLast);
        softAssert.assertEquals(priceNumberLast, priceInSidebar);
        softAssert.assertAll();
    }
}
