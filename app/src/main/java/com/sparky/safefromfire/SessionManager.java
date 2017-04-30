package com.sparky.safefromfire;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class SessionManager {

    private static final String PREFS_NAME = "SafeFromFirePrefs";
    private static final String IS_REGISTERED_KEY = "is_registered";
    private static final String NAME_KEY = "user_name";
    private static final String AGE_KEY = "user_age";
    private static final String DISABILITIES_KEY = "user_disabilities";
    private static final String ADDRESS_KEY = "user_address";
    private static final String SECONDARY_ADDRESS_KEY = "user_secondary_address";
    private static final String PHONE_KEY = "user_phone";
    private static final String USER_LATITUDE = "user_latitude";
    private static final String USER_LONGITUDE = "user_longitude";

    private static SessionManager instance;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public static SessionManager getInstance(Context context) {
        if(instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    private SessionManager(Context context) {
        prefs  = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public boolean isUserRegistered() {
        return prefs.getBoolean(IS_REGISTERED_KEY, false);
    }

    public String getUserName() {
        return prefs.getString(NAME_KEY, "");
    }

    public String getUserPhone() {
        return prefs.getString(PHONE_KEY, "");
    }

    public String getUserAge() {
        return prefs.getString(AGE_KEY, "");
    }

    public String getUserDisabilities() {
        return prefs.getString(DISABILITIES_KEY, "");
    }

    public String getUserAddress() {
        return prefs.getString(ADDRESS_KEY, "");
    }

    public String getUserSecondaryAddress() {
        return prefs.getString(SECONDARY_ADDRESS_KEY, "");
    }

    public Location getUserLocation() {
        final double latitude  = prefs.getFloat(USER_LATITUDE, 0.0f);
        final double longitude = prefs.getFloat(USER_LONGITUDE, 0.0f);
        Location newLocation   = new Location("");
        newLocation.setLatitude(latitude);
        newLocation.setLongitude(longitude);
        return newLocation;
    }

    public void setUserRegistered(final boolean registered) {
        editor.putBoolean(IS_REGISTERED_KEY, registered).commit();
    }

    public void setUserName(final String userName) {
        editor.putString(NAME_KEY, userName).commit();
    }

    public void setUserPhone(final String userPhone) {
        editor.putString(PHONE_KEY, userPhone).commit();
    }

    public void setUserAge(final String userAge) {
        editor.putString(AGE_KEY, userAge).commit();
    }

    public void setUserDisabilities(final String userDisabilities) {
        editor.putString(DISABILITIES_KEY, userDisabilities).commit();
    }

    public void setUserPrimaryAddress(final String primaryAddress) {
        editor.putString(ADDRESS_KEY, primaryAddress).commit();
    }

    public void setUserSecondaryAddress(final String secondaryAddress) {
        editor.putString(SECONDARY_ADDRESS_KEY, secondaryAddress).commit();
    }

    public void setUserLocation(final Location location) {
        editor.putFloat(USER_LATITUDE, (float) location.getLatitude()).commit();
        editor.putFloat(USER_LONGITUDE, (float) location.getLongitude()).commit();
    }
}
