package data;

/**
 * Created by bigdrop on 9/9/2019.
 */
public enum PromoCodes {

    FIXED_PROMOCODE("springsale", 5.0),
    HIGH_FIXED_PROMOCODE("summersale", 4000.0),
    PERCENT_PROMOCODE("wintersale", 10.0);

    private String name;
    private double value;

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    PromoCodes(String name, double value) {
        this.name = name;
        this.value = value;
    }

}
