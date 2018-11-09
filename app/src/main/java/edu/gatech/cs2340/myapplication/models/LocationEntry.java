package edu.gatech.cs2340.myapplication.models;

import java.util.ArrayList;
import java.util.List;

/**
 * a class of location entries
 * represents the various attributes that a location entry might have
 */
public class LocationEntry {
    public final String city;
    public final String latitude;
    public final String longitude;
    public final String name;
    public final String phone;
    public final String state;

    /**
     * a getter for a location's city
     * @return the city the location is located in
     */
    public String getCity() {
        return city;
    }

    /**
     * a getter for the location's latitude
     * @return the location's latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * a getter for a locations longitude
     * @return the location's longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * a getter for a locaiton's name
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * a getter for the phone number of a location
     * @return the phone number of a location
     */
    public String getPhone() {
        return phone;
    }

    /**
     * a getter for the state the location is in
     * @return the state the location resides in
     */
    public String getState() {
        return state;
    }

    /**
     * the getter for a location's street address
     * @return the location's street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * a getter for the location's type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * a getter for the location's website
     * @return the location's website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * a getter for the location's zip code
     * @return the location's zip code
     */
    public String getZip() {
        return zip;
    }

    public final String streetAddress;
    public final String type;
    public final String website;
    public final String zip;

    /*
    public LocationEntry() {
        this.city = "a";
        this.latitude = "b";
        this.longitude = "c";
        this.name = "d";
        this.phone = "e";
        this.state = "f";
        this.streetAddress = "g";
        this.type = "h";
        this.website = "i";
        this.zip = "j";
    } */

    /**
     * a constructor for a location entry
     * @param city the city a location is in
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     * @param name the name of the location
     * @param phone the phone number of the location
     * @param state the state of the location
     * @param streetAddress the street address of the location
     * @param type the type of the location
     * @param website the website of the locaiton
     * @param zip the zip code of the location
     */
    public LocationEntry(String city, String latitude, String longitude,
                         String name, String phone,
                         String state, String streetAddress, String type,
                         String website,
                         String zip) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.streetAddress = streetAddress;
        this.type = type;
        this.website = website;
        this.zip = zip;
    }
}
