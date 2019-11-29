package pages.admin;

import blocks.admin.orders.OrdersTable;
import blocks.admin.owners.OwnersListTable;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by bigdrop on 11/21/2019.
 */
public class OrderListingPage extends BasePage {

    public OrderListingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    OrdersTable ordersTable;

    public void checkingCorrectColumnOrdersLocalListing() {
        waitUntilElementAppeared(ordersTable.getListColumnHeader().get(0));
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(0).getText(), "Actions");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(1).getText(), "Phone Number");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(2).getText(), "Carrier");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(3).getText(), "Amount Per Month");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(4).getText(), "Total Amount");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(5).getText(), "Public IDarrow_upward");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(6).getText(), "Status");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(7).getText(), "Date");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(8).getText(), "Customer company");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(9).getText(), "Customer name");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(10).getText(), "Customer email");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(11).getText(), "Source");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(12).getText(), "Sales rep");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(13).getText(), "Resporg");
        softAssert.assertAll();
    }

    public void checkingTableNotEmpty() {
        waitUntilElementAppeared(ordersTable.getListColumnHeader().get(0));
        boolean isEmpty = false;
        for (int i = 0; i < ordersTable.getListTd().size(); i++) {
            if(ordersTable.getListTd().get(i).getText().equals("No matching records found")) {
                isEmpty = true;
                break;
            }

        }
        softAssert.assertFalse(isEmpty, "Table is empty");
        softAssert.assertAll();
    }

    public void checkingCorrectColumnOrdersTollFreeListing() {
        waitUntilElementAppeared(ordersTable.getListColumnHeader().get(0));
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(0).getText(), "Actions");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(1).getText(), "Phone Number");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(2).getText(), "Carrier");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(3).getText(), "Amount Per Month");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(4).getText(), "Public IDarrow_upward");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(5).getText(), "Status");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(6).getText(), "Date");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(7).getText(), "Customer company");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(8).getText(), "Customer name");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(9).getText(), "Customer email");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(10).getText(), "Source");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(11).getText(), "Sales rep");
        softAssert.assertEquals(ordersTable.getListColumnHeader().get(12).getText(), "Resporg");
        softAssert.assertAll();
    }

    public void clickEditIconFirstOrder() {
        waitUntilElementAppeared(ordersTable.getListOfActions().get(0));
        waitUntilElementWillBeClickable(ordersTable.getListOfActions().get(0));
        ordersTable.getListOfActions().get(0).click();
    }
}