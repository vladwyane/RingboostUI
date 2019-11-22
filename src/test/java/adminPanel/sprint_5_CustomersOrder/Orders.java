package adminPanel.sprint_5_CustomersOrder;

import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 11/21/2019.
 */
public class Orders extends TestBase {

    private Login login;
    private Admin admin;
    private OrderListingPage orderListingPage;
    private OrderDetailPage orderDetailPage;

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        orderListingPage = new OrderListingPage(app.getDriver());
        orderDetailPage = new OrderDetailPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void testCheckingCorrectColumnOrdersLocalListing() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersLocal();
        orderListingPage.checkingCorrectColumnOrdersLocalListing();
    }

    @Test
    public void testCheckingOrdersLocalListingIsNotEmpty() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersLocal();
        orderListingPage.checkingTableNotEmpty();
    }

    @Test
    public void testCheckingCorrectColumnOrdersTollFreeListing() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersTollFree();
        orderListingPage.checkingCorrectColumnOrdersTollFreeListing();
    }

    @Test
    public void testCheckingOrdersTollFreeListingIsNotEmpty() throws InterruptedException, IOException, JSONException {
        admin.clickOrdersTollFree();
        orderListingPage.checkingTableNotEmpty();
    }
}
