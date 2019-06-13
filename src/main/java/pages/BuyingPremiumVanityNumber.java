package pages;

import blocks.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.text.DecimalFormat;

public class BuyingPremiumVanityNumber extends BasePage {

    public BuyingPremiumVanityNumber(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    private AvailableByMarketOrNationwide availableByMarketOrNationwide;
    private DragAndDropBlock dragAndDropBlock;
    private AvailableAreaCodesBlock availableAreaCodesBlock;
    private SelectedAreaCodes selectedAreaCodes;
    private TermLengthBlock termLengthBlock;
    private SliderMonthlyMinutesBlock sliderMonthlyMinutesBlock;
    private RingToNumberBlock ringToNumberBlock;
    private OrderSummaryBlock orderSummaryBlock;

    @FindBy(xpath= "//button[contains(text(), 'continue')]")
    private WebElement continueButton;

    public void clickButtonChooseMyAreas() {
        waitUntilElementWillBeClickable(availableByMarketOrNationwide.getButtonSelectMyAreas());
        availableByMarketOrNationwide.getButtonSelectMyAreas().click();
    }

    public void clickButtonChooseNationwide() {
        waitUntilElementWillBeClickable(availableByMarketOrNationwide.getButtonSelectNationwide());
        availableByMarketOrNationwide.getButtonSelectNationwide().click();
    }

    public void chooseState(String stateName) {
        waitUntilElementAppeared(dragAndDropBlock.getSelectState());
        scrollToElement(dragAndDropBlock.getSelectState());
        dragAndDropBlock.getSelectState().click();
        waitUntilElementAppeared(dragAndDropBlock.getListStates().get(0));
        for(WebElement element : dragAndDropBlock.getListStates()) {
            if (element.getText().equals(stateName) || element.equals(dragAndDropBlock.getListStates().get(dragAndDropBlock.getListStates().size() - 1))) {
                element.click();
                return;
            }
        }
    }

    public double chooseByOneAreaCodesFromSeveralStates(String[] stateNames) {
        double priceFromAmountAreaCodes = 0.0;
        for (int i = 0; i < stateNames.length; i++) {
            waitUntilElementAppeared(dragAndDropBlock.getSelectState());
            scrollToElement(dragAndDropBlock.getSelectState());
            dragAndDropBlock.getSelectState().click();
            waitUntilElementAppeared(dragAndDropBlock.getListStates().get(0));
            for(WebElement element : dragAndDropBlock.getListStates()) {
                if (element.getText().equals(stateNames[i]) || element.equals(dragAndDropBlock.getListStates().get(dragAndDropBlock.getListStates().size() - 1))) {
                    element.click();
                    break;
                }
            }
            waiting2seconds();
            waitUntilElementAppeared(availableAreaCodesBlock.getDragBoxTitle());
            waitUntilElementWillBeClickable(availableAreaCodesBlock.getListAreaCodes().get(0));
            availableAreaCodesBlock.getListAreaCodes().get(0).click();
            priceFromAmountAreaCodes = priceFromAmountAreaCodes + Double.parseDouble(getNumbersFromString(selectedAreaCodes.getListPricesSelectedAreaCodes().get(i).getText()));
        }
        switch (Integer.parseInt(selectedAreaCodes.getQuantityAreaCodes().getText())) {
            case 1:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 1;
                break;
            case 2:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.9;
                break;
            case 3:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.8;
                break;
            case 4:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.7;
                break;
            default: priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.6;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceFromAmountAreaCodes).replace(',', '.');
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(dx);
    }

    public double chooseSeveralAreaCodesFromSeveralStates(String[] stateNames, int[] quantity) {
        double priceFromAmountAreaCodes = 0.0;
        for (int i = 0; i < stateNames.length; i++) {
            waitUntilElementAppeared(dragAndDropBlock.getSelectState());
            scrollToElement(dragAndDropBlock.getSelectState());
            dragAndDropBlock.getSelectState().click();
            waitUntilElementAppeared(dragAndDropBlock.getListStates().get(0));
            for(WebElement element : dragAndDropBlock.getListStates()) {
                if (element.getText().equals(stateNames[i]) || element.equals(dragAndDropBlock.getListStates().get(dragAndDropBlock.getListStates().size() - 1))) {
                    element.click();
                    break;
                }
            }
            waiting2seconds();
            waitUntilElementAppeared(availableAreaCodesBlock.getDragBoxTitle());
            waitUntilElementWillBeClickable(availableAreaCodesBlock.getListAreaCodes().get(0));
            for (int j = 0; j < quantity[i]; j++) {
                availableAreaCodesBlock.getListAreaCodes().get(j).click();
                priceFromAmountAreaCodes = priceFromAmountAreaCodes + Double.parseDouble(getNumbersFromString(selectedAreaCodes.getListPricesSelectedAreaCodes().get(j).getText()));
            }
        }
        switch (Integer.parseInt(selectedAreaCodes.getQuantityAreaCodes().getText())) {
            case 1:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 1;
                break;
            case 2:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.9;
                break;
            case 3:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.8;
                break;
            case 4:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.7;
                break;
            default: priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.6;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceFromAmountAreaCodes).replace(',', '.');
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(dx);
    }

    public double chooseFirstAreaCodeFromList() {
        waitUntilElementAppeared(availableAreaCodesBlock.getDragBoxTitle());
        waitUntilElementWillBeClickable(availableAreaCodesBlock.getListAreaCodes().get(0));
        availableAreaCodesBlock.getListAreaCodes().get(0).click();
        double price = Double.parseDouble(getNumbersFromString(selectedAreaCodes.getListPricesSelectedAreaCodes().get(0).getText()));
        return price;
    }

    public double chooseSeveralAreaCodesFromList(int amount) {
        waitUntilElementAppeared(availableAreaCodesBlock.getDragBoxTitle());
        waitUntilElementWillBeClickable(availableAreaCodesBlock.getListAreaCodes().get(0));
        double price = 0.0;
        for (int i = 0; i < availableAreaCodesBlock.getListAreaCodes().size(); i++) {
            if(i >= amount)
                break;
            availableAreaCodesBlock.getListAreaCodes().get(i).click();
            price = price + Double.parseDouble(getNumbersFromString(selectedAreaCodes.getListPricesSelectedAreaCodes().get(i).getText()));
        }

        return price;
    }

    public double getPriceFromAmountAreaCodesWithDiscount(double priceFromAmountAreaCodes) {
        switch (Integer.parseInt(selectedAreaCodes.getQuantityAreaCodes().getText())) {
            case 1:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 1;
                break;
            case 2:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.9;
                break;
            case 3:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.8;
                break;
            case 4:
                priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.7;
                break;
            default: priceFromAmountAreaCodes = priceFromAmountAreaCodes * 0.6;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceFromAmountAreaCodes).replace(',', '.');
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(dx);
    }

    public int chooseTermLength(String term) {
        waitUntilElementWillBeClickable(termLengthBlock.listCardButtons.get(0));
        int discount;
        for (int i = 0; i < termLengthBlock.listPlaneName.size(); i++) {
            if(termLengthBlock.listPlaneName.get(i).getText().equals(term)) {
                discount = Integer.parseInt(getNumbersFromString(termLengthBlock.listOfDiscount.get(i).getText()));
                termLengthBlock.listCardButtons.get(i).click();
                return discount;
            }
        }
        termLengthBlock.listCardButtons.get(0).click();
        discount = 0;
        return discount;
    }

    public double choose5000MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutesBlock.getBulletOfSlider());
        scrollToElement(sliderMonthlyMinutesBlock.getBulletOfSlider());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(sliderMonthlyMinutesBlock.getBulletOfSlider(), 300, 0).build();
        actionFirstBull.perform();
        changeAttributeValueWithJS(sliderMonthlyMinutesBlock.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutesBlock.getTooltipPrice().getText()));
        continueButton.click();
        return price;
    }

    public double choose750MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutesBlock.getBulletOfSlider());
        scrollToElement(sliderMonthlyMinutesBlock.getBulletOfSlider());
        Actions move = new Actions(driver);
        Action actionFirstBull = move.dragAndDropBy(sliderMonthlyMinutesBlock.getBulletOfSlider(), 150, 0).build();
        actionFirstBull.perform();
        changeAttributeValueWithJS(sliderMonthlyMinutesBlock.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutesBlock.getTooltipPrice().getText()));
        continueButton.click();
        return price;
    }

    public double choose100MonthlyMinutes() {
        waitUntilElementWillBeClickable(sliderMonthlyMinutesBlock.getBulletOfSlider());
        scrollToElement(sliderMonthlyMinutesBlock.getBulletOfSlider());
        changeAttributeValueWithJS(sliderMonthlyMinutesBlock.getSliderTooltip(), "class", "vue-slider-dot-tooltip-show");
        double price = Double.parseDouble(getNumbersFromString(sliderMonthlyMinutesBlock.getTooltipPrice().getText()));
        continueButton.click();
        return price;
    }

    public void chooseCheckboxMultipleRingToNumber() {
        ringToNumberBlock.getCheckboxMultipleRingToNumber().click();
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
    }

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

    public void goToCheckout() {
        waitUntilElementAppeared(orderSummaryBlock.getButtonProceedToCheckout());
        orderSummaryBlock.getButtonProceedToCheckout().click();
    }





}
