package com.sparky.safefromfire.screens;

import android.content.Intent;
import android.graphics.Paint;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.SessionManager;
import com.sparky.safefromfire.api.ApiServiceFactory;
import com.sparky.safefromfire.api.WeatherApiService;
import com.sparky.safefromfire.api.model.WeatherCity;
import com.sparky.safefromfire.api.model.WeatherItem;
import com.sparky.safefromfire.api.response.WeatherForecastResponse;
import com.sparky.safefromfire.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class WeatherForecastActivity extends BaseActivity {

    @BindView(R.id.weatherforecast_temperature)
    TextView temperatureTw;

    @BindView(R.id.weatherforecast_location)
    TextView locationTw;

    @BindView(R.id.weatherforecast_wind_direction)
    TextView windDirectionTw;

    @BindView(R.id.weatherforecast_humidity)
    TextView humidityTw;

    @BindView(R.id.weatherforecast_rain)
    TextView rainAmountTw;

    @BindView(R.id.weatherforecast_probability)
    TextView fireProbabilityTw;

    @BindView(R.id.weatherforecast_firemap)
    TextView fireMapTw;

    private WeatherApiService weatherApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherforecast);
        weatherApiService = ApiServiceFactory.createWeatherApiService();
        getWeatherData();
    }

    private void getWeatherData() {
        final Location userLocation = SessionManager.getInstance(WeatherForecastActivity.this).getUserLocation();
        Call<WeatherForecastResponse> call = weatherApiService.getWeatherForecastResults(getString(R.string.weather_api_key),
            userLocation.getLatitude(),
            userLocation.getLongitude(),
            getString(R.string.weather_api_response_type),
            7,
            getString(R.string.weather_response_unit));
        call.enqueue(new Callback<WeatherForecastResponse>() {
            @Override
            public void onResponse(Call<WeatherForecastResponse> call, Response<WeatherForecastResponse> response) {
                WeatherForecastResponse weatherForecastResponse = response.body();
                populateWeatherForecast(weatherForecastResponse);
            }

            @Override
            public void onFailure(Call<WeatherForecastResponse> call, Throwable t) {
            }
        });
    }

    private void populateWeatherForecast(final WeatherForecastResponse weatherForecastResponse) {
        final WeatherCity cityInfo          = weatherForecastResponse.getCity();
        final WeatherItem todaysWeatherInfo = weatherForecastResponse.getWeatherItemList().get(0);
        locationTw.setText("Location: " + cityInfo.getCityName());
        temperatureTw.setText("Temperature: " + todaysWeatherInfo.getTemperature().getMin() + " \u00b0C");
        humidityTw.setText("Humidity: " + todaysWeatherInfo.getHumidityPercentage() + "%");
        windDirectionTw.setText("Wind direction: " + todaysWeatherInfo.getWindDirectionDegree() + "\u00b0");
        rainAmountTw.setText("Rain amount: " + todaysWeatherInfo.getRainAmount() + " mm");
    }

    @Override
    protected void setupViews() {
        fireMapTw.setPaintFlags(fireMapTw.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.weatherforecast_backbutton)
    public void backButtonPressed() {
        finish();
    }

    @OnClick(R.id.weatherforecast_firemap)
    public void onFireMapTextPressed() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firms.modaps.eosdis.nasa.gov/firemap/"));
        startActivity(browserIntent);
    }
}
