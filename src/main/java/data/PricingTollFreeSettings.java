package data;

/**
 * Created by bigdrop on 10/2/2019.
 */
public enum PricingTollFreeSettings {

    TERM_PREMIUM_TEST("5 years", "59.99", "50%", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", true, false, false),
    TERM_PREMIUM_UPDATE("5 years update", "59.99", "50%", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", true, false, false),
    TERM_SPARE_TEST("4 years", "49.99", "40%", "40", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", true, false, false),
    TERM_SPARE_UPDATE("4 years update", "49.99", "40%", "40", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", true, false, false),
    TERM_BASIC800_TEST("Independence", "49.99", "40%", "40", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", true, false, false),
    TERM_BASIC800_UPDATE("Independence update", "49.99", "40%", "40", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", true, false, false),
    MINUTES_TEST("6000 minutes", "6000", "Description text", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", false, false, false),
    MINUTES_UPDATE("6000 minutes update", "6000", "Description text", "50", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", false, true, false),
    MULTIPLE_AREA_TEST("6 areas", "60", "Description text", "60", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "6", false, true, false),
    MULTIPLE_AREA_UPDATE("7 areas", "70", "Description text", "70", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "7", false, true, false),
    MULTIPLE_3_AREA("3 areas", "30", "Description text", "30", "48", "Ad text", "US", "0.15",
            "500", "300", "0.1", "3", false, true, false),
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

    PricingTollFreeSettings(String name, String value, String description, String discount, String duration, String additionalText,
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
