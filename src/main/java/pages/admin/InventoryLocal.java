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

    @Name("List of states")
    @FindBys( {@FindBy(xpath = "//div[contains(@class, 'menuable__content__active')]//div[@class='v-list__tile__title']")} )
    private List<WebElement> listOfActiveDropDown;

    @FindBy(xpath = "(//p[contains(text(), 'Status')]/following::div)[1]")
    private WebElement selectOfStatus;

    @FindBy(xpath = "(//p[contains(text(), 'DID Source')]/following::div)[1]")
    private WebElement selectOfDidSource;

    @FindBy(xpath = "(//p[contains(text(), 'DID Origin')]/following::div)[1]")
    private WebElement selectOfDidOrigin;

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

    public void filterByCategory(String searchRequest) {
        waitUntilElementAppeared(tableTitle);
        type(inputCategories, searchRequest);
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
            if(listThStaticTable.get(i).getText().contains(filterParameter)){
                index = i;
                break;
            }
        }
        boolean correctFilter = false;
        for (int i = index; i < listTdOfStaticTable.size(); i+=listThStaticTable.size() - 1) {
            String er = listTdOfStaticTable.get(i).getText();
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
            if(listThScrollcTable.get(i).getText().contains(filterParameter)){
                index = i;
                break;
            }
        }
        boolean correctFilter = false;
        for (int i = index; i < listTdOfScrollTable.size(); i+=listThScrollcTable.size() - 1) {
            String er = listTdOfScrollTable.get(i).getText();
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
