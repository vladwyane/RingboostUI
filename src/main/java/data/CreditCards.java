package data;

/**
 * Created by bigdrop on 6/7/2019.
 */
public enum CreditCards {

    VISA_STRIPE("Stripe VISA", "4242424242424242", "1219", "123"),
    TEST_CARD("Test Master", "5534727490123983", "1122", "128"),
    REAL_CARD("Visa", "5489221110296465", "0120", "438"),
    DISCOVER_STRIPE("Discover", "6011000990139424", "0121", "438"),
    ERROR_STRIPE("ERROR_CVC", "4000000000000127", "0121", "438"),
    MASTERCART_STRIPE("Stripe Master", "5555555555554444", "1021", "989");

    private String cardName;
    private String cardNumber;
    private String cardExpDate;
    private String cardCVV;

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpDate() {
        return cardExpDate;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    CreditCards(String cardName, String cardNumber, String cardExpDate, String cardCVV) {
        this.cardNumber = cardNumber;
        this.cardExpDate = cardExpDate;
        this.cardCVV = cardCVV;
        this.cardName = cardName;
    }
}
