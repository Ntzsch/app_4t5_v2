package edu.gatech.cs2340.myapplication;

import android.view.Menu;

import org.junit.Test;
import org.junit.Before;

import edu.gatech.cs2340.myapplication.controllers.InventoryCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.controllers.LocationCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.Database;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;
import edu.gatech.cs2340.myapplication.models.LocationEntry;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import edu.gatech.cs2340.myapplication.models.Cloud;
import edu.gatech.cs2340.myapplication.models.User;

/**
 * JUnit testing for the cloud
 */
public class TheCloudTest {

    List<LocationEntry> mLocationList;
    List<InventoryEntry> mInventoryList;
    Database db;
    User mGuestUser;
    User mAdminUser;
    User mManagerUser;
    User mEmployeeUser;

    @Before
    public void setUp() {
        // Make Users
        mGuestUser = new User("claudia", "1234", User.Type.GUEST );
        mAdminUser = new User("chloe", "1234", User.Type.ADMIN );
        mManagerUser = new User("collin", "1234", User.Type.MANAGER);
        mEmployeeUser = new User("raj", "1234", User.Type.EMPLOYEE);

        // Initialize real data
        LocationEntry locationEntry1 = new LocationEntry("Atlanta",
                "33.75416",
                "-84.37742",
                "AFD Station 4",
                "(404) 555 - 3456",
                "GA",
                "309 EDGEWOOD AVE SE",
                "Drop Off",
                "www.afd04.atl.ga",
                "30332");
        LocationEntry locationEntry2 = new LocationEntry("Atlanta",
                "33.73182",
                "-84.43971",
                "BOYS & GILRS CLUB W.W. WOOLFOLK",
                "(404) 555 - 1234",
                "GA",
                "1642 RICHLAND RD",
                "Store",
                "www.bgc.wool.ga",
                "30332");
        LocationEntry locationEntry3 = new LocationEntry("Atlanta",
                "33.70866",
                "-84.41853",
                "PATHWAY UPPER ROOM CHRISTIAN MINISTRIES",
                "(404) 555 - 5432",
                "GA",
                "1683 SYLVAN RD",
                "Warehouse",
                "www.pathways.org",
                "30332");
        LocationEntry locationEntry4 = new LocationEntry("SCOTTDALE",
                "33.80129",
                "-84.25537",
                "PAVILION OF HOPE INC",
                "(404) 555 - 8765",
                "GA",
                "3558 EAST PONCE DE LEON AVE",
                "Warehouse",
                "www.pavhope.org",
                "30079");
        LocationEntry locationEntry5 = new LocationEntry("DECATUR",
                "33.71747",
                "-84.2521",
                "D&D CONVENIENCE STORE",
                "(404) 555 - 9876",
                "GA",
                "2426 COLUMBIA DRIVE",
                "Drop Off",
                "www.ddconv.com",
                "30034");
        LocationEntry locationEntry6 = new LocationEntry("Sandy Springs",
                "33.96921",
                "-84.3688",
                "KEEP NORTH FULTON BEAUTIFUL",
                "(770) 555 - 7321",
                "GA",
                "470 MORGAN FALLS RD",
                "Store",
                "www.knfb.org",
                "30302");
        mLocationList = new ArrayList<>();
        mLocationList.add(locationEntry1);
        mLocationList.add(locationEntry2);
        mLocationList.add(locationEntry3);
        mLocationList.add(locationEntry4);
        mLocationList.add(locationEntry5);
        mLocationList.add(locationEntry6);

        // Inventory
        InventoryEntry item1 = new InventoryEntry("time",
                "location",
                "Item Name",
                "descriptions", "3",
                "Category");
        InventoryEntry item2 = new InventoryEntry("time",
                "location",
                "Item Name",
                "descriptions", "3",
                "Category");
        InventoryEntry item3 = new InventoryEntry("time",
                "location",
                "Item Name",
                "descriptions", "3",
                "Category");
        InventoryEntry item4 = new InventoryEntry("time",
                "location",
                "Item Name",
                "descriptions", "3",
                "Category");

        mInventoryList = new ArrayList<>();
        mInventoryList.add(item1);
        mInventoryList.add(item2);
        mInventoryList.add(item3);
        mInventoryList.add(item4);
    }

    @Test
    public void testGetLocations() {
        List<LocationEntry> dbLocations = db.getLocations();
        assertEquals(dbLocations, mLocationList);
    }

    @Test
    public void testUserOptions() {
        // expected guest menu options
        Set<Cloud.MenuOptions> menuOptions = new HashSet<>();
        menuOptions.add(Cloud.MenuOptions.VIEW_LOCATIONS);
        menuOptions.add(Cloud.MenuOptions.VIEW_INVENTORY);
        assertEquals(menuOptions, Cloud.getMenuOptions(mGuestUser));

        // expected employee menu options
        menuOptions.add(Cloud.MenuOptions.EDIT_INVENTORY);
        assertEquals(menuOptions, Cloud.getMenuOptions(mEmployeeUser));

        // expected manager menu options
        menuOptions.add(Cloud.MenuOptions.EDIT_LOCATIONS);
        assertEquals(menuOptions, Cloud.getMenuOptions(mManagerUser));

        menuOptions.add(Cloud.MenuOptions.REGISTER_USER);
        assertEquals(menuOptions, Cloud.getMenuOptions(mAdminUser));
    }

    @Test
    public void testAddItem() {

    }

    @Test
    public void testAddLocation() {

    }

    @Test
    public void testRegisterUser() {

    }

    @Test
    public void testSearchItem() {

    }

    @Test
    public void testSearchLocation() {

    }



}


