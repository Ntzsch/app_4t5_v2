package edu.gatech.cs2340.myapplication.models;

import android.provider.ContactsContract;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class Cloud {
    /*
    1) signin
    2) register
    3) get locations
    4) add inventory item
    5) get inventory items
     */


    public void signIn(String username, String password) {

    }

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
        VIEW_LOCATIONS, EDIT_LOCATIONS, VIEW_INVENTORY, EDIT_INVENTORY, LOGIN, LOGOUT, REGISTER_USER;
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

    public static void getLocation(LocationEntry locationEntry) {
        // Case where the dummy item is null
        // return null if item not found
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
