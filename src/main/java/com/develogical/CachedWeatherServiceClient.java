package com.develogical;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by pradeep on 23/01/2016.
 */
public class CachedWeatherServiceClient implements WeatherServiceClient {


    private WeatherServiceClient downStreamService;
    Map<Pair<String, DayOfWeek> , Integer> cache;
    private int size;

    public CachedWeatherServiceClient(WeatherServiceClient downStreamService) {
        this(downStreamService, 10000);
    }

    public CachedWeatherServiceClient(WeatherServiceClient downStreamService, final int size) {
        this.downStreamService = downStreamService;
        this.size = size;
        this.cache = new LinkedHashMap<Pair<String, DayOfWeek>, Integer>(){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > size;
            }
        };
    }

    @Override
    public int getWeatherForecast(String place, DayOfWeek day) {
        Pair key = new Pair(place, day);
        if(!cache.containsKey(key)){
           cache.put(key, downStreamService.getWeatherForecast(place, day));
       }
        return cache.get(key);
    }
}
