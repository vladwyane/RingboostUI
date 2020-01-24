package data;

/**
 * Created by bigdrop on 6/7/2019.
 */
public enum CreditCards {

    VISA_STRIPE("Stripe VISA", "4242424242424242", "1221", "123"),
    TEST_CARD("Test Master", "5534727490123983", "1122", "128"),
    REAL_CARD("Visa", "5489221110296465", "0120", "438"),
    DISCOVER_STRIPE("Discover", "6011000990139424", "0121", "438"),
    ERROR_CVC_STRIPE("ERROR_CVC", "4000000000000127", "0121", "438"),
    ERROR_STOLEN_CARD_STRIPE("ERROR_STOLEN_CARD", "4000000000009979", "0121", "438"),
    ERROR_LOST_CARD_STRIPE("ERROR_LOST_CARD", "4000000000009987", "0121", "438"),
    ERROR_EXPIRED_CARD_STRIPE("ERROR_EXPIRED_CARD", "4000000000000069", "0122", "431"),
    ERROR_PROCESSING_STRIPE("ERROR_EXPIRED_CARD", "4000000000000119", "0122", "431"),
    ERROR_INSUFFICIENT_FUNDS_STRIPE("ERROR_EXPIRED_CARD", "4000000000009995", "0122", "431"),
    AMERICAN_EXPRESS_STRIPE("American", "378282246310005", "0121", "438"),
    JCB("JCB", "3566002020360505", "0121", "438"),
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
