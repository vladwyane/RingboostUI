package adminPanel.Sprint_7_Phone_Numbers;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.admin.Admin;
import pages.admin.InventoryLocal;
import pages.admin.LinksListingPage;
import pages.admin.Login;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 8/2/2019.
 */
public class FilterLocalNumbers extends TestBase{

    private Login login;
    private Admin admin;
    private LinksListingPage linksListingPage;
    private InventoryLocal inventoryLocal;
    private BuyingLocalNumber buyingLocalNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private LocalIndexPage localIndexPage;
    private LocalSearchResult localSearchResult;

    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        linksListingPage = new LinksListingPage(app.getDriver());
        inventoryLocal = new InventoryLocal(app.getDriver());
        buyingLocalNumber = new BuyingLocalNumber(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        localIndexPage = new LocalIndexPage(app.getDriver());
        localSearchResult = new LocalSearchResult(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1CheckingCorrectFilterByVanityField() throws InterruptedException, IOException, JSONException {
        login.open();
        inventoryLocal.open();
        inventoryLocal.filterByVanity("wood");
        inventoryLocal.checkingCorrectFiltrationStaticTable("wood","Vanity");
    }

    @Test
    public void test1CheckingCorrectFilterByPhoneNumberField() throws InterruptedException, IOException, JSONException {
        login.open();
        inventoryLocal.open();
        inventoryLocal.filterByPhoneNumbers("9663");
        inventoryLocal.checkingCorrectFiltrationStaticTable("9663","Phone numbers");
    }

    @Test
    public void test1CheckingCorrectFilterByPhoneNumberFieldWord() throws InterruptedException, IOException, JSONException {
        login.open();
        inventoryLocal.open();
        inventoryLocal.filterByPhoneNumbers("Buddy");
        inventoryLocal.checkingCorrectFiltrationStaticTable("Buddy","Phone numbers");
    }

    @Test
    public void test1CheckingCorrectFilterByNPAField() throws InterruptedException, IOException, JSONException {
        login.open();
        inventoryLocal.open();
        inventoryLocal.filterByNPA("818");
        inventoryLocal.checkingCorrectFiltrationStaticTable("818","NPA");
    }

    @Test
    public void test1CheckingCorrectFilterByNXXField() throws InterruptedException, IOException, JSONException {
        login.open();
        inventoryLocal.open();
        inventoryLocal.filterByNXX("605");
        inventoryLocal.checkingCorrectFiltrationStaticTable("605","NXX");
    }

    @Test
    public void test1CheckingCorrectFilterByLast4DigitField() throws InterruptedException, IOException, JSONException {
        login.open();
        inventoryLocal.open();
        inventoryLocal.filterByLast4Digit("6555");
        inventoryLocal.checkingCorrectFiltrationStaticTable("6555","Last 4 Digit");
    }

    @Test
    public void test1CheckingCorrectFilterByCarrier() throws InterruptedException, IOException, JSONException {
        login.open();
        inventoryLocal.open();
        inventoryLocal.filterByCarrier("alphabet");
        inventoryLocal.checkingCorrectFiltrationStaticTable("alphabet","Carrier");
    }


}
