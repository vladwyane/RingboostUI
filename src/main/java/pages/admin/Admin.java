package pages.admin;

import blocks.admin.SidebarNavigation;
import blocks.admin.ToolbarPanel;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by bigdrop on 7/31/2019.
 */
public class Admin extends BasePage {

    public Admin(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    SidebarNavigation sidebarNavigation;
    ToolbarPanel toolbarPanel;

    public void clickToolFreInventoryLink() {
        waitUntilElementAppeared(toolbarPanel.getBurgerButton());
      //  toolbarPanel.getBurgerButton().click();
        sidebarNavigation.getInventoryLink().click();
        waitUntilElementAppeared(sidebarNavigation.getTollfreeLink());
        sidebarNavigation.getTollfreeLink().click();
    }

    public void clickLocalInventoryLink() {
        waitUntilElementAppeared(toolbarPanel.getBurgerButton());
      //  toolbarPanel.getBurgerButton().click();
        sidebarNavigation.getInventoryLink().click();
        waitUntilElementAppeared(sidebarNavigation.getLocalLink());
        sidebarNavigation.getLocalLink().click();
    }

    public void clickCarriersLink() {
        waitUntilElementAppeared(sidebarNavigation.getExtraslLink());
        sidebarNavigation.getExtraslLink().click();
        waitUntilElementAppeared(sidebarNavigation.getCarriersLink());
        sidebarNavigation.getCarriersLink().click();
    }

    public void clickApiLink() {
        waitUntilElementAppeared(sidebarNavigation.getSettingsLink());
        sidebarNavigation.getSettingsLink().click();
        waitUntilElementAppeared(sidebarNavigation.getApiLink());
        sidebarNavigation.getApiLink().click();
    }

    public void clickPricingTollFreeLink() {
        waitUntilElementAppeared(sidebarNavigation.getPricingGlobalLink());
        sidebarNavigation.getPricingGlobalLink().click();
        waitUntilElementAppeared(sidebarNavigation.getTollFreePriceLink());
        sidebarNavigation.getTollFreePriceLink().click();
    }

    public void clickAreaCodesLink() {
        waitUntilElementAppeared(sidebarNavigation.getPricingGlobalLink());
        sidebarNavigation.getPricingGlobalLink().click();
        waitUntilElementAppeared(sidebarNavigation.getAreaCodesLink());
        sidebarNavigation.getAreaCodesLink().click();
    }

    public void clickOwnersLink() {
        waitUntilElementAppeared(sidebarNavigation.getOwnersLink());
        sidebarNavigation.getOwnersLink().click();
    }

    public void clickPriceMatrixLink() {
        waitUntilElementAppeared(sidebarNavigation.getPricingGlobalLink());
        sidebarNavigation.getPricingGlobalLink().click();
        waitUntilElementAppeared(sidebarNavigation.getPriceMatrixLink());
        sidebarNavigation.getPriceMatrixLink().click();
    }

    public void clickPatternLocal() {
        waitUntilElementAppeared(sidebarNavigation.getPricingGlobalLink());
        sidebarNavigation.getPricingGlobalLink().click();
        waitUntilElementAppeared(sidebarNavigation.getPriceMatrixLink());
        sidebarNavigation.getPriceMatrixLink().click();
    }

    public void clickCustomersLink() {
        waitUntilElementAppeared(sidebarNavigation.getCustomersLink());
        sidebarNavigation.getCustomersLink().click();
    }

    public void clickOrdersLocal() {
        waitUntilElementAppeared(sidebarNavigation.getOrdersLink());
        sidebarNavigation.getOrdersLink().click();
        waitUntilElementAppeared(sidebarNavigation.getOrdersLocalLink());
        sidebarNavigation.getOrdersLocalLink().click();
    }

    public void clickOrdersTollFree() {
        waitUntilElementAppeared(sidebarNavigation.getOrdersLink());
        sidebarNavigation.getOrdersLink().click();
        waitUntilElementAppeared(sidebarNavigation.getOrdersTollFreeLink());
        sidebarNavigation.getOrdersTollFreeLink().click();
    }
}
