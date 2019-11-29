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

    public void checkingCorrectDataOrderRegularFlow(String displayedName, double priceOverride, double priceMonthlyMinutes, int amountMinutes,
                                                    int planDuration, String ringToNumber, double subscriptionPrice, String promoCodeValue, String promoCodeName) {
        waiting2seconds();
        double actualResult = priceMonthlyMinutes + priceOverride - (priceMonthlyMinutes + priceOverride) * 1 * 0.01 ;
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPublicID()), "");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getZendeskId()), "");
        softAssert.assertEquals(getAttributeValue(orderDetail.getStatus()), "Completed");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getCountryISO()), "");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getTimeStamps()), "");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getPhoneName()), "");
        softAssert.assertEquals(getAttributeValue(orderDetail.getVanityPhoneName()), displayedName);
        softAssert.assertEquals(getAttributeValue(orderDetail.getPhoneType()), "tollfree");
        softAssert.assertEquals(getAttributeValue(orderDetail.getFlowType()), "vanity");
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubType()), "regular");
        softAssert.assertNotEquals(getAttributeValue(orderDetail.getBearer()), "");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsNationwide()), "yes");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsRegional()), "no");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsFeature()), "no");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsAuction()), "no");
        softAssert.assertEquals(getAttributeValue(orderDetail.getIsPremium()), "no");
        softAssert.assertEquals(getAttributeValue(orderDetail.getPaymentPrice()), 0.00);
        softAssert.assertEquals(getAttributeValue(orderDetail.getCustomerPrice()), 0.00);
        softAssert.assertEquals(getAttributeValue(orderDetail.getSubscriptionPrice()), 0.00);


        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), 19.95 + priceMonthlyMinutes, "old subscription price");
        softAssert.assertEquals(orderDetail.getSubscriptionPrice().getText(), priceOverride + priceMonthlyMinutes, "customer_subscription_priceoverride");
        softAssert.assertEquals(orderDetail.getPriceSubscriptionPayment().getText(), "PriceSubscriptionPayment");
        softAssert.assertEquals(orderDetail.getCategory().getText(), actualResult, "actual result subscription");
        softAssert.assertEquals(orderDetail.getPhoneType().getText(), amountMinutes, "amountMinutes");
        softAssert.assertEquals(orderDetail.getFlowType().getText(), "FlowType");
        softAssert.assertEquals(orderDetail.getSubType().getText(), "SubType");
        softAssert.assertEquals(orderDetail.getBearer().getText(), "Bearer");
        softAssert.assertEquals(orderDetail.getApiPartner().getText(), "0.00");
        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), promoCodeValue, "promoCodes");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderPremiumFlow(String displayedName, double priceOverride, double oldPriceChosenAreaCodes, double priceMonthlyMinutes, int amountMinutes,
                                                    int planDuration, String ringToNumber, double subscriptionPrice, String promoCodeValue, String promoCodeName) {
        waiting2seconds();
        softAssert.assertNotEquals(orderDetail.getPublicID().getText(), "-");
        softAssert.assertEquals(orderDetail.getStatus().getText(), "Active");
        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), 19.95 + oldPriceChosenAreaCodes, "old subscription price");
        softAssert.assertEquals(orderDetail.getSubscriptionPrice().getText(), priceOverride + 1, "customer_subscription_priceoverride");
        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), 1, "promoCodes");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderBasic800Flow(String displayedName, String pricePlanName, double pricePlanPrice, String amountMinutes, String additionalCost,
                                                     double priceActivationFee, String ringToNumber, String promoCodeValue, String promoCodeName, String statusPay) {
        waiting2seconds();
        softAssert.assertNotEquals(orderDetail.getPublicID().getText(), "-");
        softAssert.assertEquals(orderDetail.getStatus().getText(), "Active");
        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), 19.95 , "old subscription price");
        softAssert.assertEquals(orderDetail.getSubscriptionPrice().getText(), "customer_subscription_priceoverride");
        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), promoCodeValue, "promoCodes");
        softAssert.assertAll();
    }

    public void checkingCorrectDataOrderLocalFlow(String displayedName, double oldPrice, double customerPrice, double subscriptionPrice, double paymentPrice,
                                                  double priceSubscriptionPayment, String phoneUpsellName, double phoneUpsellPrice, String pricePlanName,
                                                  String pricePlanDuration, double additionalCost, double activationFee, int amountMinutes, double minutesPrice,
                                                  String ringToNumber, String discountPromoCode, String promoCodeName, String statusPay) {
        waiting2seconds();
        softAssert.assertNotEquals(orderDetail.getPublicID().getText(), null);
        softAssert.assertEquals(orderDetail.getStatus().getText(), statusPay);
        softAssert.assertNotEquals(orderDetail.getPhoneName().getText(), null);
        softAssert.assertEquals(orderDetail.getVanityPhoneName().getText(), displayedName);
        softAssert.assertEquals(orderDetail.getCategory().getText(), "category");
        softAssert.assertEquals(orderDetail.getPhoneType().getText(), "local");
        softAssert.assertEquals(orderDetail.getFlowType().getText(), "local");
        softAssert.assertEquals(orderDetail.getSubType().getText(), "");
        softAssert.assertNotEquals(orderDetail.getBearer().getText(), null);
        softAssert.assertEquals(orderDetail.getPaymentPrice().getText(), oldPrice);
        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), customerPrice);
        softAssert.assertEquals(orderDetail.getCustomerSubscriptionPrice().getText(), subscriptionPrice);
        softAssert.assertEquals(orderDetail.getPhoneUpsellName().getText(), phoneUpsellName);
        softAssert.assertEquals(orderDetail.getPhoneUpsellPrice().getText(), phoneUpsellPrice);
        softAssert.assertEquals(orderDetail.getPriceOnePlanPayment().getText(), paymentPrice);
        softAssert.assertEquals(orderDetail.getPriceSubscriptionPayment().getText(), priceSubscriptionPayment);
        softAssert.assertEquals(orderDetail.getPriceOneTimePayment().getText(), additionalCost);
        softAssert.assertEquals(orderDetail.getSubscriptionPrice().getText(), activationFee);
        softAssert.assertEquals(orderDetail.getPromoCodeName().getText(), promoCodeName);
        softAssert.assertEquals(orderDetail.getDiscountValue().getText(), discountPromoCode);
        softAssert.assertEquals(orderDetail.getMinutes().getText(), amountMinutes);
        softAssert.assertEquals(orderDetail.getMinutesPrice(), minutesPrice);
        softAssert.assertAll();
    }
}
