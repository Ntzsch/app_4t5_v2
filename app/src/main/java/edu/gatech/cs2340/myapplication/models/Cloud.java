package edu.gatech.cs2340.myapplication.models;

import java.util.ArrayList;
import java.util.List;

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
    public static List<MenuOptions> getMenuOptions(User user) {
        List<MenuOptions> displayOptionsList = new ArrayList<>();
        User.Type type = user.getType();

        switch (type) {
            case ADMIN:
                displayOptionsList.add(MenuOptions.REGISTER_USER);
            case MANAGER:
                displayOptionsList.add(MenuOptions.EDIT_LOCATIONS);
            case EMPLOYEE:
                displayOptionsList.add(MenuOptions.EDIT_INVENTORY);
            case GUEST:
                displayOptionsList.add(MenuOptions.VIEW_LOCATIONS);
                displayOptionsList.add(MenuOptions.VIEW_INVENTORY);
        }
        return displayOptionsList;
    }

    public enum MenuOptions {
        VIEW_LOCATIONS, EDIT_LOCATIONS, VIEW_INVENTORY, EDIT_INVENTORY, LOGIN, LOGOUT, REGISTER_USER;
    }

    public static void addLocation(LocationEntry locationEntry) {

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
