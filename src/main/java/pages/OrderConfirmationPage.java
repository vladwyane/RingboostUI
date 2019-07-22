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
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseWithFixedPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "promoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseWithPercentPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "PriceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseAfterRemovePromoCode (double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
       // double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
       // softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseWithHighFixedPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
       // double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
      //  softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, 0.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    //Checking for Local Numbers

    public void checkingYourPurchasePortNumber (double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchasePortNumberWithFixedPromoCode(double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchasePortNumberWithHighFixedPromoCode(double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, 0.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchasePortNumberWithPercentPromoCode(double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchasePortNumberAfterRemovePromoCode (double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumber (double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
       // double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceNumber).replace(',', '.');
        priceNumber = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
     //   softAssert.assertEquals(priceRecurringMonthly, pricePlan, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, priceNumber + Math.round(pricePlan * 100.0) / 100.0, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumberWithFixedPromoCode(double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0,"pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumberWithHighFixedPromoCode(double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan  * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, 0.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumberWithPercentPromoCode(double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumberAfterRemovePromoCode (double priceNumber, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceNumber).replace(',', '.');
        priceNumber = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceRecurringMonthly, pricePlan, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, priceNumber + Math.round(pricePlan * 100.0) / 100.0, "priceTotalDueToday is incorrect");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseLocalNumbersPickPlan (double priceNumber, double pricePlan, double perMonthPrice) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceNumber).replace(',', '.');
        priceNumber = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceRecurringMonthly, pricePlan, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(priceTotalDueToday, priceNumber + Math.round(pricePlan / 2 * 100.0) / 100.0, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    //Checking for Basic800 Numbers

    public void checkingYourPurchaseBasic800Number (double priceActivationFee, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan * 100.0) / 100.0;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseBasic800NumberWithFixedPromoCode(double priceActivationFee, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0,"pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseBasic800NumberWithHighFixedPromoCode(double priceActivationFee, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan  * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, 0.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseBasic800NumberWithPercentPromoCode(double priceActivationFee, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPricePayToday().getText()));
        double pricePromoCode = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseBasic800NumberAfterRemovePromoCode (double priceActivationFee, double pricePlan) {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan * 100.0) / 100.0;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceTotalDueToday, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

}
