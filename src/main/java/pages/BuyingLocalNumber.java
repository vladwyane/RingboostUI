package pages;

import blocks.LocalNumbersBlock;
import blocks.LocalSupportSolutionBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by bigdrop on 6/14/2019.
 */
public class BuyingLocalNumber extends BasePage {

    public BuyingLocalNumber(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    private LocalNumbersBlock localNumbersBlock;
    private LocalSupportSolutionBlock localSupportSolutionBlock;

    @FindBy(css= ".text-center button")
    private WebElement continueButton;

    @FindBy(css= ".title-price")
    private WebElement priceNumber;

    @FindBy(css= ".main-number-holder")
    private WebElement phoneNumber;

    public double choosePlan(String planName) {
        waiting2seconds();
        double pricePlan;
        for (int i = 0; i < localSupportSolutionBlock.getListTitlesSolutionItems().size(); i++) {
            if(localSupportSolutionBlock.getListTitlesSolutionItems().get(i).getText().equals(planName)) {
                pricePlan = Double.parseDouble(getNumbersFromString(localSupportSolutionBlock.getListPricesSolutionItems().get(i).getText()));

                localSupportSolutionBlock.getListButtonsSolutionItems().get(i).click();
                return pricePlan;
            }
        }
        scrollToElement(localSupportSolutionBlock.getListButtonsSolutionItems().get(0));
        localSupportSolutionBlock.getListButtonsSolutionItems().get(0).click();
        pricePlan = 0;
        return pricePlan;
    }
}
