package com.develogical;

import com.weather.Day;
import com.weather.Forecaster;
import com.weather.Region;

import java.time.DayOfWeek;

/**
 * Created by pradeep on 23/01/2016.
 */
public class WeatherServiceClientImpl implements WeatherServiceClient {
    @Override
    public int getWeatherForecast(String place, DayOfWeek day) {
        return new Forecaster().forecastFor(Region.valueOf(place.toUpperCase()), Day.valueOf(day.name().toUpperCase()))
                .temperature();
    }
}
