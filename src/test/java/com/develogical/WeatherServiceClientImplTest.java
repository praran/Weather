package com.develogical;

import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pradeep on 23/01/2016.
 */
public class WeatherServiceClientImplTest {

    @Test
    public void retrievesTemperatureForCityAndDay(){
        WeatherServiceClient weatherServiceClient = new WeatherServiceClientImpl();
        int temp = weatherServiceClient.getWeatherForecast("London", DayOfWeek.FRIDAY);
        assertThat(temp,is(greaterThan(-20)));
    }
}
