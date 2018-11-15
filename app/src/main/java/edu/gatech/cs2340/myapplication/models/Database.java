package edu.gatech.cs2340.myapplication.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores all the data for the app
 */
public class Database {
    public List<Map<String, String>> locationData;
    public List<Map<String, String>> inventoryData;
    public List<Map<String, String>> userData;

    /**
     * Constructor
     */

    public Database(List<User> userList, List<InventoryEntry> inventoryList,
                    List<LocationEntry> locationList) {
        locationData = new ArrayList<>();
        inventoryData = new ArrayList<>();
        userData = new ArrayList<>();

        for (InventoryEntry entry : inventoryList) {
            Map<String, String> newMap = new HashMap<>();
            newMap.put("timeStamp", entry.getTimeStamp());
            newMap.put("location", entry.getLocation());
            newMap.put("smallDescription", entry.getSmallDescription());
            newMap.put("fullDescription", entry.getFullDescription());
            newMap.put("value", entry.getValue());
            newMap.put("category", entry.getCategory());
            inventoryData.add(newMap);
        }

        for (LocationEntry entry : locationList) {
            Map<String, String> newMap = new HashMap<>();
            newMap.put("City", entry.getCity());
            newMap.put("Latitude", entry.getLatitude());
            newMap.put("Longitude", entry.getLongitude());
            newMap.put("Name", entry.getName());
            newMap.put("Phone", entry.getPhone());
            newMap.put("State", entry.getState());
            newMap.put("Street Address", entry.getStreetAddress());
            newMap.put("Type", entry.getType());
            newMap.put("Website", entry.getWebsite());
            newMap.put("Zip", entry.getZip());
            locationData.add(newMap);
        }

        for (User user : userList) {
            Map<String, String> newMap = new HashMap<>();
            newMap.put("username", user.getUsername());
            newMap.put("password", user.getPassword());
            newMap.put("type", user.getType().toString());
            userData.add(newMap);
        }
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

    public boolean existInLocations(LocationEntry entry) {
        Map<String, String> checkEntry = new HashMap<>();
        checkEntry.put("City", entry.getCity());
        checkEntry.put("Latitude", entry.getLatitude());
        checkEntry.put("Longitude", entry.getLongitude());
        checkEntry.put("Name", entry.getName());
        checkEntry.put("Phone", entry.getPhone());
        checkEntry.put("State", entry.getState());
        checkEntry.put("Street Address", entry.getStreetAddress());
        checkEntry.put("Type", entry.getType());
        checkEntry.put("Website", entry.getWebsite());
        checkEntry.put("Zip", entry.getZip());

        for (Map<String, String> map : locationData) {
            if (checkEntry.equals(map)) {
                return true;
            }
        }
        return false;
    }

    public boolean existInUsers(User user) {
        Map<String, String> checkEntry = new HashMap<>();
        checkEntry.put("username", user.getUsername());
        checkEntry.put("password", user.getPassword());
        checkEntry.put("type", user.getType().toString());

        for (Map<String, String> map : userData) {
            if (checkEntry.equals(map)) {
                return true;
            }
        }
        return false;
    }

    public void addLocationEntry(Map<String, String> locationEntryMap) {
        locationData.add(locationEntryMap);
    }

    public void addInventoryEntry(Map<String, String> inventoryEntryMap) {
        inventoryData.add(inventoryEntryMap);
    }

    public void addUser(Map<String, String> userMap) {
        userData.add(userMap);
    }
}
