package pages.admin;

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
public class InventoryTollfree extends BasePage{

    public InventoryTollfree(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

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


}
