import org.json.JSONException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.Login;
import pages.front.HomePage;
import testBase.TestBase;

import java.io.IOException;

/**
 * Created by bigdrop on 10/8/2019.
 */
public class TestBallet extends TestBase {

    private HomePage homePage;
    private Login login;

    @BeforeMethod
    public void initPageObjects() {
        homePage = new HomePage(app.getDriver());
        login = new Login(app.getDriver());
    }


    @AfterMethod
    public void clearAllCookies() {
        app.delleteAllCookies();
    }

    @Test
    public void test() throws InterruptedException, IOException, JSONException {
        app.getDriver().navigate().to("https://ballet-dev.bigdropinc.net/customer/account/login");
        app.getDriver().findElement(By.id("email")).sendKeys("vladyslav.chesalov+1@bigdropinc.com");
        app.getDriver().findElement(By.id("pass")).sendKeys("JR6GMs4ywG");
        homePage.scrollToElement( app.getDriver().findElement(By.id("send2")));
        app.getDriver().findElement(By.id("send2")).click();

    }

    @Test
    public void test2() throws InterruptedException, IOException, JSONException {
        app.getDriver().navigate().to("https://ballet-dev.bigdropinc.net/customer/account/create");
        app.getDriver().findElement(By.id("firstname")).sendKeys("Vladyslav12");
        app.getDriver().findElement(By.id("lastname")).sendKeys("Chesalov12");
        app.getDriver().findElement(By.id("email_address")).sendKeys("vladyslav.chesalov+12@bigdropinc.com");
        app.getDriver().findElement(By.id("password")).sendKeys("JR6GMs4ywG");
        app.getDriver().findElement(By.id("confirmation")).sendKeys("JR6GMs4ywG");
        homePage.scrollToElement( app.getDriver().findElement(By.xpath("//button[@title='Register']")));
        app.getDriver().findElement(By.xpath("//button[@title='Register']")).click();
        String a = app.getDriver().findElement(By.cssSelector(".error-msg")).getText();

    }

    @Test
    public void test3() throws InterruptedException, IOException, JSONException {
        app.getDriver().navigate().to("http://ges-dev.bigdropinc.net/request-a-quote/");
        app.getDriver().findElement(By.id("first_name")).sendKeys("Vladyslav1");
        app.getDriver().findElement(By.id("last_name")).sendKeys("Chesalov1");
        app.getDriver().findElement(By.id("company_name")).sendKeys("Bigdrop");
        app.getDriver().findElement(By.id("email")).sendKeys("vladyslav.chesalov+1@bigdropinc.com");
        app.getDriver().findElement(By.id("phone_number")).sendKeys("4444444444");
        app.getDriver().findElement(By.id("manufacturer")).sendKeys("1");
        app.getDriver().findElement(By.id("model_number")).sendKeys("1");
        app.getDriver().findElement(By.xpath("//button[contains(text(), 'Send Your Request')]")).click();

    }

    @Test
    public void test4() throws InterruptedException, IOException, JSONException {
        app.getDriver().navigate().to("http://www.toledorocket.com/perftest/uploadtest/fileselect.asp");
        app.getDriver().findElement(By.name("FILE1")).sendKeys("F:\\projects\\RingboostUI\\src\\main\\resources\\ordersDetail.json");

    }

    @Test
    public void test5() throws InterruptedException, IOException, JSONException {
        login.open();
        login.fillLoginForm();
        app.getDriver().navigate().to("https://ringboost-dev.bigdropinc.net/admin/owners/5");
        app.getDriver().findElement(By.xpath("//input[@type='file']")).sendKeys("F:\\projects\\RingboostUI\\src\\main\\resources\\ordersDetail.json");

    }
}
