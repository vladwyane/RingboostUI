package pages.admin;

import blocks.admin.LoginForm;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.ConfigProperties;

/**
 * Created by bigdrop on 7/31/2019.
 */
public class Login extends BasePage {

    public Login(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        driver.get(ConfigProperties.getProperty("adminLogin.url"));
    }

    LoginForm loginForm;

    public void fillLoginForm() {
        type(loginForm.getLoginField(), "admin@admin.com");
        type(loginForm.getPasswordField(), "adminadmin");
        loginForm.getLoginButton().click();
    }

}
