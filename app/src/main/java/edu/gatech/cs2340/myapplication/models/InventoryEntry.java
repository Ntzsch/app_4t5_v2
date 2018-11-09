package edu.gatech.cs2340.myapplication.models;

/**
 * Class of Inventory Entries
 */
public class InventoryEntry {

    private String timeStamp;
    private String location;
    private String smallDescription;
    private String fullDescription;
    private String value; //in dollars
    private String category;

    /**
     * the constructor to create an inventory entry
     *
     * @param timeStamp the time item was donated
     * @param location the location where item was donated
     * @param smallDescription a short description of when item was donated
     * @param fullDescription a longer descrption of when item wasd onated
     * @param value the cost of the item
     * @param category the category of the item donated
     */
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

    /**
     * a getter of an items timestamp
     * @return string representation of when the item was donated
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * a setter of an item's timestamp
     * @param timeStamp a new time that a particular item was donated
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * a getter of an items location
     * @return the location an item was donated at
     */
    public String getLocation() {
        return location;
    }

    /**
     * a setter for an items location
     * @param location the location that an item was donated at
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * a getter of an items small description
     * @return the items small description
     */
    public String getSmallDescription() {
        return smallDescription;
    }

    /**
     * a setter for an items small description
     * @param smallDescription the items new small description
     */
    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    /**
     * a getter for an items full description
     * @return the items full description
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * a setter for an items full description
     * @param fullDescription the items new full description
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    /**
     * a getting for an item's value/cost
     * @return the item's value/cost
     */
    public String getValue() {
        return value;
    }

    /**
     * a setter for an items value
     * @param value the item's new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * a getter for an item's category
     * @return the item's category
     */
    public String getCategory() {
        return category;
    }

    /**
     * a setter for an item's category
     * @param category the item's category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
