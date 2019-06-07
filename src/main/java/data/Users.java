package data;

/**
 * Created by bigdrop on 6/7/2019.
 */
public enum Users {

    VLADYSLAV("Vladyslav", "Chesalov", "vladyslav.chesalov@bigdropinc.com", "3275 NW 24th Street Rd", "apt Suit", "Miami", "FL", "33101", "0123456789");

    private String firstName;
    private String lastName;
    private String email;
    private String streetAddress;
    private String aptSuite;
    private String city;
    private String state;
    private String zipCode;
    private String phone;

    Users(String firstName, String lastName, String email, String streetAddress, String aptSuite, String city, String state, String zipCode, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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