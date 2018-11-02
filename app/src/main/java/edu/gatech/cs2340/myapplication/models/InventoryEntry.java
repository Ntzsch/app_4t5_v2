package edu.gatech.cs2340.myapplication.models;

public class InventoryEntry {

    private String timeStamp;
    private String location;
    private String smallDescription;
    private String fullDescription;
    private String value; //in dollars
    private String category;

    public InventoryEntry(String timeStamp, String location,
                          String smallDescription, String fullDescription,
                          String value, String category) {
        this.timeStamp = timeStamp;
        this.location = location;
        this.smallDescription = smallDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.category = category;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
