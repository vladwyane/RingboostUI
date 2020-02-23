package pages.front;

import blocks.front.OrderSummaryBlock;
import blocks.front.PickYourMonthlyPlanBlock;
import blocks.front.RingToNumberBlock;
import data.PricingTollFreeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by bigdrop on 6/19/2019.
 */
public class BuyingBasic800Number extends BasePage {

    private PickYourMonthlyPlanBlock pickYourMonthlyPlanBlock;
    private RingToNumberBlock ringToNumberBlock;
    private OrderSummaryBlock orderSummaryBlock;

    public BuyingBasic800Number(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    @FindBy(css= ".text-center button")
    private WebElement continueButton;

    @FindBy(css= ".main-number-holder")
    private WebElement phoneNumber;

    public WebElement getPhoneNumber() {
        waitUntilElementAppeared(phoneNumber);
        return phoneNumber;
    }

    @Step("Click Link Continue To Checkout")
    public void goToCheckout() {
        waitUntilElementAppeared(orderSummaryBlock.getButtonProceedToCheckout());
        orderSummaryBlock.getButtonProceedToCheckout().click();
    }

    @Step("Choose Pick Your Monthly Plan: {0}")
    public double choosePickYourMonthlyPlan(String planName) {
        waitUntilElementWillBeClickable(pickYourMonthlyPlanBlock.listCardButtons.get(0));
        double perMonthPrice;
        for (int i = 0; i < pickYourMonthlyPlanBlock.listPlaneName.size(); i++) {
            if(pickYourMonthlyPlanBlock.listPlaneName.get(i).getText().equals(planName)) {
                perMonthPrice = Double.parseDouble(getNumbersFromString(pickYourMonthlyPlanBlock.listOfPerMonthPrice.get(i).getText()));
                scrollToElement(pickYourMonthlyPlanBlock.listCardButtons.get(i));
                pickYourMonthlyPlanBlock.listCardButtons.get(i).click();
                return perMonthPrice;
            }
        }
        scrollToElement(pickYourMonthlyPlanBlock.listCardButtons.get(0));
        pickYourMonthlyPlanBlock.listCardButtons.get(0).click();
        perMonthPrice = 0.0;
        return perMonthPrice;
    }

    public String getAdditionalCost(String planName) {
        waitUntilElementWillBeClickable(pickYourMonthlyPlanBlock.listCardButtons.get(0));
        String additionalCost = null;
        for (int i = 0; i < pickYourMonthlyPlanBlock.listPlaneName.size(); i++) {
            if(pickYourMonthlyPlanBlock.listPlaneName.get(i).getText().equals(planName)) {
                additionalCost = pickYourMonthlyPlanBlock.getListOfAdditionalCost().get(i).getText().replaceAll("[^0-9?!\\\\.]","");
                return additionalCost;
            }
        }
        return additionalCost;
    }

    public String getAmountMinutes(String planName) {
        waitUntilElementWillBeClickable(pickYourMonthlyPlanBlock.listCardButtons.get(0));
        String amountMinutes = null;
        for (int i = 0; i < pickYourMonthlyPlanBlock.listPlaneName.size(); i++) {
            if(pickYourMonthlyPlanBlock.listPlaneName.get(i).getText().equals(planName)) {
                amountMinutes = pickYourMonthlyPlanBlock.getListOfMinutes().get(i).getText();
                return amountMinutes;
            }
        }
        return amountMinutes;
    }

    public void checkingCreatedTermFromAdmin(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementWillBeClickable(pickYourMonthlyPlanBlock.listCardButtons.get(0));
        String perMonthPrice = null;
        boolean termPresent = false;
        for (int i = 0; i < pickYourMonthlyPlanBlock.listPlaneName.size(); i++) {
            if(pickYourMonthlyPlanBlock.listPlaneName.get(i).getText().toLowerCase().equals(pricingTollFreeSettings.getName().toLowerCase())) {
                perMonthPrice = getNumbersFromString(pickYourMonthlyPlanBlock.listOfPerMonthPrice.get(i).getText());
                termPresent = true;
                break;
            }
        }
        softAssert.assertEquals(pricingTollFreeSettings.getValue(), perMonthPrice);
        softAssert.assertTrue(termPresent, "Term not found");
        softAssert.assertAll();
    }

    @Step("Choose Checkbox Multiple Ring To Number")
    public void chooseCheckboxMultipleRingToNumber() {
        ringToNumberBlock.getCheckboxMultipleRingToNumber().click();
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
    }

    @Step("Enter your ring-to-number: {0}")
    public void enterRingToNumber(String number) {
        char[] array = number.toCharArray();
        for (int i = 0; i < ringToNumberBlock.listInputRingToNumber.size(); i++) {
            switch (i) {
                case 0:
                    type(ringToNumberBlock.listInputRingToNumber.get(i), array[0] + "" + array[1] + "" + array[2]);
                    break;
                case 1:
                    type(ringToNumberBlock.listInputRingToNumber.get(i), array[3] + "" + array[4] + "" + array[5]);
                    break;
                case 2:
                    type(ringToNumberBlock.listInputRingToNumber.get(i), array[6] + "" + array[7] + "" + array[8] + "" + array[9]);
                    break;
            }
        }
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
    }

    public void enterRingToNumberWithMultipleCheckbox(String number) {
        char[] array = number.toCharArray();
        for (int i = 0; i < ringToNumberBlock.listInputRingToNumber.size(); i++) {
            switch (i) {
                case 0:
                    type(ringToNumberBlock.listInputRingToNumber.get(i), array[0] + "" + array[1] + "" + array[2]);
                    break;
                case 1:
                    type(ringToNumberBlock.listInputRingToNumber.get(i), array[3] + "" + array[4] + "" + array[5]);
                    break;
                case 2:
                    type(ringToNumberBlock.listInputRingToNumber.get(i), array[6] + "" + array[7] + "" + array[8] + "" + array[9]);
                    break;
            }
        }
        ringToNumberBlock.getCheckboxMultipleRingToNumber().click();
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
    }

    public double getPriceActivationFee() {
        waitUntilElementAppeared(orderSummaryBlock.getPriceActivationFee());
        return Double.parseDouble(getNumbersFromString(orderSummaryBlock.getPriceActivationFee().getText()));
    }


}
