package pages.admin;

import blocks.admin.orders.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Users;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.io.*;
import java.util.Date;

/**
 * Created by bigdrop on 11/21/2019.
 */
public class OrderDetailPage extends BasePage {

    public OrderDetailPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    OrderDetail orderDetail;

    public void clickTab(String tabName) {
        waiting2seconds();
        waitUntilElementAppeared(orderDetail.getListOfTabs().get(0));
        for(WebElement element : orderDetail.getListOfTabs()) {
            if (element.getText().equals(tabName.toUpperCase())) {
                element.click();
                return;
            }
        }
    }

    public void checkingCorrectDataOrderVanityNumbers(String displayedName, String subPriceOverride, String subPrice, String paymentPrice, String customerPrice,
                                                      String pricePlan, String minutesPackage, String pricePerMinute, String ringToNumber, String payToday,
                                                      String promoCode, String statusPay, Users users, String owner, String ownerComission) throws IOException {
        waiting2seconds();
        generateOrdersDetailFile(displayedName, subPriceOverride, subPrice, paymentPrice, customerPrice, pricePlan, minutesPackage, payToday, promoCode);
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCompany()), "", "Company");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFirstName()), users.getFirstName(), "FirstName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getLastName()), users.getLastName(), "LastName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getContactPhoneNumber()), users.getPhone(), "ContactPhoneNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getEmail()), users.getEmail(), "Email");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneNumber()), "", "PhoneNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), paymentPrice, "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()).replaceAll(",", ""), subPrice, "SubscriptionPrice");
        if(subPriceOverride.equals("") && customerPrice.equals("")) {}
        else {
            softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()).replaceAll(",", ""), subPriceOverride, "CustomerSubscriptionPrice");
            softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), customerPrice, "CustomerPrice");
        }
        softAssert.assertEquals(getAttributeValue(orderDetail.getPayToday()), payToday, "PayToday");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), "$0", "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanActivationFee()), "$0", "PricePlanActivationFee");
        if(statusPay.equals("Completed")) {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getPaymentMethod()), "", "PaymentMethod");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingCity()), users.getCity(), "BillingCity");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingName()), users.getFirstName() + " " + users.getLastName(), "BillingName");
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getBillingState()), "", "BillingState");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingAddress()), users.getStreetAddress(), "BillingAddress");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingZIP()), users.getZipCode(), "BillingZIP");
        }
        clickTab("Order details");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        //softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), statusPay, "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        if(statusPay.equals("Completed")) {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getCompletedAt()), "", "CompletedAt");
        }
        else  {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getFailedAt()), "", "FailedAt");
        }

        if(promoCode.equals("")) {}
        else  {
            softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCode, "PromoCode");
        }
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwner()), owner, "Owner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwnerCommission()), ownerComission, "OwnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutes()), minutesPackage, "Minutes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePerMinute()), pricePerMinute, "PricePerMinute");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderBasic800Flow(String displayedName, String pricePlan, String subsPrice, String paymentPrice, String payToday, String additionalCost, String priceActivationFee,
                                                     String ringToNumber, String promoCode, String statusPay, Users users) throws IOException {
        waiting2seconds();
        generateOrdersDetailFile(displayedName, "", subsPrice, paymentPrice, "", pricePlan, "", payToday, promoCode);
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCompany()), "", "Company");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFirstName()), users.getFirstName(), "FirstName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getLastName()), users.getLastName(), "LastName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getContactPhoneNumber()), users.getPhone(), "ContactPhoneNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getEmail()), users.getEmail(), "Email");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneNumber()), "", "PhoneNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), paymentPrice, "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()).replaceAll(",", ""), subsPrice, "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPayToday()), payToday, "PayToday");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), additionalCost, "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanActivationFee()), priceActivationFee, "PricePlanActivationFee");
        if(statusPay.equals("Completed")) {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getPaymentMethod()), "", "PaymentMethod");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingCity()), users.getCity(), "BillingCity");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingName()), users.getFirstName() + " " + users.getLastName(), "BillingName");
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getBillingState()), "", "BillingState");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingAddress()), users.getStreetAddress(), "BillingAddress");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingZIP()), users.getZipCode(), "BillingZIP");
        }
        clickTab("Order details");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        //softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), statusPay, "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        if(statusPay.equals("Completed")) {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getCompletedAt()), "", "CompletedAt");
        }
        else  {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getFailedAt()), "", "FailedAt");
        }

        if(promoCode.equals("")) {}
        else  {
            softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCode, "PromoCode");
        }
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertAll();
    }


    public void checkingCorrectDataOrderLocalFlow(String displayedName, String oldPrice, String customerPrice, String subscriptionPrice, String subPriceOverride, String payToday,
                                                  String phoneUpsellName, String phoneUpsellPrice, String pricePlan, String additionalCost, String ringToNumber,
                                                  String promoCode, String statusPay, Users users) throws IOException {
        waiting2seconds();
        generateOrdersDetailFile(displayedName, subPriceOverride, subscriptionPrice, oldPrice, customerPrice, pricePlan, "", payToday, promoCode);
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCompany()), "", "Company");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFirstName()), users.getFirstName(), "FirstName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getLastName()), users.getLastName(), "LastName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getContactPhoneNumber()), users.getPhone(), "ContactPhoneNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getEmail()), users.getEmail(), "Email");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneNumber()), "", "PhoneNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), oldPrice, "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneUpsellName()).toLowerCase(), phoneUpsellName.toLowerCase(), "PhoneUpsellName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneUpsellPrice()), phoneUpsellPrice, "PhoneUpsellPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()).replaceAll(",", ""), subscriptionPrice, "SubscriptionPrice");
        if(customerPrice.equals("")) {}
        else {
            softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()).replaceAll(",", ""), subPriceOverride, "CustomerSubscriptionPrice");
            softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), customerPrice, "CustomerPrice");
        }
        softAssert.assertEquals(getAttributeValue(orderDetail.getPayToday()), payToday, "PayToday");
        if(pricePlan.equals("")) {}
        else {
            softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
            softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), additionalCost, "PricePlanAdditionalCost");
        }
        if(statusPay.equals("Completed")) {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getPaymentMethod()), "", "PaymentMethod");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingCity()), users.getCity(), "BillingCity");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingName()), users.getFirstName() + " " + users.getLastName(), "BillingName");
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getBillingState()), "", "BillingState");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingAddress()), users.getStreetAddress(), "BillingAddress");
            softAssert.assertEquals(getAttributeValue(orderDetail.getBillingZIP()), users.getZipCode(), "BillingZIP");
        }
        clickTab("Order details");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        //softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), statusPay, "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        if(statusPay.equals("Completed")) {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getCompletedAt()), "", "CompletedAt");
        }
        else  {
            softAssert.assertNotEquals(getAttributeValue(orderDetail.getFailedAt()), "", "FailedAt");
        }

        if(promoCode.equals("")) {}
        else  {
            softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCode, "PromoCode");
        }
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertAll();
    }

    public void generateOrdersDetailFile(String displayedName, String subPriceOverride, String subPrice, String paymentPrice, String customerPrice,
                                         String pricePlan, String minutesPackage, String payToday, String promoCode) throws IOException, JSONException {
        File file = new File("src/main/resources/ordersDetail.json");
        JSONObject orderDetailData = generateOrdersDetailData(displayedName, subPriceOverride, subPrice, paymentPrice, customerPrice,
                pricePlan, minutesPackage, payToday, promoCode);
        save(orderDetailData, file);
    }

    private void save(JSONObject orderDetailList, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(orderDetailList);
        Writer writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(json);
        writer.close();
    }

    private JSONObject generateOrdersDetailData(String displayedName, String subPriceOverride, String subPrice, String paymentPrice, String customerPrice,
                                                String pricePlan, String minutesPackage, String payToday, String promoCode) throws JSONException {
        JSONObject orderDetail = new JSONObject();
        orderDetail.put("Displayed Name", displayedName);
        orderDetail.put("Subscription Price Override", subPriceOverride);
        orderDetail.put("Subscription Price", subPrice);
        orderDetail.put("Payment Price Override", customerPrice);
        orderDetail.put("Payment Price", paymentPrice);
        orderDetail.put("Pay Today", payToday);
        orderDetail.put("Price Plan", pricePlan);
        orderDetail.put("Minutes", minutesPackage);
        orderDetail.put("Promo Code", promoCode);
        orderDetail.put("currentDate", new Date().toString());
        return orderDetail;
    }
}
