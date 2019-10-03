package data;

/**
 * Created by bigdrop on 10/2/2019.
 */
public enum TFNumberSettings {

    TERMS_TEST("5 years", "59.99", "Description text", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", true, false, false),
    TERMS_UPDATE("5 years update", "59.99", "Description text", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", true, false, false),
    MINUTES_TEST("300 minutes", "10", "Description text", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", false, false, false),
    MULTIPLE_AREA_TEST("Area", "10", "Description text", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", false, true, false),
    PRICE_TIER_TEST("Tier $501", "501", "Description text", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", false, false, false),
    PRICE_TIER_UPDATE("UpdateTier $501", "501", "Description text", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", false, false, false);

    private String name;
    private String value;
    private String description;
    private String discount;
    private String duration;
    private String additionalText;
    private String country;
    private String additionalCoast;
    private String activationFee;
    private String price;
    private String pricePerMinute;
    private String number;
    private boolean isPopularCheckbox;
    private boolean isActiveCheckbox;
    private boolean contactUsCheckbox;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getDiscount() {
        return discount;
    }

    public String getDuration() {
        return duration;
    }

    public String getAdditionalText() {
        return additionalText;
    }

    public String getCountry() {
        return country;
    }

    public String getAdditionalCoast() {
        return additionalCoast;
    }

    public String getActivationFee() {
        return activationFee;
    }

    public String getPrice() {
        return price;
    }

    public String getPricePerMinute() {
        return pricePerMinute;
    }

    public String getNumber() {
        return number;
    }

    public boolean isPopularCheckbox() {
        return isPopularCheckbox;
    }

    public boolean isActiveCheckbox() {
        return isActiveCheckbox;
    }

    public boolean isContactUsCheckbox() {
        return contactUsCheckbox;
    }

    TFNumberSettings(String name, String value, String description, String discount, String duration, String additionalText,
                     String country, String additionalCoast, String activationFee, String price,
                     String pricePerMinute, String number, boolean isPopularCheckbox, boolean isActiveCheckbox, boolean contactUsCheckbox) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.discount = discount;
        this.duration = duration;
        this.additionalText = additionalText;
        this.country = country;
        this.additionalCoast = additionalCoast;
        this.activationFee = activationFee;
        this.price = price;
        this.pricePerMinute = pricePerMinute;
        this.number = number;
        this.isPopularCheckbox = isPopularCheckbox;
        this.isActiveCheckbox = isActiveCheckbox;
        this.contactUsCheckbox = contactUsCheckbox;
    }
}
