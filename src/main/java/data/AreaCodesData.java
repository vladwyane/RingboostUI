package data;

/**
 * Created by bigdrop on 10/7/2019.
 */
public enum AreaCodesData {

    AREA_CODES_FLORIDA ("239", "305", "786", "352", "1.1", "Bundle Florida"),
    AREA_CODES_239 ("239", "305", "786", "352", "1.1", "239"),
    AREA_CODES_FLORIDA_UPDATE ("352", "305", "786", "352", "1.1", "352"),
    AREA_CODES_FLORIDA_199 ("199", "305", "786", "352", "1.1", "Bundle Florida"),
    AREA_CODES_FLORIDA_1000 ("1000", "305", "786", "352", "1.1", "Bundle Florida"),
    AREA_CODES_INDIANA ("317", "812", "930", "931", "1.3", "Bundle Indiana");


    private String areaCode1;
    private String areaCode2;
    private String areaCode3;
    private String areaCode4;
    private String multiplier;
    private String groupName;

    public String getAreaCode1() {
        return areaCode1;
    }

    public String getAreaCode2() {
        return areaCode2;
    }

    public String getAreaCode3() {
        return areaCode3;
    }

    public String getAreaCode4() {
        return areaCode4;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public String getGroupName() {
        return groupName;
    }

    AreaCodesData(String areaCode1, String areaCode2, String areaCode3, String areaCode4, String multiplier, String groupName) {
        this.areaCode1 = areaCode1;
        this.areaCode2 = areaCode2;
        this.areaCode3 = areaCode3;
        this.areaCode4 = areaCode4;
        this.multiplier = multiplier;
        this.groupName = groupName;
    }
}
