package adminPanel.sprint_1_PriceOverride;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.*;
import testBase.TestBase;

/**
 * Created by bigdrop on 8/2/2019.
 */
public class priceOverrideForLocal extends TestBase{

    private Login login;
    private Admin admin;
    private LinksListingPage linksListingPage;
    private InventoryLocal inventoryLocal;

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        inventoryLocal = new InventoryLocal(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1CorrectRedirectFromRegularTollFree() throws InterruptedException {
        admin.clickLocalInventoryLink();
        inventoryLocal.searchNumber(0, "8335897464");
        String phoneNumber = inventoryLocal.clickCreateNewLinkByNumber(0);
        linksListingPage.generateLinkWithPromocode("23");
        String generatedLink = linksListingPage.getGeneratedLink(0);

    }

    public static void main(String[] args) {
        String number = "1-800-234-5642";
        String result = number.substring(2).replaceAll("-", "");
        System.out.println(result);

    }
}
