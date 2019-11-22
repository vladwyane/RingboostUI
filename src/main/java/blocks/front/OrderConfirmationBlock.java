package blocks.front;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import java.io.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Block(@FindBy(className = "order-confirmation"))
public class OrderConfirmationBlock extends HtmlElement {

    @FindBy(xpath= "//a[contains(text(),'Order Details')]")
    private WebElement linkOrderDetails;

    @FindBy(xpath= "//*[contains(text(),'Recurring Monthly')]/following::div[1]")
    private WebElement priceRecurringMonthly;

    @FindBy(xpath= "//*[contains(text(),'Due Today')]/following::div[1]")
    private WebElement priceTotalDueToday;

    @FindBy(xpath= "//*[contains(text(),'Pay Today')]/following::div[1]")
    private WebElement pricePayToday;

    @FindBy(xpath= "//*[contains(text(),'Promo')]/following::div[1]")
    private WebElement priceAfterAppliedPromoCode;

    @FindBy(css= ".order-title")
    private WebElement orderTitle;

    @FindBy(xpath= "//div[@class='number-type']//preceding::strong")
    private WebElement phoneNumber;

    public WebElement getPriceTotalDueTodayWithException() {
        try {
            return priceTotalDueToday;
        }
        catch (Exception e) {
            return null;
        }

    }

    public WebElement getPricePayTodayWithException() {
        try {
            return pricePayToday;
        }
        catch (Exception e) {
            return null;
        }
    }

    public WebElement getPriceAfterAppliedPromoCodeWithException() {
        try {
            return priceAfterAppliedPromoCode;
        }
        catch (Exception e) {
            return null;
        }
    }

    public WebElement getPhoneNumberWithException() {
        try {
            return phoneNumber;
        }
        catch (Exception e) {
            return null;
        }
    }

    public void generateOrdersDetailFile() throws IOException, JSONException {
        File file = new File("src/main/resources/ordersDetail.json");
        JSONObject orderDetailData = generateOrdersDetailData();
        save(orderDetailData, file);
    }

    public void generateOrdersDetailFileWthPromo() throws IOException, JSONException {
        File file = new File("src/main/resources/ordersDetail.json");
        JSONObject orderDetailData = generateOrdersDetailDataWithPromo();
        save(orderDetailData, file);
    }

    private void save(JSONObject orderDetailList, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(orderDetailList);
        Writer writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(json);
        writer.close();
    }

    private JSONObject generateOrdersDetailDataWithPromo() throws JSONException {
        JSONObject orderDetail = new JSONObject();
        orderDetail.put("phoneNumber", getPhoneNumberWithException().getText());
        orderDetail.put("currentDate", new Date().toString());
        orderDetail.put("priceTotalDueToday", getPriceTotalDueTodayWithException().getText());
        orderDetail.put("priceAfterAppliedPromoCode", getPriceAfterAppliedPromoCodeWithException().getText());
        orderDetail.put("pricePayToday", getPricePayTodayWithException().getText());
        return orderDetail;
    }

    private JSONObject generateOrdersDetailData() throws JSONException {
        JSONObject orderDetail = new JSONObject();
        orderDetail.put("phoneNumber", getPhoneNumberWithException().getText());
        orderDetail.put("currentDate", new Date().toString());
        orderDetail.put("priceTotalDueToday", getPriceTotalDueTodayWithException().getText());
        return orderDetail;
    }



}
