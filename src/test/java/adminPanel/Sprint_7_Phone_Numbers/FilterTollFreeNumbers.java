package adminPanel.Sprint_7_Phone_Numbers;

import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.*;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 8/2/2019.
 */
public class FilterTollFreeNumbers extends TestBase{

    private Login login;
    private InventoryTollfree inventoryTollfree;


    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        inventoryTollfree = new InventoryTollfree(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1CheckingCorrectFilterByPhoneNumberField() throws InterruptedException, IOException, JSONException {
       // login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByPhoneNumber("20532");
        inventoryTollfree.checkingCorrectFiltrationStaticTable("20532","Phone number");
    }

    @Test
    public void test1CheckingCorrectFilterByPhoneNumberFieldWord() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByPhoneNumber("Admin");
        inventoryTollfree.checkingCorrectFiltrationStaticTable("Admin","Phone number");
    }

    @Test
    public void test1CheckingCorrectFilterByType() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByType("Vanity - Spare Pool");
        inventoryTollfree.checkingCorrectFiltrationStaticTable("Vanity - Spare Pool","Type");
    }

    @Test
    public void test1CheckingCorrectFilterByStatus() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByStatus("Licensed");
        inventoryTollfree.checkingCorrectFiltrationStaticTable("Licensed","Status");
    }

    @Test
    public void test1CheckingCorrectFilterByCategories() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByCategories("Financial Services");
        inventoryTollfree.checkingCorrectFiltrationStaticTable("Financial Services","Categories");
    }

    @Test
    public void test1CheckingCorrectFilterByRespOrg() throws InterruptedException, IOException, JSONException {
      //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByResporg("resporg");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("resporg","RespOrg");
    }

    @Test
    public void test1CheckingCorrectFilterByCarrier() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByCarrier("Carrier");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("Carrier","Carrier");
    }

    @Test
    public void test1CheckingCorrectFilterByNationwide() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByNationwide("Yes");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("Yes","Nationwide");
    }

    @Test
    public void test1CheckingCorrectFilterByNationwidePrice() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByNationwidePrice("1233");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("1233","Nationwide Price");
    }

    @Test
    public void test1CheckingCorrectFilterByRegional() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByRegional("Yes");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("Yes","Regional");
    }

    @Test
    public void test1CheckingCorrectFilterByPriceTier() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByPriceTier("55");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("Tier $55","Price Tier");
    }

    @Test
    public void test1CheckingCorrectFilterByInvisibleWebsite() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByInvisibleWebsite("Yes");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("Yes","Invisible Website");
    }

    @Test
    public void test1CheckingCorrectFilterByCallForPrice() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByCallForPrice("Yes");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("Yes","Call For Price");
    }

    @Test
    public void test1CheckingCorrectFilterByTNSource() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByTNSource("RINGBOOST");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("RINGBOOST","TN Source");
    }

    @Test
    public void test1CheckingCorrectFilterByTNOrigin() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByTNOrigin("TELNYX");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("TELNYX","TN Origin");
    }

    @Test
    public void test1CheckingCorrectFilterByOwner() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByOwner("Morgan Gross");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("Morgan Gross","Owner");
    }

    @Test
    public void test1CheckingCorrectFilterBySearchWeight() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterBySearchWeight("18");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("18","Search Weight");
    }

    @Test
    public void test1CheckingCorrectFilterByZipCodeRouted() throws InterruptedException, IOException, JSONException {
        //  login.open();
        inventoryTollfree.open();
        inventoryTollfree.filterByZipCode("Yes");
        inventoryTollfree.checkingCorrectFiltrationScrollTable("Yes","Zip code routed");
    }

}
