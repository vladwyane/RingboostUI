package adminPanel;

import data.Carriers;
import org.json.JSONException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.admin.*;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/1/2019.
 */
public class Sprint_3_TFPhoneNumberExtras extends TestBase {

    private Login login;
    private Admin admin;
    private ExtrasTFNumbers extrasTFNumbers;


    @BeforeClass
    public void initPageObjects() {
        login = new Login(app.getDriver());
        admin = new Admin(app.getDriver());
        extrasTFNumbers = new ExtrasTFNumbers(app.getDriver());
        login.open();
        login.fillLoginForm();
    }

    @AfterClass
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test1SuccessCreateNewCarrier() throws InterruptedException, IOException, JSONException {
        login.open();
        extrasTFNumbers.clickSave();
    }
}
