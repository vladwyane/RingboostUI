package pages.admin;

import blocks.admin.orders.OrderDetail;
import blocks.admin.orders.OrdersTable;
import data.PromoCodes;
import data.Users;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

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

    public void checkingCorrectDataOrderRegularFlow(String displayedName, String subPriceOverride, String subPrice, String paymentPrice, String customerPrice,
                                                    String pricePlan, String pricePlanDiscount, String priceMonthlyMinutes, String amountMinutes, String pricePerMinute,
                                                    String planDuration, String ringToNumber, String payToday, String promoCodeValue, String promoCodeName,
                                                    String promoCodeType, String statusPay, Users users) {
        waiting2seconds();
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCompany()), "", "Company");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFirstName()), users.getFirstName(), "FirstName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getLastName()), users.getLastName(), "LastName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getContactPhoneNumber()), users.getPhone(), "ContactPhoneNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getEmail()), users.getEmail(), "Email");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneNumber()), "", "PhoneNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), paymentPrice, "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), customerPrice, "CustomerPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), subPrice, "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()), subPriceOverride, "CustomerSubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPayToday()), payToday, "PayToday");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), "$0", "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanActivationFee()), "$0", "PricePlanActivationFee");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPaymentMethod()), "", "PaymentMethod");
        softAssert.assertEquals(getAttributeValue(orderDetail.getBillingCity()), users.getCity(), "BillingCity");
        softAssert.assertEquals(getAttributeValue(orderDetail.getBillingName()), users.getFirstName() + users.getLastName(), "BillingName");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBillingState()), "", "BillingState");
        softAssert.assertEquals(getAttributeValue(orderDetail.getBillingAddress()), users.getStreetAddress(), "BillingAddress");
        softAssert.assertEquals(getAttributeValue(orderDetail.getBillingZIP()), users.getZipCode(), "BillingZIP");
        clickTab("Additional details");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), statusPay, "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getTimeStamps()), "", "TimeStamps");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneType()), "tollfree", "PhoneType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFlowType()), "vanity", "FlowType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubType()), "regular", "SubType");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBearer()), "", "Bearer");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsNationwide()), "yes", "IsNationwide");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsRegional()), "no", "IsRegional");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsFeature()), "no", "IsFeature");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsAuction()), "no", "IsAuction");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsPremium()), "no", "IsPremium");
/*        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDuration()), planDuration, "PricePlanDuration");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDiscount()), pricePlanDiscount, "PricePlanDiscount");*/
        softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCodeName + " - " + promoCodeValue + " " + promoCodeType, "PromoCodeName");
/*        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountValue()), promoCodeValue, "DiscountValue");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountType()), promoCodeType, "DiscountType");*/
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwner()), "", "Owner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwnerCommission()), "", "OwnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutes()), amountMinutes, "Minutes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutesPrice()), priceMonthlyMinutes, "MinutesPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePerMinute()), pricePerMinute, "PricePerMinute");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderPremiumFlow(String displayedName, String subPriceOverride, String subPrice, String pricePlan, String pricePlanDiscount, String priceMonthlyMinutes,
                                                    String amountMinutes, String pricePerMinute, String planDuration, String ringToNumber, String payToday,
                                                    String promoCodeValue, String promoCodeName, String promoCodeType, String owner, String ownerComm, String statusPay) {
        waiting2seconds();
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "", "PhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), "$0.00", "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), "$0.00", "CustomerPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), subPrice, "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()), subPriceOverride, "CustomerSubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPayToday()), payToday, "PayToday");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), "$0", "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanActivationFee()), "$0", "PricePlanActivationFee");
        clickTab("Additional details");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), "Completed", statusPay);
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getTimeStamps()), "", "TimeStamps");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneType()), "tollfree", "PhoneType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFlowType()), "vanity", "FlowType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubType()), "premium", "SubType");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBearer()), "", "Bearer");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsNationwide()), "no", "IsNationwide");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsRegional()), "yes", "IsRegional");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsFeature()), "no", "IsFeature");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsAuction()), "no", "IsAuction");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsPremium()), "yes", "IsPremium");
/*        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDuration()), planDuration, "PricePlanDuration");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDiscount()), pricePlanDiscount, "PricePlanDiscount");*/
        softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCodeName + " - " + promoCodeValue + " " + promoCodeType, "PromoCodeName");
