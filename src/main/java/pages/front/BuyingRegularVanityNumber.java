package pages.front;

import blocks.front.OrderSummaryBlock;
import blocks.front.RingToNumberBlock;
import blocks.front.SliderMonthlyMinutesBlock;
import blocks.front.TermLengthBlock;
import data.PricingTollFreeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.text.DecimalFormat;


public class BuyingRegularVanityNumber extends BasePage {

    public BuyingRegularVanityNumber(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    private SliderMonthlyMinutesBlock sliderMonthlyMinutesBlock;
    private TermLengthBlock termLengthBlock;
    private RingToNumberBlock ringToNumberBlock;
    private OrderSummaryBlock orderSummaryBlock;

    @FindBy(css= ".text-center button")
    private WebElement continueButton;

    @FindBy(css= ".step-details p")
    private WebElement stepDetailDescription;

    @FindBy(css= ".title-price")
    private WebElement priceNumber;

    @FindBy(css= ".main-number-holder")
    private WebElement phoneNumber;

    public WebElement getPhoneNumber() {
        waitUntilElementAppeared(phoneNumber);
        return phoneNumber;
    }

    public double choose5000MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutesBlock.getBulletOfSlider());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(sliderMonthlyMinutesBlock.getBulletOfSlider(), 2000, 0).build();
        actionFirstBull.perform();
        changeAttributeValueWithJS(sliderMonthlyMinutesBlock.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutesBlock.getTooltipPrice().getText()));
        continueButton.click();
        return price;
    }

    public double choose250MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutesBlock.getBulletOfSlider());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(sliderMonthlyMinutesBlock.getBulletOfSlider(), 300, 0).build();
        actionFirstBull.perform();
        changeAttributeValueWithJS(sliderMonthlyMinutesBlock.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutesBlock.getTooltipPrice().getText()));
        continueButton.click();
        return price;
    }

    public double choose100MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutesBlock.getBulletOfSlider());
        changeAttributeValueWithJS(sliderMonthlyMinutesBlock.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutesBlock.getTooltipPrice().getText()));
        continueButton.click();
        return price;
    }

    public int getAmountMinutes(double priceOfMinute) {
        int amountMinute;
        switch((int)priceOfMinute) {
            case 15:
                amountMinute = 250;
                break;
            case 30:
                amountMinute = 500;
                break;
            case 45:
                amountMinute = 750;
                break;
            case 50:
                amountMinute = 1000;
                break;
            case 100:
                amountMinute = 2000;
                break;
            case 255:
                amountMinute = 5000;
                break;
            default:
                amountMinute = 100;
        }
        return amountMinute;
    }


    public void checkingCreatedPriceMinuteFromAdmin(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementWillBeClickable(sliderMonthlyMinutesBlock.getBulletOfSlider());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(sliderMonthlyMinutesBlock.getBulletOfSlider(), 2000, 0).build();
        actionFirstBull.perform();
        changeAttributeValueWithJS(sliderMonthlyMinutesBlock.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutesBlock.getTooltipPrice().getText()));
        softAssert.assertEquals(Double.parseDouble(pricingTollFreeSettings.getPrice()), price);
        softAssert.assertAll();
    }

    public int chooseTermLength(String term) {
        waitUntilElementWillBeClickable(termLengthBlock.listCardButtons.get(0));
        int discount;
        for (int i = 0; i < termLengthBlock.listPlaneName.size(); i++) {
            if(termLengthBlock.listPlaneName.get(i).getText().toLowerCase().equals(term)) {
                discount = Integer.parseInt(getNumbersFromString(termLengthBlock.listOfDiscount.get(i).getText()));
                termLengthBlock.listCardButtons.get(i).click();
                return discount;
            }
        }
        termLengthBlock.listCardButtons.get(0).click();
        discount = 0;
        return discount;
    }

    public int getPricePlanDuration(int pricePlanSale) {
        int duration;
        switch(pricePlanSale) {
            case 10:
                duration = 12;
                break;
            case 20:
                duration = 24;
                break;
            default:
                duration = 1;
        }
        return duration;
    }

    public void checkingCreatedTermFromAdmin(PricingTollFreeSettings pricingTollFreeSettings) {
        waitUntilElementWillBeClickable(termLengthBlock.listCardButtons.get(0));
        String discount = null;
        boolean termPresent = false;
        for (int i = 0; i < termLengthBlock.listPlaneName.size(); i++) {
            if(termLengthBlock.listPlaneName.get(i).getText().toLowerCase().equals(pricingTollFreeSettings.getName())) {
                discount = getNumbersFromString(termLengthBlock.listOfDiscount.get(i).getText());
                termPresent = true;
                break;
            }
        }
        softAssert.assertEquals(pricingTollFreeSettings.getDiscount(), discount);
        softAssert.assertTrue(termPresent, "Term not found");
        softAssert.assertAll();
    }

    public double chooseCheckboxMultipleRingToNumber() {
        ringToNumberBlock.getCheckboxMultipleRingToNumber().click();
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(getNumbersFromString(priceNumber.getText().replaceAll("[^0-9?!\\\\.]","")));
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
        return Double.parseDouble(getNumbersFromString(priceNumber.getText().replaceAll("[^0-9?!\\\\.]","")));
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
        return Double.parseDouble(getNumbersFromString(priceNumber.getText().replaceAll("\\D+","")));
    }

    public void checkingOrderSummary (double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderSummaryBlock.getTitleOrderSummary());
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderSummaryBlock.getPriceRecurringMonthly().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult);
        actualResult = Double.valueOf(dx);
        softAssert.assertEquals(priceRecurringMonthly, actualResult);
        softAssert.assertAll();
    }

    public void goToCheckout() {
        waitUntilElementAppeared(orderSummaryBlock.getButtonProceedToCheckout());
        scrollToElement(orderSummaryBlock.getButtonProceedToCheckout());
        orderSummaryBlock.getButtonProceedToCheckout().click();
    }


}
