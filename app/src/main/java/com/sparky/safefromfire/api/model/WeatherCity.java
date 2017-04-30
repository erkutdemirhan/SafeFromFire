package com.sparky.safefromfire.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class WeatherCity {

    @SerializedName("name")
    private String cityName;

    public String getCityName() {
        return cityName;
    }
}
