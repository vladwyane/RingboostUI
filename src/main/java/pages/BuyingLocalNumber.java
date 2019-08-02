package pages;

import blocks.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bigdrop on 6/14/2019.
 */
public class BuyingLocalNumber extends BasePage {

    public BuyingLocalNumber(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    private LocalNumbersBlock localNumbersBlock;
    private LocalSupportSolutionBlock localSupportSolutionBlock;
    private OrderSummaryBlock orderSummaryBlock;
    private PickYourMonthlyPlanBlock pickYourMonthlyPlanBlock;
    private RingToNumberBlock ringToNumberBlock;

    @FindBy(css= ".text-center button")
    private WebElement continueButton;

    @FindBy(css= ".title-price")
    private WebElement priceNumber;

    @FindBy(css= ".main-number-holder")
    private WebElement phoneNumber;

    public double choosePlan(String planName) {
        waiting2seconds();
        double pricePlan = 0;
        for (int i = 0; i < localSupportSolutionBlock.getListTitlesSolutionItems().size(); i++) {
            if(localSupportSolutionBlock.getListTitlesSolutionItems().get(i).getText().equals(planName)) {
                scrollToElement(localSupportSolutionBlock.getListButtonsSolutionItems().get(i));
                try {
                    pricePlan = Double.parseDouble(getNumbersFromString(localSupportSolutionBlock.getListPricesSolutionItems().get(i).getText()));
                } catch (Exception e) {
                    localSupportSolutionBlock.getListButtonsSolutionItems().get(i).click();
                    return pricePlan;
                }
                localSupportSolutionBlock.getListButtonsSolutionItems().get(i).click();
                return pricePlan;
            }
        }
        return 0;
    }

    public double getPriceNumber() {
        waitUntilElementAppeared(priceNumber);
        return Double.parseDouble(getNumbersFromString(priceNumber.getText()));
    }


    public String goToCheckout() {
        waitUntilElementAppeared(orderSummaryBlock.getButtonProceedToCheckout());
        String boughtNumber = phoneNumber.getText();
        boughtNumber = boughtNumber.replaceAll("\\D+","").substring(3);
        orderSummaryBlock.getButtonProceedToCheckout().click();
        return boughtNumber;
    }

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

    public double chooseCheckboxMultipleRingToNumber() {
        ringToNumberBlock.getCheckboxMultipleRingToNumber().click();
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(getNumbersFromString(priceNumber.getText()));
    }

    public double enterRingToNumber(String number) {
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
        return Double.parseDouble(getNumbersFromString(priceNumber.getText()));
    }

    public double enterRingToNumberWithMultipleCheckbox(String number) {
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
        return Double.parseDouble(getNumbersFromString(priceNumber.getText()));
    }
}
