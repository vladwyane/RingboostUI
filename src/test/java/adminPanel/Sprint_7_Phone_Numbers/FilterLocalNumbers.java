package adminPanel.Sprint_7_Phone_Numbers;

import data.CreditCards;
import data.PromoCodes;
import data.Users;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
    private InventoryLocal inventoryLocal;


    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        inventoryLocal = new InventoryLocal(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1CheckingCorrectFilterByVanityField() throws InterruptedException, IOException, JSONException {
        //login.open();
        inventoryLocal.open();
        inventoryLocal.filterByVanity("wood");
        inventoryLocal.checkingCorrectFiltrationStaticTable("wood","Vanity");
    }

    @Test
    public void test1CheckingCorrectFilterByPhoneNumberField() throws InterruptedException, IOException, JSONException {
       // login.open();
        inventoryLocal.open();
        inventoryLocal.filterByPhoneNumbers("9663");
        inventoryLocal.checkingCorrectFiltrationStaticTable("9663","Phone numbers");
    }

    @Test
    public void test1CheckingCorrectFilterByPhoneNumberFieldWord() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByPhoneNumbers("Buddy");
        inventoryLocal.checkingCorrectFiltrationStaticTable("Buddy","Phone numbers");
    }

    @Test
    public void test1CheckingCorrectFilterByNPAField() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByNPA("818");
        inventoryLocal.checkingCorrectFiltrationStaticTable("818","NPA");
    }

    @Test
    public void test1CheckingCorrectFilterByNXXField() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByNXX("605");
        inventoryLocal.checkingCorrectFiltrationStaticTable("605","NXX");
    }

    @Test
    public void test1CheckingCorrectFilterByLast4DigitField() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByLast4Digit("6555");
        inventoryLocal.checkingCorrectFiltrationStaticTable("6555","Last 4 Digit");
    }

    @Test
    public void test1CheckingCorrectFilterByCarrier() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByCarrier("alphabet");
        inventoryLocal.checkingCorrectFiltrationStaticTable("alphabet","Carrier");
    }

    @Test
    public void test1CheckingCorrectFilterByStatus() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByStatus("Sold");
        inventoryLocal.checkingCorrectFiltrationStaticTable("Sold","Status");
    }

    @Test
    public void test1CheckingCorrectFilterByDidSource() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByDidSource("VIVO");
        inventoryLocal.checkingCorrectFiltrationScrollTable("VIVO","DID Source");
    }

    @Test
    public void test1CheckingCorrectFilterByDidOrigin() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByDidSOrigin("RINGBOOST");
        inventoryLocal.checkingCorrectFiltrationScrollTable("RINGBOOST","DID Origin");
    }

    @Test
    public void test1CheckingCorrectFilterByCategories() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByCategory("Top 4 Digit");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Top 4 Digit","Categories");
    }

    @Test
    public void test1CheckingCorrectFilterByRateCenter() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByRateCenter("NAMPA");
        inventoryLocal.checkingCorrectFiltrationScrollTable("NAMPA","Rate Center");
    }

    @Test
    public void test1CheckingCorrectFilterByCity() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByCity("Miami");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Miami","City");
    }

    @Test
    public void test1CheckingCorrectFilterByCallForPrice() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByCallForPrice("Yes");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Yes","Call For Price");
    }

    @Test
    public void test1CheckingCorrectFilterBySalePrice() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterBySalePrice("Pattern - $549");
        inventoryLocal.checkingCorrectFiltrationScrollTable("$549","Sale Price");
    }

    @Test
    public void test1CheckingCorrectFilterByState() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByState("Alabama");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Al","State");
    }

    @Test
    public void test1CheckingCorrectFilterByPriceOverride() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByPriceOverride("23.5");
        inventoryLocal.checkingCorrectFiltrationScrollTable("$23.50","Price override");
    }

    @Test
    public void test1CheckingCorrectFilterByDisableCoupon() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByDisableCoupon("Yes");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Yes","Disable coupon");
    }

    @Test
    public void test1CheckingCorrectFilterBySource() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterBySource("phone");
        inventoryLocal.checkingCorrectFiltrationScrollTableByIndex("phone",11);
    }

    @Test
    public void test1CheckingCorrectFilterByRevShare() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByRevShare("99");
        inventoryLocal.checkingCorrectFiltrationScrollTable("99","Rev Share");
    }

    @Test
    public void test1CheckingCorrectFilterByPopular() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByPopular("Yes");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Yes","Popular");
    }

    @Test
    public void test1CheckingCorrectFilterByFeatured() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByFeatured("Yes");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Yes","Featured");
    }

    @Test
    public void test1CheckingCorrectFilterByWeight() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByWeight("10");
        inventoryLocal.checkingCorrectFiltrationScrollTable("10","Weight");
    }

    @Test
    public void test1CheckingCorrectFilterByPlan() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByPlan("Starter");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Starter","Plan");
    }

    @Test
    public void test1CheckingCorrectFilterByPortStatus() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByPortStatus("Complete");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Complete","Port Status");
    }

    @Test
    public void test1CheckingCorrectFilterByCallForward() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByCallForward("Yes");
        inventoryLocal.checkingCorrectFiltrationScrollTable("Yes","Call Forward");
    }

    @Test
    public void test1CheckingCorrectFilterByTicket() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByTicket("1234");
        inventoryLocal.checkingCorrectFiltrationScrollTable("1234","Ticket#");
    }

    @Test
    public void test1CheckingCorrectFilterByOrderDate() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryLocal.open();
        inventoryLocal.filterByOrderDate();
        inventoryLocal.checkingCorrectFiltrationScrollTableDatePicker();
    }



}
