package data;

/**
 * Created by bigdrop on 8/20/2019.
 */
public enum Carriers {

    VODAFONE ("Vodafone", "1", "135", "2", "2334", "3.14", "Comments"),
    ALPHABET ("alphabet", "123", "1", "569", "1", "256.01", ""),
    ABC ("ABC", "0.01", "0.01", "0.01", "0.01", "0.01", "Comments Comments Comments CommentsComments " +
            "CommentsCommentsComments Comments Comments Comments CommentsComments Comments Comments Comments");

    private String carriers;
    private String mrc;
    private String nrc;
    private String perMinuteUsage;
    private String portInFees;
    private String portOutFees;
    private String notes;


    Carriers(String carriers, String mrc, String nrc, String perMinuteUsage, String portInFees, String portOutFees, String notes) {
        this.carriers = carriers;
        this.mrc = mrc;
        this.nrc = nrc;
        this.perMinuteUsage = perMinuteUsage;
        this.portInFees = portInFees;
        this.portOutFees = portOutFees;
        this.notes = notes;
    }

    public String getCarriers() {
        return carriers;
    }

    public void setCarriers(String carriers) {
        this.carriers = carriers;
    }

    public String getMrc() {
        return mrc;
    }

    public void setMrc(String mrc) {
        this.mrc = mrc;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getPerMinuteUsage() {
        return perMinuteUsage;
    }

    public void setPerMinuteUsage(String perMinuteUsage) {
        this.perMinuteUsage = perMinuteUsage;
    }

    public String getPortInFees() {
        return portInFees;
    }

    public void setPortInFees(String portInFees) {
        this.portInFees = portInFees;
    }

    public String getPortOutFees() {
        return portOutFees;
    }

    public void setPortOutFees(String portOutFees) {
        this.portOutFees = portOutFees;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
