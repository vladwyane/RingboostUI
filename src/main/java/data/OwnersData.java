package data;

/**
 * Created by bigdrop on 10/15/2019.
 */
public enum OwnersData {

    VLADYSLAV("bigdrop", "Vladyslav Chesalov", "0669948723", "vladyslav.chesalov+1@bigdropinc.com", "20"),
    JAMES("Lakers", "Lebron James", "0669948721", "lebron.james+1@bigdropinc.com", "1"),
    RAY("Sony", "Ray Allen", "0669948712", "ray.allen+1@bigdropinc.com", "99");

    private String company;
    private String contactName;
    private String phone;
    private String email;
    private String commission;

    public String getCompany() {
        return company;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCommission() {
        return commission;
    }

    OwnersData(String company, String contactName, String phone, String email, String commission) {
        this.company = company;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
        this.commission = commission;
    }
}
