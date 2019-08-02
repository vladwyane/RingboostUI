package adminPanel;

import data.CreditCards;
import data.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import pages.admin.Admin;
import pages.admin.InventoryTollfree;
import pages.admin.LinksListingPage;
import pages.admin.Login;
import testBase.TestBase;

/**
 * Created by bigdrop on 7/31/2019.
 */
public class Sprint_1_PriceOverride extends TestBase {

    private Login login;
    private Admin admin;
    private InventoryTollfree inventoryTollfree;
    private LinksListingPage linksListingPage;

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        inventoryTollfree = new InventoryTollfree(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1CorrectRedirectFromRegularTollFree() throws InterruptedException {
        admin.clickToolFreInventoryLink();
        inventoryTollfree.searchNumber(0, "8335897464");
        inventoryTollfree.clickCreateNewLinkByNumber(0);
        linksListingPage.checkingCorrectRedirect();
    }
}
