package pages;

import blocks.OrderConfirmationBlock;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;

public class OrderConfirmationPage extends BasePage {

    private OrderConfirmationBlock orderConfirmationBlock;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void checkingYourPurchase (double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceTotalDueToday().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceRecurringMonthly, actualResult);
        softAssert.assertEquals(priceTotalDueToday, Math.round(actualResult / 2 * 100.0) / 100.0);
        softAssert.assertAll();
    }


    public void checkingYourPurchaseWithFixedPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceTotalDueToday().getText()));
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceRecurringMonthly, actualResult);
        softAssert.assertEquals(priceTotalDueToday, Math.round(actualResult / 2 * 100.0) / 100.0);
        softAssert.assertEquals(pricePayToday, Math.round((actualResult / 2 - pricePromoCode) * 100.0) / 100.0);
        softAssert.assertEquals(pricePromoCode, 5.0);
        softAssert.assertAll();
    }

    public void checkingYourPurchaseWithPercentPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceTotalDueToday().getText()));
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(priceRecurringMonthly, actualResult, "PriceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, Math.round(actualResult / 2 * 100.0) / 100.0, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult / 2 - pricePromoCode) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * 0.1 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseAfterRemovePromoCode (double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceTotalDueToday().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceRecurringMonthly, actualResult);
        softAssert.assertEquals(priceTotalDueToday, Math.round(actualResult / 2 * 100.0) / 100.0);
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseWithHighFixedPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceTotalDueToday().getText()));
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceRecurringMonthly, actualResult);
        softAssert.assertEquals(priceTotalDueToday, Math.round(actualResult / 2 * 100.0) / 100.0);
        softAssert.assertEquals(pricePayToday, 0.5);
        softAssert.assertEquals(pricePromoCode, 50.0);
        softAssert.assertAll();
    }


}
