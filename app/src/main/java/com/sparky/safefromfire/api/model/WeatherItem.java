package com.sparky.safefromfire.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class WeatherItem {

    private double pressure;

    @SerializedName("humidity")
    private int humidityPercentage;

    @SerializedName("speed")
    private double windSpeed;

    @SerializedName("deg")
    private double windDirectionDegree;

    @SerializedName("temp")
    private WeatherTemp temperature;

    @SerializedName("rain")
    private double rainAmount;

    public double getPressure() {
        return pressure;
    }

    public int getHumidityPercentage() {
        return humidityPercentage;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirectionDegree() {
        return windDirectionDegree;
    }

    public WeatherTemp getTemperature() {
        return temperature;
    }

    public double getRainAmount() {
        return rainAmount;
    }
}
