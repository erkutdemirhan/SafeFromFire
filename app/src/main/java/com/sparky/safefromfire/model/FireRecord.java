package com.sparky.safefromfire.model;

import android.location.Location;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class FireRecord {

    private Location location;
    private String date;

    public FireRecord(Location location, String date) {
        this.location = location;
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
