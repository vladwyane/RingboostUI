package data;

/**
 * Created by bigdrop on 6/7/2019.
 */
public enum Users {

    VLADYSLAV_44("Vladyslav44", "Chesalov44", "vladyslav.chesalov+44@bigdropinc.com", "", "3275 NW 24th Street Rd", "apt Suit", "Miami", "FL", "33101", "0123456789"),
    VLADYSLAV_45("Vladyslav45", "Chesalov45", "vladyslav.chesalov+45@bigdropinc.com", "Bigdrop", "103 North Perry St.", "", "Montgomery", "AL", "36043", "0987654321"),
    VLADYSLAV_46("Vladyslav46", "Chesalov46", "vladyslav.chesalov+46@bigdropinc.com", "Bigdrop", "29 Puerto Rico Addresses", "", "Montgomery", "PR", "00908", "0668843478"),
    CUSTOMER("Customer", "Customer", "customer@bigdropinc.com", "Bigdrop", "29 Puerto Rico Addresses", "", "Montgomery", "PR", "00908", "0668843478"),
    CUSTOMER_ONLY_EMAIL("", "", "customer@bigdropinc.com", "", "", "", "", "", "", "");

    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String streetAddress;
    private String aptSuite;
    private String city;
    private String state;
    private String zipCode;
    private String phone;

    Users(String firstName, String lastName, String email, String companyName, String streetAddress, String aptSuite, String city, String state, String zipCode, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.companyName = companyName;
        this.streetAddress = streetAddress;
        this.aptSuite = aptSuite;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return companyName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getAptSuite() {
        return aptSuite;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

}