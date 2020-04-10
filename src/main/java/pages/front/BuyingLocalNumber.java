package pages.front;

import blocks.front.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

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

    @FindBy(css= ".make-offer-heading")
    private WebElement makeOfferHeading;

    @FindBy(css= ".make-offer-wrap form")
    private WebElement makeOfferForm;

    public WebElement getPhoneNumber() {
        waitUntilElementAppeared(phoneNumber);
        return phoneNumber;
    }

    @Step("Select upsell plan: {0}")
    public double getPhoneUpsellPrice(String planName) {
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
        return Double.parseDouble(getNumbersFromString(priceNumber.getText().replaceAll(",","")));
    }


    @Step("Click continue to checkout")
    public String goToCheckout() {
        waitUntilElementAppeared(orderSummaryBlock.getButtonProceedToCheckout());
        String boughtNumber = phoneNumber.getText();
        boughtNumber = boughtNumber.replaceAll("-","");
 //       boughtNumber = boughtNumber.replaceAll("\\D+","");
        orderSummaryBlock.getButtonProceedToCheckout().click();
        return boughtNumber;
    }

    @Step("Select your monthly plan: {0}")
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

    public double getAdditionalCoast(String planName) {
        waitUntilElementWillBeClickable(pickYourMonthlyPlanBlock.listCardButtons.get(0));
        double additionalCoast;
        for (int i = 0; i < pickYourMonthlyPlanBlock.listPlaneName.size(); i++) {
            if(pickYourMonthlyPlanBlock.listPlaneName.get(i).getText().equals(planName)) {
                additionalCoast = Double.parseDouble(getNumbersFromString(pickYourMonthlyPlanBlock.listOfAdditionalCost.get(i).getText()));
                return additionalCoast;
            }
        }
        additionalCoast = 0.0;
        return additionalCoast;
    }

    public String getDescriptionPlan(String planName) {
        waitUntilElementWillBeClickable(pickYourMonthlyPlanBlock.listCardButtons.get(0));
        String description;
        for (int i = 0; i < pickYourMonthlyPlanBlock.listPlaneName.size(); i++) {
            if(pickYourMonthlyPlanBlock.listPlaneName.get(i).getText().equals(planName)) {
                description = pickYourMonthlyPlanBlock.listOfPlanDescription.get(i).getText();
                return description;
            }
        }
        description = null;
        return description;
    }

    @Step("Choose checkbox Multiple Ring To Number")
    public double chooseCheckboxMultipleRingToNumber() {
        ringToNumberBlock.getCheckboxMultipleRingToNumber().click();
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return Double.parseDouble(getNumbersFromString(priceNumber.getText().replaceAll(",", "")));
    }

    @Step("Enter your ring-to-number: {0}")
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
        return Double.parseDouble(getNumbersFromString(priceNumber.getText().replaceAll("\\D+","")));
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

    @Step("Click Link Continue To Checkout")
    public void clickLinkContinueToCheckout() {
        waitUntilElementWillBeClickable(localSupportSolutionBlock.getLinkToCheckout());
        localSupportSolutionBlock.getLinkToCheckout().click();
    }

    @Step("Checking Visibility Make Offer Form")
    public void checkingVisibilityMakeOfferForm() throws InterruptedException {
        softAssert.assertTrue(isElementPresent(makeOfferHeading), "Make Offer Heading is absent");
        softAssert.assertTrue(isElementPresent(makeOfferForm), "Make Offer Form is absent");
        softAssert.assertAll();
    }
}
