package edu.gatech.cs2340.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class LocationEntry {
    public final String city;
    public final String latitude;
    public final String longitude;
    public final String name;
    public final String phone;
    public final String state;
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
