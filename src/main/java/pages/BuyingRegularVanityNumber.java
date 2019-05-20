package pages;

import blocks.OrderSummary;
import blocks.RingToNumber;
import blocks.SliderMonthlyMinutes;
import blocks.TermLength;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class BuyingRegularVanityNumber extends BasePage {

    public BuyingRegularVanityNumber(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    private SliderMonthlyMinutes sliderMonthlyMinutes;
    private TermLength termLength;
    private RingToNumber ringToNumber;
    private OrderSummary orderSummary;

    @FindBy(css= ".text-center button")
    private WebElement continueButton;

    @FindBy(css= ".title-price")
    private WebElement priceNumber;

    public double choose5000MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutes.getBulletOfSlider());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(sliderMonthlyMinutes.getBulletOfSlider(), 2000, 0).build();
        actionFirstBull.perform();
        changeAttributeValueWithJS(sliderMonthlyMinutes.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutes.getTooltipPrice().getText()));
        int minute = Integer.parseInt(getNumbersFromString(sliderMonthlyMinutes.getTooltipMinute().getText()));
        continueButton.click();
        return minute * price;
    }

    public double choose20000MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutes.getBulletOfSlider());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(sliderMonthlyMinutes.getBulletOfSlider(), 509, 0).build();
        actionFirstBull.perform();
        changeAttributeValueWithJS(sliderMonthlyMinutes.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutes.getTooltipPrice().getText()));
        int minute = Integer.parseInt(getNumbersFromString(sliderMonthlyMinutes.getTooltipMinute().getText()));
        continueButton.click();
        return minute * price;
    }

    public double choose100MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutes.getBulletOfSlider());
        changeAttributeValueWithJS(sliderMonthlyMinutes.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutes.getTooltipPrice().getText()));
        int minute = Integer.parseInt(getNumbersFromString(sliderMonthlyMinutes.getTooltipMinute().getText()));
        continueButton.click();
        return minute * price;
    }

    public int chooseTermLength(String term) {
        waitUntilElementWillBeClickable(termLength.listCardButtons.get(0));
        int discount;
        for (int i = 0; i < termLength.listPlaneName.size(); i++) {
            if(termLength.listPlaneName.get(i).getText().equals(term)) {
                discount = Integer.parseInt(getNumbersFromString(termLength.listOfDiscount.get(i).getText()));
                termLength.listCardButtons.get(i).click();
                return discount;
            }
        }
        termLength.listCardButtons.get(0).click();
        discount = 0;
        return discount;

    }

    public double chooseCheckboxMultipleRingToNumber() {
        ringToNumber.getCheckboxMultipleRingToNumber().click();
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(getNumbersFromString(priceNumber.getText()));
    }

    public double enterRingToNumber(String number) {
        char[] array = number.toCharArray();
        for (int i = 0; i < ringToNumber.listInputRingToNumber.size(); i++) {
            switch (i) {
                case 0:
                    type(ringToNumber.listInputRingToNumber.get(i), array[0] + "" + array[1] + "" + array[2]);
                    break;
                case 1:
                    type(ringToNumber.listInputRingToNumber.get(i), array[3] + "" + array[4] + "" + array[5]);
                    break;
                case 2:
                    type(ringToNumber.listInputRingToNumber.get(i), array[6] + "" + array[7] + "" + array[8] + "" + array[9]);
                    break;
            }
        }
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(getNumbersFromString(priceNumber.getText()));
    }

    public double enterRingToNumberWithMultipleCheckbox(String number) {
        char[] array = number.toCharArray();
        for (int i = 0; i < ringToNumber.listInputRingToNumber.size(); i++) {
            switch (i) {
                case 0:
                    type(ringToNumber.listInputRingToNumber.get(i), array[0] + "" + array[1] + "" + array[2]);
                    break;
                case 1:
                    type(ringToNumber.listInputRingToNumber.get(i), array[3] + "" + array[4] + "" + array[5]);
                    break;
                case 2:
                    type(ringToNumber.listInputRingToNumber.get(i), array[6] + "" + array[7] + "" + array[8] + "" + array[9]);
                    break;
            }
        }
        ringToNumber.getCheckboxMultipleRingToNumber().click();
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(getNumbersFromString(priceNumber.getText()));
    }

    public void checkingOrderSummary (double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderSummary.getTitleOrderSummary());
        double priceRecurringMonthly = Math.round(Double.parseDouble(getNumbersFromString(orderSummary.getPriceRecurringMonthly().getText())));
        double actualResult = Math.round(priceMonthlyMinutes - (priceMonthlyMinutes * discountPriceSelectedPlan / 100) + priceNumber);
        softAssert.assertEquals(priceRecurringMonthly, actualResult);
        softAssert.assertAll();
    }

}
