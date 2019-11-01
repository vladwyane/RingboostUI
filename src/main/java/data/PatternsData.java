package data;

/**
 * Created by bigdrop on 10/31/2019.
 */
public enum PatternsData {

    QUALITY("Vladik", "word", "vanity", "Top 6 Digit"),
    VANITY("Vanity", "word", "vanity", "Vanity");

    private String namePatterns;
    private String typePattern;
    private String flowType;
    private String category;

    public String getNamePatterns() {
        return namePatterns;
    }

    public String getTypePattern() {
        return typePattern;
    }

    public String getFlowType() {
        return flowType;
    }

    public String getCategory() {
        return category;
    }

    PatternsData(String namePatterns, String typePattern, String flowType, String category) {
        this.namePatterns = namePatterns;
        this.typePattern = typePattern;
        this.flowType = flowType;
        this.category = category;
    }
}
