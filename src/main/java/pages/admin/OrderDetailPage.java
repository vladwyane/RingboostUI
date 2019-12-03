package pages.admin;

import blocks.admin.orders.OrderDetail;
import blocks.admin.orders.OrdersTable;
import data.PromoCodes;
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

    public void checkingCorrectStructureDataLocalOrder() {
        waitUntilElementAppeared(orderDetail.getListOfSubHeaders().get(0));
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(0).getText(), "Actions");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(1).getText(), "Phone Number");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(2).getText(), "Carrier");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(3).getText(), "Amount Per Month");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(4).getText(), "Total Amount");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(5).getText(), "Public IDarrow_upward");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(6).getText(), "Status");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(7).getText(), "Date");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(8).getText(), "Customer company");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(9).getText(), "Customer name");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(10).getText(), "Customer email");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(11).getText(), "Source");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(12).getText(), "Sales rep");
        softAssert.assertEquals(orderDetail.getListOfSubHeaders().get(13).getText(), "Resporg");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderRegularFlow(String displayedName, double subPriceOverride, double subPrice, String pricePlan, int pricePlanDiscount, double priceMonthlyMinutes,
                                                    int amountMinutes, int planDuration, String ringToNumber, double payToday, String promoCodeValue, String promoCodeName) {
        waiting2seconds();
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), "Completed", "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getTimeStamps()), "", "TimeStamps");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "", "PhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneType()), "tollfree", "PhoneType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFlowType()), "vanity", "FlowType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubType()), "regular", "SubType");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBearer()), "", "Bearer");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsNationwide()), "yes", "IsNationwide");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsRegional()), "no", "IsRegional");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsFeature()), "no", "IsFeature");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsAuction()), "no", "IsAuction");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsPremium()), "no", "IsPremium");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), "0.00", "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), "0.00", "CustomerPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), String.valueOf(subPrice), "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()), String.valueOf(subPriceOverride), "CustomerSubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDuration()), String.valueOf(planDuration), "PricePlanDuration");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDiscount()), String.valueOf(pricePlanDiscount), "PricePlanDiscount");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), "", "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanActivationFee()), "", "PricePlanActivationFee");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCodeName, "PromoCodeName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountValue()), promoCodeValue, "DiscountValue");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountType()), "", "DiscountType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwner()), "", "Owner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwnerCommission()), "", "OwnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutes()), String.valueOf(amountMinutes), "Minutes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutesPrice()), String.valueOf(priceMonthlyMinutes), "MinutesPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePerMinute()), "0.08", "PricePerMinute");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderPremiumFlow(String displayedName, double subPriceOverride, double subPrice, String pricePlan, int pricePlanDiscount, double priceMonthlyMinutes,
                                                    int amountMinutes, int planDuration, String ringToNumber, double payToday, String promoCodeValue, String promoCodeName) {
        waiting2seconds();
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), "Completed", "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getTimeStamps()), "", "TimeStamps");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "", "PhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneType()), "tollfree", "PhoneType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFlowType()), "vanity", "FlowType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubType()), "premium", "SubType");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBearer()), "", "Bearer");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsNationwide()), "no", "IsNationwide");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsRegional()), "yes", "IsRegional");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsFeature()), "no", "IsFeature");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsAuction()), "no", "IsAuction");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsPremium()), "yes", "IsPremium");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), "0.00", "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), "0.00", "CustomerPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), String.valueOf(subPrice), "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()), String.valueOf(subPriceOverride), "CustomerSubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDuration()), String.valueOf(planDuration), "PricePlanDuration");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDiscount()), String.valueOf(pricePlanDiscount), "PricePlanDiscount");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), "", "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanActivationFee()), "", "PricePlanActivationFee");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCodeName, "PromoCodeName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountValue()), promoCodeValue, "DiscountValue");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountType()), "$", "DiscountType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwner()), "Lebron James", "Owner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getOwnerCommission()), "1", "OwnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutes()), String.valueOf(amountMinutes), "Minutes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutesPrice()), String.valueOf(priceMonthlyMinutes), "MinutesPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePerMinute()), "0.05", "PricePerMinute");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderBasic800Flow(String displayedName, String pricePlan, double subsPrice, String amountMinutes, String additionalCost,
                                                     double priceActivationFee, String ringToNumber, String promoCodeValue, String promoCodeName, String statusPay) {
        waiting2seconds();
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), "Completed", "Status");
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
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), String.valueOf(priceActivationFee), "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), "0.00", "CustomerPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), String.valueOf(subsPrice), "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()), "0.00", "CustomerSubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlan, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDuration()), "1", "PricePlanDuration");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDiscount()), "0", "PricePlanDiscount");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), String.valueOf(additionalCost), "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanActivationFee()), String.valueOf(priceActivationFee), "PricePlanActivationFee");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCodeName, "PromoCodeName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountValue()), promoCodeValue, "DiscountValue");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountType()), "%", "DiscountType");
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
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "", "PublicID");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "", "ZendeskId");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), statusPay, "Status");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "", "CountryISO");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getLocalArea()), "", "Local Area");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getState()), "", "State");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getTimeStamps()), "", "TimeStamps");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "", "PhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName, "VanityPhoneName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCategory()), "", "Category");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneType()), "local", "PhoneType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFlowType()), "local", "FlowType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubType()), "", "SubType");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBearer()), "", "Bearer");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), oldPrice, "PaymentPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), customerPrice, "CustomerPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), subscriptionPrice, "SubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerSubscriptionPrice()), "", "CustomerSubscriptionPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPayToday()), payToday, "PayToday");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneUpsellName()).toLowerCase(), phoneUpsellName.toLowerCase(), "PhoneUpsellName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneUpsellPrice()), phoneUpsellPrice, "PhoneUpsellPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlan()), pricePlanName, "PricePlan");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanDuration()), pricePlanDuration, "PricePlanDuration");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePlanAdditionalCost()), additionalCost, "PricePlanAdditionalCost");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPromoCodeName()), promoCodeName, "PromoCodeName");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountValue()), discountPromoCode, "DiscountValue");
        softAssert.assertEquals(getAttributeValue(orderDetail.getDiscountType()), promoCodeType, "PromoCodeType");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartner()), "", "ApiPartner");
        softAssert.assertEquals(getAttributeValue(orderDetail.getApiPartnerCommission()), "", "ApiPartnerCommission");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutes()), "", "Minutes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getMinutesPrice()), "", "MinutesPrice");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPricePerMinute()), "", "PricePerMinute");
        softAssert.assertEquals(getAttributeValue(orderDetail.getRingToNumber()), ringToNumber, "RingToNumber");
        softAssert.assertAll();
    }
}
