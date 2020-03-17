package adminPanel.sprint_3_PricingTollFree;

import data.PricingTollFreeSettings;
import org.json.JSONException;
import org.testng.annotations.*;
import pages.admin.Admin;
import pages.admin.Login;
import pages.admin.PricingTollFreePage;
import pages.front.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/7/2019.
 */
public class MultipleAreas extends TestBase {

    private Login login;
    private Admin admin;
    private PricingTollFreePage pricingTollFreePage;
    private HomePage homePage;
    private VanitySearchResult vanitySearchResult;
    private BuyingPremiumVanityNumber buyingPremiumVanityNumber;
    private Checkout checkout;
    private OrderConfirmationPage orderConfirmationPage;
    private ContactUsPage contactUsPage;
    String tabName = "Multiple Areas";

    @BeforeMethod
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        pricingTollFreePage = new PricingTollFreePage(app.getDriver());
        homePage = new HomePage(app.getDriver());
        vanitySearchResult = new VanitySearchResult(app.getDriver());
        buyingPremiumVanityNumber = new BuyingPremiumVanityNumber(app.getDriver());
        checkout = new Checkout(app.getDriver());
        orderConfirmationPage = new OrderConfirmationPage(app.getDriver());
        contactUsPage = new ContactUsPage(app.getDriver());
        login.open();
        login.fillLoginForm();

    }

    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreatingNewMultipleAreas() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickAddPriceMultipleAreasButton();
        pricingTollFreePage.createNewPriceMultipleAreas(PricingTollFreeSettings.MULTIPLE_AREA_TEST);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.MULTIPLE_AREA_TEST);
    }

    @Test
    public void test1ErrorCreatingNewMultipleAreasAllFieldsEmpty() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickAddPriceMultipleAreasButton();
        pricingTollFreePage.clickSaveButton();
        pricingTollFreePage.checkingErrorMessagesCreatingMultipleAreasEmptyFields();
    }

    @Test
    public void test2ErrorCreatingNewMultipleAreasSameName() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickAddPriceMultipleAreasButton();
        pricingTollFreePage.createNewPriceMultipleAreas(PricingTollFreeSettings.MULTIPLE_AREA_TEST);
        pricingTollFreePage.checkingErrorMessagesCreatingMultipleAreasNameHasBeenUsed();
    }

    @Test
    public void test3CheckingChosen3AreaCodesOnSite() throws InterruptedException, IOException, JSONException {
       // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickEditIcon(PricingTollFreeSettings.MULTIPLE_3_AREA);
        pricingTollFreePage.editMultipleAreas(PricingTollFreeSettings.MULTIPLE_3_AREA);
        homePage.open();
        homePage.searchTollFreeNumbers("333");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Kansas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(3);
        buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        contactUsPage.checkingCorrectlyHeadingH1("We Want to Hear from You");
    }

    @Test
    public void test4CheckingChosen4AreaCodesOnSite() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers("333");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Kansas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(4);
        buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        contactUsPage.checkingCorrectlyHeadingH1("We Want to Hear from You");
    }

    @Test
    public void test4CheckingChosen2AreaCodesOnSite() throws InterruptedException, IOException, JSONException {
        homePage.open();
        homePage.searchTollFreeNumbers("333");
        vanitySearchResult.chooseFirstNumberFromPremiumVanityList();
        buyingPremiumVanityNumber.clickButtonChooseMyAreas();
        buyingPremiumVanityNumber.chooseState("Kansas");
        double priceFromAmountAreaCodes = buyingPremiumVanityNumber.chooseSeveralAreaCodesFromList(2);
        buyingPremiumVanityNumber.getPriceFromAmountAreaCodesWithDiscount(priceFromAmountAreaCodes);
        buyingPremiumVanityNumber.checkingFirstTermLength();
    }

    @Test
    public void test5ReturnToDefaultState() throws InterruptedException, IOException, JSONException {
      //  login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickEditIcon(PricingTollFreeSettings.MULTIPLE_3_AREA);
        pricingTollFreePage.editMultipleAreas(PricingTollFreeSettings.MULTIPLE_3_AREA);
    }

    @Test
    public void test6EditMultipleAreas() throws InterruptedException, IOException, JSONException {
        // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickEditIcon(PricingTollFreeSettings.MULTIPLE_AREA_TEST);
        pricingTollFreePage.editMultipleAreas(PricingTollFreeSettings.MULTIPLE_AREA_UPDATE);
        pricingTollFreePage.checkingSuccessCreatingNewRule(PricingTollFreeSettings.MULTIPLE_AREA_UPDATE);
    }

    @Test
    public void test7DeleteMultipleAreas() throws InterruptedException, IOException, JSONException {
        // login.open();
        admin.clickPricingTollFreeLink();
        pricingTollFreePage.clickTab(tabName);
        pricingTollFreePage.clickDeleteIcon(PricingTollFreeSettings.MULTIPLE_AREA_UPDATE);
        pricingTollFreePage.checkingSuccessDeleted(PricingTollFreeSettings.MULTIPLE_AREA_UPDATE, tabName);
    }

}
