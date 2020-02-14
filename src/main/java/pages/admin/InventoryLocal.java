package pages.admin;

import blocks.admin.generatorLinks.ListGeneratedURL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.BasePage;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TextInput;

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
}
