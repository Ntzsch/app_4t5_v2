package edu.gatech.cs2340.myapplication.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Stores all the data for the app
 */
public class Database {
    List<Map<String, String>> locationData;
    List<Map<String, String>> inventoryData;
    List<Map<String, String>> userData;

    /**
     * Constructor
     */

    public Database() {

    }

    /**
     * Get Locations
     */
    public List<LocationEntry> getLocations() {
        List<LocationEntry> locationEntryList = new ArrayList<>();
        for (Map<String, String> det: locationData) {
            locationEntryList.add(new LocationEntry(
                    det.get("City"),
                    det.get("Latitude"),
                    det.get("Longitude"),
                    det.get("Name"),
                    det.get("Phone"),
                    det.get("State"),
                    det.get("Street Address"),
                    det.get("Type"),
                    det.get("Website"),
                    det.get("Zip")));
        }
        return locationEntryList;
    }

    public List<InventoryEntry> getInventory() {
        List<InventoryEntry> inventoryEntryList = new ArrayList<>();
        for(Map<String, String> det: inventoryData) {
            inventoryEntryList.add(new InventoryEntry(
                    det.get("timeStamp"),
                    det.get("location"),
                    det.get("smallDescription"),
                    det.get("fullDescription"),
                    det.get("value"),
                    det.get("category")));
        }
        return inventoryEntryList;
    }

    public boolean existInInventory(InventoryEntry entry) {
        Map<String, String> checkEntry = new HashMap<>();
        checkEntry.put("timeStamp", entry.getTimeStamp());
        checkEntry.put("location", entry.getLocation());
        checkEntry.put("smallDescription", entry.getSmallDescription());
        checkEntry.put("fullDescription", entry.getFullDescription());
        checkEntry.put("value", entry.getValue());
        checkEntry.put("category", entry.getCategory());

        for (Map<String, String> map : inventoryData) {
            if (checkEntry.equals(map)) {
                return true;
            }
        }
        return false;

    }




}
