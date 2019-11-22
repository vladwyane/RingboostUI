package pages.front;

import blocks.front.OrderConfirmationBlock;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.io.IOException;
import java.text.DecimalFormat;

public class OrderConfirmationPage extends BasePage {

    private OrderConfirmationBlock orderConfirmationBlock;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    public void waitUntilConfirmationMessageAppears() {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
    }

    public void checkingYourPurchase (double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseWithFixedPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "promoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseWithPercentPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "PriceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseAfterRemovePromoCode (double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
       // double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
       // softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseWithHighFixedPromoCode(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
       // double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
      //  softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, 0.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    //Checking for Local Numbers

    public void checkingYourPurchasePortNumber (double priceNumber, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchasePortNumberWithFixedPromoCode(double priceNumber, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchasePortNumberWithHighFixedPromoCode(double priceNumber, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, 0.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchasePortNumberWithPercentPromoCode(double priceNumber, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchasePortNumberAfterRemovePromoCode (double priceNumber, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber  + pricePlan;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumber (double priceNumber, double pricePlan) throws IOException, JSONException{
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
       // double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceNumber).replace(',', '.');
        priceNumber = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
     //   softAssert.assertEquals(priceRecurringMonthly, pricePlan, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, priceNumber + Math.round(pricePlan * 100.0) / 100.0, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumberWithFixedPromoCode(double priceNumber, double pricePlan) throws JSONException, IOException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0,"pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumberWithHighFixedPromoCode(double priceNumber, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan  * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, 0.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumberWithPercentPromoCode(double priceNumber, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseParkNumberAfterRemovePromoCode (double priceNumber, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceNumber).replace(',', '.');
        priceNumber = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceRecurringMonthly, pricePlan, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, priceNumber + Math.round(pricePlan * 100.0) / 100.0, "priceTotalDueToday is incorrect");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseLocalNumbersPickPlan (double priceNumber, double pricePlan, double perMonthPrice) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double priceRecurringMonthly = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(1).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(priceNumber).replace(',', '.');
        priceNumber = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(priceRecurringMonthly, pricePlan, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, priceNumber + Math.round(pricePlan / 2 * 100.0) / 100.0, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    //Checking for Basic800 Numbers

    public void checkingYourPurchaseBasic800Number (double priceActivationFee, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan * 100.0) / 100.0;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseBasic800NumberWithFixedPromoCode(double priceActivationFee, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0,"pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseBasic800NumberWithHighFixedPromoCode(double priceActivationFee, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan  * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, 0.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseBasic800NumberWithPercentPromoCode(double priceActivationFee, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertAll();
    }

    public void checkingYourPurchaseBasic800NumberAfterRemovePromoCode (double priceActivationFee, double pricePlan) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceActivationFee + Math.round(pricePlan * 100.0) / 100.0;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPriceAfterAppliedPromoCode()), "PricePromoCode is present");
        softAssert.assertFalse(isElementPresent(orderConfirmationBlock.getPricePayToday()), "PricePayToday is present");
        softAssert.assertAll();
    }

    //Generated link in admin panel

    public void checkingGeneratedLinkWithoutPromoCodeRegularFlow(double priceMonthlyMinutes, int discountPriceSelectedPlan,
                                                                 double priceNumber, boolean isPromocode, String displayedName) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertFalse(isPromocode, "promocode is still present");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(), displayedName, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinkWithFixedPromoCodeRegularFlow(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "promoCode is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(),displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinkWithPercentPromoCodeRegularFlow(double priceMonthlyMinutes, int discountPriceSelectedPlan, double priceNumber, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        double actualResult = priceMonthlyMinutes + priceNumber - (priceMonthlyMinutes + priceNumber) * discountPriceSelectedPlan * 0.01 ;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "PriceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(),displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinkWithoutPromoCodePremiumFlow(double pricePayToday, boolean isPromocode, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = pricePayToday;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertFalse(isPromocode, "promocode is still present");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(),displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinkWithFixedPromoCodePremiumFlow(double price, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        double actualResult = price;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, fixedPromocode, "promoCode is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(),displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinkWithPercentPromoCodePremiumFlow(double price, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        //double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        double actualResult = price;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        //softAssert.assertEquals(priceRecurringMonthly, actualResult, "PriceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(),displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinkWithHighFixedPromoCodePremiumFlow(double price, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        // double priceRecurringMonthly = Double.parseDouble(getNumbersFromString(orderConfirmationBlock.getPriceRecurringMonthly().getText()));
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        double actualResult = price;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        //  softAssert.assertEquals(priceRecurringMonthly, actualResult, "priceRecurringMonthly is incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(pricePayToday, 0.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(),displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinkWithoutPromoCodePortNumber(double priceNumber, boolean isPromocode) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFile();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber;
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        softAssert.assertFalse(isPromocode, "promocode is still present");
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinParkNumberWithFixedPromoCode(double priceNumber, double pricePlan, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - pricePromoCode) * 100.0) / 100.0,"pricePayToday is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(), displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinParkNumberWithPercentPromoCode(double priceNumber, double pricePlan, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase", "Title incorrect");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "PriceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, Math.round((actualResult - (actualResult * percentPromocode / 100)) * 100.0) / 100.0, "PricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, Math.round(actualResult * percentPromocode / 100 * 100.0) / 100.0, "PricePromoCode is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(), displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }

    public void checkingGeneratedLinParkNumberWithHighFixedPromoCode(double priceNumber, double pricePlan, String displayedNumber) throws IOException, JSONException {
        waitUntilElementAppeared(orderConfirmationBlock.getOrderTitle());
        waitUntilElementWillBeClickable(orderConfirmationBlock.getLinkOrderDetails());
        orderConfirmationBlock.getLinkOrderDetails().click();
        waiting2seconds();
        orderConfirmationBlock.generateOrdersDetailFileWthPromo();
        double priceTotalDueToday = Double.parseDouble(orderConfirmationBlock.getPriceTotalDueToday().getText().substring(1).replaceAll(",", ""));
        double actualResult = priceNumber + Math.round(pricePlan  * 100.0) / 100.0;
        double pricePayToday = Double.parseDouble(orderConfirmationBlock.getPricePayToday().getText().substring(1).replaceAll(",", ""));
        double pricePromoCode = Double.parseDouble(orderConfirmationBlock.getPriceAfterAppliedPromoCode().getText().substring(2).replaceAll(",", ""));
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(actualResult).replace(',', '.');
        actualResult = Double.parseDouble(dx);
        System.out.println("priceTotalDueToday = " + priceTotalDueToday);
        System.out.println("pricePayToday = " + pricePayToday);
        System.out.println("pricePromoCode = " + pricePromoCode);
        softAssert.assertEquals(titleH1.getText(), "Thank You for Your Purchase");
        softAssert.assertEquals(Math.round(priceTotalDueToday * 100.0) / 100.0, actualResult, "priceTotalDueToday is incorrect");
        softAssert.assertEquals(Math.round(pricePayToday * 100.0) / 100.0, 0.0, "pricePayToday is incorrect");
        softAssert.assertEquals(pricePromoCode, highFixedPromocode, "pricePromoCode is incorrect");
        softAssert.assertEquals(orderConfirmationBlock.getPhoneNumber().getText(), displayedNumber, "displayedName is incorrect");
        softAssert.assertAll();
    }


}
