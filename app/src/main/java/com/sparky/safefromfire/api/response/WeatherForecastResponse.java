package com.sparky.safefromfire.api.response;

import com.google.gson.annotations.SerializedName;
import com.sparky.safefromfire.api.model.WeatherCity;
import com.sparky.safefromfire.api.model.WeatherItem;

import java.util.List;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class WeatherForecastResponse {

    private WeatherCity city;

    @SerializedName("list")
    private List<WeatherItem> weatherItemList;

    public WeatherCity getCity() {
        return city;
    }

    public List<WeatherItem> getWeatherItemList() {
        return weatherItemList;
    }
}
