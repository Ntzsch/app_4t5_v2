package edu.gatech.cs2340.myapplication.models;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class Cloud {

    // Menu item options -> put out a list of menu items
    public static Set<MenuOptions> getMenuOptions(User user) {
        Set<MenuOptions> displayOptionsSet = new HashSet<>();
        User.Type type = user.getType();

        switch (type) {
            case ADMIN:
                displayOptionsSet.add(MenuOptions.REGISTER_USER);
            case MANAGER:
                displayOptionsSet.add(MenuOptions.EDIT_LOCATIONS);
            case EMPLOYEE:
                displayOptionsSet.add(MenuOptions.EDIT_INVENTORY);
            case GUEST:
                displayOptionsSet.add(MenuOptions.VIEW_LOCATIONS);
                displayOptionsSet.add(MenuOptions.VIEW_INVENTORY);
        }
        return displayOptionsSet;
    }

    /**
     * Set menu items to force certain users particular accessibility
     */
    public enum MenuOptions {
        VIEW_LOCATIONS, EDIT_LOCATIONS, VIEW_INVENTORY, EDIT_INVENTORY, REGISTER_USER
    }

    public static void addLocation(LocationEntry locationEntry, Database db) {
        Map<String, String> locationEntryMap = new HashMap<>();
        locationEntryMap.put("City", locationEntry.getCity());
        locationEntryMap.put("Latitude", locationEntry.getLatitude());
        locationEntryMap.put("Longitude", locationEntry.getLongitude());
        locationEntryMap.put("Name", locationEntry.getName());
        locationEntryMap.put("Phone", locationEntry.getPhone());
        locationEntryMap.put("State", locationEntry.getState());
        locationEntryMap.put("Street Address", locationEntry.getStreetAddress());
        locationEntryMap.put("Type", locationEntry.getType());
        locationEntryMap.put("Website", locationEntry.getWebsite());
        locationEntryMap.put("Zip", locationEntry.getZip());
        db.addLocationEntry(locationEntryMap);
    }



    public static void addInventoryEntry(InventoryEntry inventoryEntry, Database db) {
        Map<String, String> inventoryEntryMap = new HashMap<>();
        inventoryEntryMap.put("timeStamp", inventoryEntry.getTimeStamp());
        inventoryEntryMap.put("location", inventoryEntry.getLocation());
        inventoryEntryMap.put("smallDescription", inventoryEntry.getSmallDescription());
        inventoryEntryMap.put("fullDescription", inventoryEntry.getFullDescription());
        inventoryEntryMap.put("value", inventoryEntry.getValue());
        inventoryEntryMap.put("category", inventoryEntry.getCategory());

        db.addInventoryEntry(inventoryEntryMap);
    }

    public static void registerUser(User user, Database db) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());
        userMap.put("password", user.getPassword());
        userMap.put("type", user.getType().toString());
        db.addUser(userMap);
    }


    public static LocationEntry searchLocation(String locationEntry, Database db) {
        for (Map<String, String> map : db.locationData) {
            if (map.containsValue(locationEntry)) {
                return new LocationEntry(map.get("City"), map.get("Latitude")
                        ,map.get("Longitude"), map.get("Name"), map.get("Phone"), map.get("State"), map.get("Street Address")
                        ,map.get("Type"), map.get("Website"), map.get("Zip"));
            }
        }
        return null;
    }

    public static InventoryEntry searchInventory(String inventoryEntry, Database db) {
        for (Map<String, String> map : db.inventoryData) {
            if (map.containsValue(inventoryEntry)) {
                return new InventoryEntry(map.get("timeStamp"), map.get("location")
                        ,map.get("smallDescription"), map.get("fullDescription"), map.get("value"), map.get("category"));
            }
        }
        return null;
    }


    // Add location and test that it's in the Database
        // isThere method in Database class

    // Add Item and test it's in inventory database
        // isThere method
    // Search Item: is there or not
        // return null if not found, else returns the item's data
    // Search Locations:
        // return null if not found, else returns the location's data
    // Register User
        // check user is there through isThere method in database class




}
