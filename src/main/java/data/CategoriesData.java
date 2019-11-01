package data;

/**
 * Created by bigdrop on 10/31/2019.
 */
public enum CategoriesData {

    QUALITY("Quality", "Quality", "0", "vanity", "Elite"),
    UPDATE_QUALITY("Update", "Update", "20", "vanity", "A1");

    private String name;
    private String slug;
    private String order;
    private String phoneType;
    private String tier;

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getOrder() {
        return order;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public String getTier() {
        return tier;
    }

    CategoriesData(String name, String slug, String order, String phoneType, String tier) {
        this.name = name;
        this.slug = slug;
        this.order = order;
        this.phoneType = phoneType;
        this.tier = tier;
    }
}
