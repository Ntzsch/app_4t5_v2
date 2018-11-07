package edu.gatech.cs2340.myapplication.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.controllers.LocationCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.TheCloud;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.functions.FirebaseFunctions;

public class TheCloudTest {

    @Before
    public void setUp() {

    }

    // Raj
    @Test
    public void signIn() {
    }

    // Chloe
    @Test
    public void addInventoryEntry() {
    }

    // Collin
    @Test
    public void registerUser() {
    }

    // Claudia
    @Test
    public void getLocations() {
        // create a bunch of location entries and put in a list

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

        List<LocationEntry> locationEntryList = new ArrayList<>();
        locationEntryList.add(locationEntry1);
        locationEntryList.add(locationEntry2);
        locationEntryList.add(locationEntry3);
        locationEntryList.add(locationEntry4);
        locationEntryList.add(locationEntry5);
        locationEntryList.add(locationEntry6);

        // run TheCloud.getLocations(Callback)
        final LocationCardRecyclerViewAdapter adapter = new
                LocationCardRecyclerViewAdapter(
                new ArrayList<LocationEntry>());

        TheCloud.getLocations(new Callback<List<LocationEntry>>() {
            @Override
            public void callback(List<LocationEntry> value) {
                adapter.updateList(value);
            }
        });
        List<LocationEntry> locationEntryListFromDB = adapter.getmLocationList();

        // assertEquals(locationEntryList, locationEntryListFromDB);
    }

    // Reece?
    @Test
    public void getInventory() {
    }
}