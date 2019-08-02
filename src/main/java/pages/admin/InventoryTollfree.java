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

    @Name("ArrayList td of table")
    @FindBys( {@FindBy(css = "tbody tr td")} )
    public List<WebElement> listTdOfTable;

    public void searchNumber(int index, String text) {
        waitUntilElementAppeared(listSearchIconTh.get(0));
        type(listSearchFieldTh.get(index),text);
        listSearchIconTh.get(index).click();
        waiting2seconds();
    }

    public String clickCreateNewLinkByNumber(int indexNumber) {
        String phoneNumber = listTdOfTable.get(indexNumber * 7).getText();
        listTdOfTable.get(indexNumber * 7 + 6).click();
        return phoneNumber;
    }


}
