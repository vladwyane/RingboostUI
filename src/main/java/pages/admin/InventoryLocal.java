package pages.admin;

import blocks.admin.generatorLinks.ListGeneratedURL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.BasePage;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TextInput;
import utils.ConfigProperties;

import java.util.List;

/**
 * Created by bigdrop on 8/1/2019.
 */
public class InventoryLocal extends BasePage {

    public InventoryLocal(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("adminInventoryLocal.url"));
    }

    ListGeneratedURL listGeneratedURL;

    @Name("ArrayList of search input in th")
    @FindBys( {@FindBy(css = "th form input")} )
    public List<TextInput> listSearchFieldTh;

    @Name("ArrayList of search input in th WebElement")
    @FindBys( {@FindBy(css = "th form input")} )
    public List<WebElement> listSearchFieldThWebElement;

    @Name("ArrayList of search icon in th")
    @FindBys( {@FindBy(css = "th form .v-input__icon")} )
    public List<WebElement> listSearchIconTh;

    @Name("ArrayList actions of table")
    @FindBys( {@FindBy(css = "tbody tr td a")} )
    public List<WebElement> listActionsOfTable;

    @Name("ArrayList td of table")
    @FindBys( {@FindBy(css = "tbody tr td")} )
    public List<WebElement> listTdOfTable;

    @Name("List of column header")
    @FindBys( {@FindBy(xpath = "//th [not (contains(@class, 'column'))]")} )
    public List<WebElement> listColumnHeader;

    @FindBy(css = ".v-toolbar__title")
    private WebElement tableTitle;

    @Name("List of th static table")
    @FindBys( {@FindBy(css = ".action-first table th")} )
    public List<WebElement> listThStaticTable;

    @Name("ArrayList td of static table")
    @FindBys( {@FindBy(css = ".action-first table td")} )
    public List<WebElement> listTdOfStaticTable;

    @Name("List of th scroll table")
    @FindBys( {@FindBy(css = ".additional-table table th")} )
    public List<WebElement> listThScrollcTable;

    @Name("ArrayList td of stcroll table")
    @FindBys( {@FindBy(css = ".additional-table table td")} )
    public List<WebElement> listTdOfScrollTable;

    @FindBy(xpath = "//input[@aria-label='Select role']/ancestor::div[@class='v-select__selections']")
    private WebElement selectOfRole;

    @Name("List of active drop down")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@role='listitem']")} )
    private List<WebElement> listOfActiveDropDown;

    @FindBy(xpath = "(//p[contains(text(), 'Status')]/following::div)[1]")
    private WebElement selectOfStatus;

    @FindBy(xpath = "(//p[contains(text(), 'DID Source')]/following::div)[1]")
    private WebElement selectOfDidSource;

    @FindBy(xpath = "(//p[contains(text(), 'DID Origin')]/following::div)[1]")
    private WebElement selectOfDidOrigin;

    @FindBy(xpath = "(//p[contains(text(), 'Call For Price')]/following::div)[1]")
    private WebElement selectOfCallForPrice;

    @FindBy(xpath = "(//p[contains(text(), 'State')]/following::div)[1]")
    private WebElement selectOfState;

    @FindBy(xpath = "(//p[contains(text(), 'Sale Price')]/following::div)[1]")
    private WebElement selectOfSalePrice;

    @FindBy(xpath = "(//p[contains(text(), 'Disable coupon')]/following::div)[1]")
    private WebElement selectOfDisableCoupon;

    @FindBy(xpath = "(//p[contains(text(), 'Popular')]/following::div)[1]")
    private WebElement selectOfPopular;

    @FindBy(xpath = "(//p[contains(text(), 'Featured')]/following::div)[1]")
    private WebElement selectOfFeatured;

    @FindBy(xpath = "(//p[contains(text(), 'Port Status')]/following::div)[1]")
    private WebElement selectOfPortStatus;

    @FindBy(xpath = "(//p[contains(text(), 'Call Forward')]/following::div)[1]")
    private WebElement selectOfCallForward;

    @FindBy(xpath = "(//p[contains(text(), 'Order Date')]/following::div)[1]")
    private WebElement datePickerOrderDate;

    @Name("List of Dates in current month")
    @FindBys( {@FindBy(xpath = "//div[@class='mx-datepicker-body']//td[not (contains(@class, 'not-current-month'))]")} )
    private List<WebElement> listOfDateCurrentMonth;

    @FindBy(xpath = "(//p[contains(text(), 'Phone number')]/following::form//input)[1]")
    private TextInput inputPhoneNumbers;

    @FindBy(xpath = "(//p[contains(text(), 'Vanity')]/following::form//input)[1]")
    private TextInput inputVanity;

    @FindBy(xpath = "(//p[contains(text(), 'NPA')]/following::form//input)[1]")
    private TextInput inputNPA;

    @FindBy(xpath = "(//p[contains(text(), 'NXX')]/following::form//input)[1]")
    private TextInput inputNXX;

    @FindBy(xpath = "(//p[contains(text(), 'Last 4 Digit')]/following::form//input)[1]")
    private TextInput inputLast4Digit;

    @FindBy(xpath = "(//p[contains(text(), 'Carrier')]/following::form//input)[1]")
    private TextInput inputCarrier;

    @FindBy(xpath = "(//p[contains(text(), 'Categories')]/following::form//input)[1]")
    private TextInput inputCategories;

    @FindBy(xpath = "(//p[contains(text(), 'Rate Center')]/following::form//input)[1]")
    private TextInput inputRateCenter;

    @FindBy(xpath = "(//p[contains(text(), 'City')]/following::form//input)[1]")
    private TextInput inputCity;

    @FindBy(xpath = "(//p[contains(text(), 'Price override')]/following::form//input)[1]")
    private TextInput inputPriceOverride;

    @FindBy(xpath = "(//p[contains(text(), 'Source')]/following::form//input)[5]")
    private TextInput inputSource;

    @FindBy(xpath = "(//p[contains(text(), 'Rev Share')]/following::form//input)[1]")
    private TextInput inputRevShare;

    @FindBy(xpath = "(//p[contains(text(), 'Weight')]/following::form//input)[1]")
    private TextInput inputWeight;

    @FindBy(xpath = "(//p[contains(text(), 'Plan')]/following::form//input)[1]")
    private TextInput inputPlan;

    @FindBy(xpath = "(//p[contains(text(), 'Ticket#')]/following::form//input)[1]")
    private TextInput inputTicket;


    public void filterByVanity(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputVanity, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByPhoneNumbers(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputPhoneNumbers, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByNPA(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputNPA, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByNXX(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputNXX, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByLast4Digit(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputLast4Digit, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByCarrier(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfRole, listOfActiveDropDown, "Support");
        type(inputCarrier, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByStatus(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfRole, listOfActiveDropDown, "Support");
        chooseElementFromSelectInAdminPanel(selectOfStatus, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByDidSource(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfDidSource, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByDidSOrigin(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfDidOrigin, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByOrderDate() {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfRole, listOfActiveDropDown, "Support");
        choosePeriodFromDatePickerInAdminPanel(datePickerOrderDate, listOfDateCurrentMonth, "1", "30");
        waiting5seconds();
    }

    public void filterByCategory(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputCategories, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByRateCenter(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputRateCenter, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByCity(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputCity, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByCallForPrice(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfCallForPrice, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByState(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfState, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterBySalePrice(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfSalePrice, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByPriceOverride(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputPriceOverride, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByDisableCoupon(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfDisableCoupon, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterBySource(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputSource, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByRevShare(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputRevShare, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByPopular(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfPopular, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByFeatured(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfFeatured, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByWeight(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputWeight, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByPlan(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfRole, listOfActiveDropDown, "Support");
        type(inputPlan, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByPortStatus(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfRole, listOfActiveDropDown, "Support");
        chooseElementFromSelectInAdminPanel(selectOfPortStatus, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByCallForward(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfRole, listOfActiveDropDown, "Support");
        chooseElementFromSelectInAdminPanel(selectOfCallForward, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByTicket(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfRole, listOfActiveDropDown, "Support");
        type(inputTicket, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void searchNumber(int index, String text) {
        waitUntilElementAppeared(tableTitle);
        type(listSearchFieldTh.get(index),text);
        listSearchIconTh.get(index).click();
        waiting2seconds();
    }

    public String clickCreateNewLinkByNumber(int indexNumber) {
        waitUntilElementAppeared(listActionsOfTable.get(0));
        String phoneNumber = listTdOfTable.get((indexNumber * listColumnHeader.size()) + 1).getText();
        listActionsOfTable.get(indexNumber * 2).click();
        waitUntilElementAppeared(listGeneratedURL.getButtonCreateNewURL());
        return phoneNumber;
    }

    public void searchNumberByAriaLabel(String areaLabel, String text) {
        waitUntilElementAppeared(listSearchIconTh.get(0));
        int index = 0;
        for (int i = 0; i < listSearchFieldThWebElement.size(); i++) {
            if(isElementContainsAttributeValue(listSearchFieldThWebElement.get(i), "aria-label", areaLabel)) {
                index = i;
                break;
            }
        }
        type(listSearchFieldTh.get(index),text);
        listSearchIconTh.get(index).click();
        waiting2seconds();
    }

    public void checkingCorrectFiltrationStaticTable(String searchRequest, String filterParameter) {
        waitUntilElementAppeared(tableTitle);
        int index = 1;
        for (int i = 0; i < listThStaticTable.size(); i++) {
            if(listThStaticTable.get(i).getText().toLowerCase().contains(filterParameter.toLowerCase())){
                index = i;
                break;
            }
        }
        boolean correctFilter = false;
        for (int i = index; i < listTdOfStaticTable.size(); i+=listThStaticTable.size() - 1) {
            if(listTdOfStaticTable.get(i).getText().toLowerCase().contains(searchRequest.toLowerCase())){
                correctFilter = true;
            } else {
                correctFilter = false;
                break;
            }
        }
        softAssert.assertTrue(correctFilter, "Filtration is incorrect");
        softAssert.assertTrue(listTdOfStaticTable.size() > 0, "Not found");
        softAssert.assertAll();
    }

    public void checkingCorrectFiltrationScrollTable(String searchRequest, String filterParameter) {
        waitUntilElementAppeared(tableTitle);
        int index = 1;
        for (int i = 0; i < listThScrollcTable.size(); i++) {
            if(listThScrollcTable.get(i).getText().toLowerCase().contains(filterParameter.toLowerCase())){
                index = i;
                break;
            }
        }
        boolean correctFilter = false;
        for (int i = index; i < listTdOfScrollTable.size(); i+=listThScrollcTable.size() - 1) {
            if(listTdOfScrollTable.get(i).getText().toLowerCase().contains(searchRequest.toLowerCase())){
                correctFilter = true;
            } else {
                correctFilter = false;
                break;
            }
        }
        softAssert.assertTrue(correctFilter, "Filtration is incorrect");
        softAssert.assertTrue(listTdOfScrollTable.size() > 1, "Not found");
        softAssert.assertAll();
    }

    public void checkingCorrectFiltrationScrollTableByIndex(String searchRequest, int index) {
        waitUntilElementAppeared(tableTitle);
        boolean correctFilter = false;
        for (int i = index; i < listTdOfScrollTable.size(); i+=listThScrollcTable.size() - 1) {
            if(listTdOfScrollTable.get(i).getText().toLowerCase().contains(searchRequest.toLowerCase())){
                correctFilter = true;
            } else {
                correctFilter = false;
                break;
            }
        }
        softAssert.assertTrue(correctFilter, "Filtration is incorrect");
        softAssert.assertTrue(listTdOfScrollTable.size() > 0, "Not found");
        softAssert.assertAll();
    }

    public void checkingCorrectFiltrationScrollTableDatePicker() {
        waitUntilElementAppeared(tableTitle);
        softAssert.assertTrue(listTdOfScrollTable.size() > 1, "Not found");
        softAssert.assertAll();
    }


}
