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
}
