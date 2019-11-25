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

    public void checkingCorrectDataOrderRegularFlow(String displayedName, double priceOverride, double oldSubscriptionPrice, double priceMonthlyMinutes,
                                                    int amountMinutes, int discountPriceSelectedPlan, int monthDuration, String ringToNumber, String promoCodeValue) {
        waiting2seconds();
        double actualResult = priceMonthlyMinutes + priceOverride - (priceMonthlyMinutes + priceOverride) * discountPriceSelectedPlan * 0.01 ;
        softAssert.assertNotEquals(orderDetail.getPublicID().getText(), "-");
        softAssert.assertEquals(orderDetail.getStatus().getText(), "Active");
        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), 19.95 + priceMonthlyMinutes, "old subscription price");
        softAssert.assertEquals(orderDetail.getSubscriptionPrice().getText(), priceOverride + priceMonthlyMinutes, "customer_subscription_priceoverride");
        softAssert.assertEquals(orderDetail.getPricePlan().getText(), discountPriceSelectedPlan,"PricePlan");
        softAssert.assertEquals(orderDetail.getPricePlanDuration().getText(), monthDuration,"PricePlanDuration");
        softAssert.assertEquals(orderDetail.getPricePlanAdditionalCost().getText(), "PlanAdditionalCost");
        softAssert.assertEquals(orderDetail.getPricePlanActivationFee().getText(), "PricePlanActivationFee");
        softAssert.assertEquals(orderDetail.getPriceSubscriptionPayment().getText(), "PriceSubscriptionPayment");
        softAssert.assertEquals(orderDetail.getPhone().getText(), "erer");
        softAssert.assertEquals(orderDetail.getVanity().getText(), displayedName);
        softAssert.assertEquals(orderDetail.getCategory().getText(), actualResult, "actual result subscription");
        softAssert.assertEquals(orderDetail.getPhoneType().getText(), amountMinutes, "amountMinutes");
        softAssert.assertEquals(orderDetail.getFlowType().getText(), "FlowType");
        softAssert.assertEquals(orderDetail.getSubType().getText(), "SubType");
        softAssert.assertEquals(orderDetail.getRingToPhoneNumber().getText(), ringToNumber, "ringToNumber");
        softAssert.assertEquals(orderDetail.getBearer().getText(), "Bearer");
        softAssert.assertEquals(orderDetail.getApiPartner().getText(), "0.00");
        softAssert.assertEquals(orderDetail.getApiPartnerCommission(), "0.00");
        softAssert.assertEquals(orderDetail.getOwner().getText(), "0.00");
        softAssert.assertEquals(orderDetail.getCustomerPrice().getText(), promoCodeValue, "promoCodes");
        softAssert.assertAll();
    }
}
