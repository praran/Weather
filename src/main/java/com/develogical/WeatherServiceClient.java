package com.develogical;

import java.time.DayOfWeek;

/**
 * Created by pradeep on 23/01/2016.
 */
public interface WeatherServiceClient {
    int getWeatherForecast(String place, DayOfWeek day);
}
