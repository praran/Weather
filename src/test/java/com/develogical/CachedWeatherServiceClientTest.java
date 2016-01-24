package com.develogical;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by pradeep on 23/01/2016.
 */
public class CachedWeatherServiceClientTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    public WeatherServiceClient downStreamService = context.mock(WeatherServiceClient.class);


    @Test
    public void retrievesFromCacheWhenDataIsAvailableInCache() {

        WeatherServiceClient cachedWeatherServiceClient = new CachedWeatherServiceClient(downStreamService);
        context.checking(new Expectations() {{
            exactly(1).of(downStreamService).getWeatherForecast("London", DayOfWeek.MONDAY);
            will(returnValue(45));
        }});
        int temp = cachedWeatherServiceClient.getWeatherForecast("London", DayOfWeek.MONDAY);
        assertThat(cachedWeatherServiceClient.getWeatherForecast("London", DayOfWeek.MONDAY), is(temp));
    }

    @Test
    public void evictsOldEntriesWhenMaxCacheSizeReached() {
        WeatherServiceClient cachedWeatherServiceClient = new CachedWeatherServiceClient(downStreamService, 1);

        context.checking(new Expectations() {{
            exactly(2).of(downStreamService).getWeatherForecast("London", DayOfWeek.MONDAY);
            will(returnValue(45));
            exactly(1).of(downStreamService).getWeatherForecast("London", DayOfWeek.TUESDAY);
        }});

        cachedWeatherServiceClient.getWeatherForecast("London", DayOfWeek.MONDAY);
        cachedWeatherServiceClient.getWeatherForecast("London", DayOfWeek.TUESDAY);
        cachedWeatherServiceClient.getWeatherForecast("London", DayOfWeek.MONDAY);
    }
}