/*        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountValue()), promoCodeValue, "DiscountValue");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountType()), promoCodeType, "DiscountType");*/
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwner()), owner, "Owner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwnerCommission()), ownerComm, "OwnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutes()), amountMinutes, "Minutes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutesPrice()), priceMonthlyMinutes, "MinutesPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePerMinute()), pricePerMinute, "PricePerMinute");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderBasic800Flow(String displayedName, String pricePlan, String subsPrice, String payToday, String additionalCost, String priceActivationFee,
                                                     String ringToNumber, String promoCodeValue, String promoCodeName, String promoCodeType, String statusPay) {
        waiting2seconds();
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "", "PhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), priceActivationFee, "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), "", "CustomerPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), subsPrice, "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()), "", "CustomerSubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPayToday()), payToday, "PayToday");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), additionalCost, "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanActivationFee()), priceActivationFee.substring(0, 4), "PricePlanActivationFee");
        clickTab("Additional details");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), statusPay, "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getTimeStamps()), "", "TimeStamps");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "", "PhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneType()), "tollfree", "PhoneType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFlowType()), "basic800", "FlowType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubType()), "", "SubType");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBearer()), "", "Bearer");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsNationwide()), "yes", "IsNationwide");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsRegional()), "no", "IsRegional");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsFeature()), "no", "IsFeature");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsAuction()), "no", "IsAuction");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsPremium()), "no", "IsPremium");
/*        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDuration()), "1", "PricePlanDuration");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDiscount()), "0", "PricePlanDiscount");*/
        softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCodeName + " - " + promoCodeValue + " " + promoCodeType, "PromoCodeName");
/*        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountValue()), promoCodeValue, "DiscountValue");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountType()), promoCodeType, "DiscountType");*/
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwner()), "", "Owner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwnerCommission()), "", "OwnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutes()), "", "Minutes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutesPrice()), "", "MinutesPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePerMinute()), "", "PricePerMinute");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderLocalFlow(String displayedName, String oldPrice, String customerPrice, String subscriptionPrice, String payToday, String phoneUpsellName,
                                                  String phoneUpsellPrice, String pricePlanName, String pricePlanDuration, String additionalCost, String ringToNumber,
                                                  String discountPromoCode, String promoCodeName, String promoCodeType, String statusPay) {
        waiting2seconds();
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "", "PhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), oldPrice, "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), customerPrice, "CustomerPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), subscriptionPrice, "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()), "$0.00", "CustomerSubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPayToday()), payToday, "PayToday");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneUpsellName()).toLowerCase(), phoneUpsellName.toLowerCase(), "PhoneUpsellName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneUpsellPrice()), phoneUpsellPrice, "PhoneUpsellPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlanName, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), additionalCost, "PricePlanAdditionalCost");
        clickTab("Additional details");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), statusPay, "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getLocalArea()), "", "Local Area");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getState()), "", "State");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getTimeStamps()), "", "TimeStamps");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "", "PhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCategory()), "", "Category");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneType()), "local", "PhoneType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFlowType()), "local", "FlowType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubType()), "", "SubType");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBearer()), "", "Bearer");
        //softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDuration()), pricePlanDuration, "PricePlanDuration");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCodeName + " - " + discountPromoCode + " " + promoCodeType, "PromoCodeName");
/*        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountValue()), discountPromoCode, "DiscountValue");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountType()), promoCodeType, "PromoCodeType");*/
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutes()), "", "Minutes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutesPrice()), "", "MinutesPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePerMinute()), "", "PricePerMinute");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertAll();
    }
}
