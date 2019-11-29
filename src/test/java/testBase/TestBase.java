package testBase;

import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

/**
 * Created by bigdrop on 3/14/2019.
 */
@Listeners(MyTestListener.class)
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite
    public static void setupClass() {
        app.setup();
        Reporter.log("Before Suit executed",1,true);
    }
    @BeforeClass
    public void setupTest(ITestContext context) throws Exception{
        if(getClass().getName().equals("tollFreeFlow.LicensingVanityPremiumNumbers") ||
                getClass().getName().equals("adminPanel.sprint_3_PricingTollFree.TermPremium") ||
                getClass().getName().equals("adminPanel.sprint_3_PricingTollFree.CostPerMinute") ||
                getClass().getName().equals("adminPanel.sprint_3_PricingTollFree.MultipleAreas") ||
                getClass().getName().equals("adminPanel.sprint_5_CustomersOrder.OrdersTollFreePremium"))
            app.unitMobileView();
        else app.unit();
        context.setAttribute("app", app);
    }

    @AfterClass
    public void teardown() {
        app.stop();
    }
}
