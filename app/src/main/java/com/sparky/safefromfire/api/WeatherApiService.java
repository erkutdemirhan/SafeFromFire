package com.sparky.safefromfire.api;

import com.sparky.safefromfire.api.response.WeatherForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public interface WeatherApiService {

    @GET("forecast/daily")
    Call<WeatherForecastResponse> getWeatherForecastResults(@Query("appid") String apiKey,
                                                            @Query("lat") double latitude,
                                                            @Query("lon") double longitude,
                                                            @Query("mode") String responseType,
                                                            @Query("cnt") int dayCount,
                                                            @Query("units") String responseUnits);
}
