package pages.admin;

import blocks.admin.categories.CategoriesTable;
import blocks.admin.categories.NewCategoryPopup;
import data.CategoriesData;
import data.OwnersData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 10/30/2019.
 */
public class CategoryLocal extends BasePage {

    public CategoryLocal(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("adminCategoryLocal.url"));
    }

    CategoriesTable categoriesTable;
    NewCategoryPopup newCategoryPopup;

    public void clickAddNewCategoryButton() {
        waitUntilElementAppeared(categoriesTable.getButtonNewCategory());
        waitUntilElementWillBeClickable(categoriesTable.getButtonNewCategory());
        categoriesTable.getButtonNewCategory().click();
    }

    public void clickAddCategoryButton() {
        waitUntilElementAppeared(newCategoryPopup.getButtonAddCategory());
        waitUntilElementWillBeClickable(newCategoryPopup.getButtonAddCategory());
        newCategoryPopup.getButtonAddCategory().click();
        waiting2seconds();
    }

    public void clickCancelButton() {
        waitUntilElementAppeared(newCategoryPopup.getButtonCancel());
        waitUntilElementWillBeClickable(newCategoryPopup.getButtonCancel());
        newCategoryPopup.getButtonCancel().click();
    }

    public void choosePhoneType(String phoneType) {
        waitUntilElementAppeared(newCategoryPopup.getSelectPhoneType());
        newCategoryPopup.getSelectPhoneType().click();
        waiting2seconds();
        for(WebElement element : newCategoryPopup.getListOfPhoneTypes()) {
            if (element.getText().equals(phoneType)) {
                element.click();
                return;
            }
        }
        newCategoryPopup.getListOfPhoneTypes().get(0).click();
    }

    public void chooseTier(String tier) {
        waitUntilElementAppeared(newCategoryPopup.getSelectTier());
        newCategoryPopup.getSelectTier().click();
        waiting2seconds();
        for(WebElement element : newCategoryPopup.getListOfTiers()) {
            if (element.getText().equals(tier)) {
                element.click();
                return;
            }
        }
        newCategoryPopup.getListOfTiers().get(0).click();
    }

    public void fillNewCategoryForm(CategoriesData categoriesData) {
        waitUntilElementAppeared(newCategoryPopup.getButtonAddCategory());
        type(newCategoryPopup.getNameField(), categoriesData.getName());
        type(newCategoryPopup.getOrderField(), categoriesData.getOrder());
    }

    public void createNewCategory(CategoriesData categoriesData) {
        fillNewCategoryForm(categoriesData);
        chooseTier(categoriesData.getTier());
        waitUntilElementWillBeClickable(newCategoryPopup.getButtonAddCategory());
        newCategoryPopup.getButtonAddCategory().click();
    }

    public void editCategory(CategoriesData categoriesData) {
        waitUntilElementAppeared(newCategoryPopup.getButtonAddCategory());
        newCategoryPopup.getNameField().sendKeys(Keys.CONTROL + "a");
        newCategoryPopup.getNameField().sendKeys(Keys.DELETE);
        newCategoryPopup.getOrderField().sendKeys(Keys.CONTROL + "a");
        newCategoryPopup.getOrderField().sendKeys(Keys.DELETE);
        type(newCategoryPopup.getNameField(), categoriesData.getName());
        type(newCategoryPopup.getOrderField(), categoriesData.getOrder());
        waitUntilElementWillBeClickable(newCategoryPopup.getButtonAddCategory());
        newCategoryPopup.getButtonAddCategory().click();
    }

    public void searchCategory (String searchRequest) {
        waiting2seconds();
        categoriesTable.getSearchField().sendKeys(Keys.CONTROL + "a");
        categoriesTable.getSearchField().sendKeys(Keys.DELETE);
        type(categoriesTable.getSearchField(), searchRequest);
        categoriesTable.getSearchField().sendKeys(Keys.ENTER);
        waiting2seconds();
    }

    public void clickEditIconFirstCategory() {
        waitUntilElementAppeared(categoriesTable.getListOfActions().get(0));
        waitUntilElementWillBeClickable(categoriesTable.getListOfActions().get(0));
        categoriesTable.getListOfActions().get(0).click();
    }

    public void clickDeleteIconFirstCategory() {
        waitUntilElementAppeared(categoriesTable.getListOfActions().get(1));
        waitUntilElementWillBeClickable(categoriesTable.getListOfActions().get(1));
        categoriesTable.getListOfActions().get(1).click();
        waiting2seconds();
        categoriesTable.getButtonDelete().click();
        waiting2seconds();
    }

    public void checkingSuccessAlertMessage() {
        waitUntilElementAppeared(categoriesTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(categoriesTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void checkingErrorMessagesAllFieldsIsEmpty() {
        waitUntilElementAppeared(newCategoryPopup.getListOfErrorMessage().get(0));
        softAssert.assertEquals(newCategoryPopup.getListOfErrorMessage().get(0).getText(), "The name must be a string.The name must be at least 3 characters.The name field is required.");
        softAssert.assertEquals(newCategoryPopup.getListOfErrorMessage().get(1).getText(), "The slug field is required.");
        softAssert.assertEquals(newCategoryPopup.getListOfErrorMessage().get(4).getText(), "The order must be an integer.");
        softAssert.assertEquals(newCategoryPopup.getListOfErrorMessage().get(5).getText(), "The local tier id field is required when type is local.");
        softAssert.assertAll();
    }

    public void checkingNameFirstCategory(CategoriesData categoriesData) {
        softAssert.assertEquals(categoriesTable.getListTd().get(0).getText(), categoriesData.getName());
        softAssert.assertAll();
    }

    public void checkingErrorMessagesIsAbsent() {
        waitUntilElementAppeared(newCategoryPopup.getButtonAddCategory());
        waiting2seconds();
        softAssert.assertTrue(isElementInvisible(newCategoryPopup.getListOfErrorMessage().get(0)));
        softAssert.assertAll();
    }

    public void checkingErrorMessagesNameHasBeenUsed() {
        waitUntilElementAppeared(newCategoryPopup.getButtonAddCategory());
        waiting2seconds();
        softAssert.assertEquals(newCategoryPopup.getListOfErrorMessage().get(1).getText(), "The slug has already been taken.");
        softAssert.assertAll();
    }

    public void checkingSuccessDeleted() {
        waitUntilElementAppeared(categoriesTable.getSuccessAlert());
        boolean result = isElementContainsAttributeValue(categoriesTable.getSuccessAlert(), "style", "display");
        softAssert.assertFalse(result);
        softAssert.assertAll();
    }

    public void clickColumnHeaderOfTable(String titleTh) {
        waitUntilElementAppeared(categoriesTable.getListColumnHeader().get(0));
        waitUntilElementWillBeClickable(categoriesTable.getListColumnHeader().get(0));
        for (int i = 0; i < categoriesTable.getListColumnHeader().size(); i++) {
            if(categoriesTable.getListColumnHeader().get(i).getText().contains(titleTh)){
                categoriesTable.getListColumnHeader().get(i).click();
                return;
            }
        }
    }

    public String getFistTdByColumn(String headingColumn) {
        int indexColumn = 0;
        for (int i = 0; i < categoriesTable.getListColumnHeader().size(); i++) {
            if(categoriesTable.getListColumnHeader().get(i).getText().contains(headingColumn)){
                indexColumn = i;
                break;
            }
        }
        waiting2seconds();
        return categoriesTable.getListTd().get(indexColumn).getText();
    }

    public void checkingSuccessSorting(String beforeSorting, String afterSorting) {
        softAssert.assertNotEquals(beforeSorting, afterSorting);
        softAssert.assertAll();
    }
}

