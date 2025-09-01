package eku.EcommerceApp.model;

public class DtoNew {

    private String lastUpdated;
    private String last_updated_epoch;

    public DtoNew() {
    }

    public DtoNew(String lastUpdated, String last_updated_epoch) {
        this.lastUpdated = lastUpdated;
        this.last_updated_epoch = last_updated_epoch;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLast_updated_epoch() {
        return last_updated_epoch;
    }

    public void setLast_updated_epoch(String last_updated_epoch) {
        this.last_updated_epoch = last_updated_epoch;
    }
}
