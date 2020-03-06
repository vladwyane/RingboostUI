package pages.admin;

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
public class InventoryTollfree extends BasePage{

    public InventoryTollfree(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("adminInventoryTollFree.url"));
    }

    @Name("ArrayList of search input in th")
    @FindBys( {@FindBy(css = "th form input")} )
    public List<TextInput> listSearchFieldTh;

    @Name("ArrayList of search icon in th")
    @FindBys( {@FindBy(css = "th form .v-input__icon")} )
    public List<WebElement> listSearchIconTh;

    @Name("ArrayList actions of table")
    @FindBys( {@FindBy(css = "tbody tr td a")} )
    public List<WebElement> listActionsOfTable;

    @Name("ArrayList td of table")
    @FindBys( {@FindBy(xpath = "//div[@class = 'additional-table']/preceding-sibling::div//td")} )
    public List<WebElement> listTdOfTable;

    @Name("List of column header")
    @FindBys( {@FindBy(xpath = "//div[@class = 'additional-table']/preceding-sibling::div//th [not (contains(@class, 'column'))]")} )
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

    @Name("List of active drop down")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@role='listitem']")} )
    private List<WebElement> listOfActiveDropDown;

    @FindBy(xpath = "(//p[contains(text(), 'Status')]/following::div)[1]")
    private WebElement selectOfStatus;

    @FindBy(xpath = "(//p[contains(text(), 'Type')]/following::div)[1]")
    private WebElement selectOfType;

    @FindBy(xpath = "(//p[contains(text(), 'Nationwide')]/following::div)[1]")
    private WebElement selectOfNationwide;

    @FindBy(xpath = "(//p[contains(text(), 'Regional')]/following::div)[1]")
    private WebElement selectOfRegional;

    @FindBy(xpath = "(//p[contains(text(), 'Invisible Website')]/following::div)[1]")
    private WebElement selectOfInvisibleWebsite;

    @FindBy(xpath = "(//p[contains(text(), 'Call For Price')]/following::div)[1]")
    private WebElement selectOfCallForPrice;

    @FindBy(xpath = "(//p[contains(text(), 'TN Source')]/following::div)[1]")
    private WebElement selectOfTNSource;

    @FindBy(xpath = "(//p[contains(text(), 'TN Origin')]/following::div)[1]")
    private WebElement selectOfTNOrigin;

    @FindBy(xpath = "(//p[contains(text(), 'Zip code routed')]/following::div)[1]")
    private WebElement selectOfZipCodeRouted;

    @FindBy(xpath = "(//p[contains(text(), 'Phone number')]/following::form//input)[1]")
    private TextInput inputPhoneNumbers;

    @FindBy(xpath = "(//p[contains(text(), 'Categories')]/following::form//input)[1]")
    private TextInput inputCategories;

    @FindBy(xpath = "(//p[contains(text(), 'RespOrg')]/following::form//input)[1]")
    private TextInput inputResporg;

    @FindBy(xpath = "(//p[contains(text(), 'Carrier')]/following::form//input)[1]")
    private TextInput inputCarrier;

    @FindBy(xpath = "(//p[contains(text(), 'Price Tier')]/following::form//input)[1]")
    private TextInput inputPriceTier;

    @FindBy(xpath = "(//p[contains(text(), 'Nationwide Price')]/following::form//input)[1]")
    private TextInput inputNationwidePrice;

    @FindBy(xpath = "(//p[contains(text(), 'Price Tier')]/following::form//input)[1]")
    private TextInput inputNationwidePriceTier;

    @FindBy(xpath = "(//p[contains(text(), 'Owner')]/following::form//input)[1]")
    private TextInput inputOwner;

    @FindBy(xpath = "(//p[contains(text(), 'Search Weight')]/following::form//input)[1]")
    private TextInput inputSearchWeight;

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
        return phoneNumber;
    }

    public String returnCellOfTable(String headingColumn, int indexOfListLinks) {
        int indexColumn = 0;
        for (int i = 0; i < listColumnHeader.size(); i++) {
            if(listColumnHeader.get(i).getText().contains(headingColumn)){
                indexColumn = i;
                break;
            }
        }
        int counter = 0;
        for (int i = indexColumn; i < listTdOfTable.size(); i += listColumnHeader.size()) {
            if(counter == indexOfListLinks) {
                return listTdOfTable.get(i).getText();
            }
            counter ++;
        }
        return listTdOfTable.get(indexColumn).getText();
    }

    public void checkingStatusLicensed(String headingColumn, int indexOfListLinks) {
        waitUntilElementAppeared(listSearchIconTh.get(0));
        softAssert.assertEquals(returnCellOfTable(headingColumn, indexOfListLinks), "Licensed");
        softAssert.assertAll();
    }

    public void filterByType(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfType, listOfActiveDropDown, searchRequest);
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByPhoneNumber(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputPhoneNumbers, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByCategories(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputCategories, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByResporg(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputResporg, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByCarrier(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputCarrier, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByNationwidePrice(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputNationwidePrice, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByPriceTier(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputPriceTier, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByOwner(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputOwner, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterBySearchWeight(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputSearchWeight, searchRequest);
        listSearchIconTh.get(0).click();
        waiting5seconds();
    }

    public void filterByStatus(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfStatus, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByNationwide(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfNationwide, listOfActiveDropDown, searchRequest);
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByRegional(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfRegional, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByInvisibleWebsite(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfInvisibleWebsite, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByCallForPrice(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfCallForPrice, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByTNSource(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfTNSource, listOfActiveDropDown, searchRequest);
        waiting5seconds();
    }

    public void filterByTNOrigin(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfTNOrigin, listOfActiveDropDown, searchRequest);
        waiting5seconds();
        waiting5seconds();
    }

    public void filterByZipCode(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        chooseElementFromSelectInAdminPanel(selectOfZipCodeRouted, listOfActiveDropDown, searchRequest);
        waiting5seconds();
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
            String ee = listTdOfScrollTable.get(i).getText();
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


}
