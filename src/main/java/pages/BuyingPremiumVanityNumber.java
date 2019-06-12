package pages;

import blocks.AvailableAreaCodesBlock;
import blocks.AvailableByMarketOrNationwide;
import blocks.DragAndDropBlock;
import blocks.SelectedAreaCodes;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class BuyingPremiumVanityNumber extends BasePage {

    public BuyingPremiumVanityNumber(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    private AvailableByMarketOrNationwide availableByMarketOrNationwide;
    private DragAndDropBlock dragAndDropBlock;
    private AvailableAreaCodesBlock availableAreaCodesBlock;
    private SelectedAreaCodes selectedAreaCodes;

    @FindBy(xpath= "//button[contains(text(), 'continue')]")
    private WebElement continueButton;

    public void clickButtonChooseMyAreas() {
        waitUntilElementWillBeClickable(availableByMarketOrNationwide.getButtonSelectMyAreas());
        availableByMarketOrNationwide.getButtonSelectMyAreas().click();
    }

    public void clickButtonChooseNationwide() {
        waitUntilElementWillBeClickable(availableByMarketOrNationwide.getButtonSelectNationwide());
        availableByMarketOrNationwide.getButtonSelectNationwide().click();
    }

    public void chooseState(String stateName) {
        waitUntilElementAppeared(dragAndDropBlock.getSelectState());
        scrollToElement(dragAndDropBlock.getSelectState());
        dragAndDropBlock.getSelectState().click();
        waitUntilElementAppeared(dragAndDropBlock.getListStates().get(0));
        for(WebElement element : dragAndDropBlock.getListStates()) {
            if (element.getText().equals(stateName) || element.equals(dragAndDropBlock.getListStates().get(dragAndDropBlock.getListStates().size() - 1))) {
                element.click();
                return;
            }
        }
    }

    public double chooseFirstAreaCodeFromList() {
        waitUntilElementAppeared(availableAreaCodesBlock.getDragBoxTitle());
        waitUntilElementWillBeClickable(availableAreaCodesBlock.getListAreaCodes().get(0));
        availableAreaCodesBlock.getListAreaCodes().get(0).click();
        double price = Double.parseDouble(getNumbersFromString(selectedAreaCodes.getListPricesSelectedAreaCodes().get(0).getText()));
        waitUntilElementWillBeClickable(continueButton);
        continueButton.click();
        return price;
    }




}
