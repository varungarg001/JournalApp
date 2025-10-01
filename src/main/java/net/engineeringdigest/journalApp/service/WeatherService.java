package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.appcache.AppCache;
import net.engineeringdigest.journalApp.constants.PlaceHolders;
import net.engineeringdigest.journalApp.entity.WeatherResponsePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    private String apiKey;


    public WeatherResponsePOJO getWeather(String city){
        String finalKey=appCache.getAppCacheMap().get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.API_KEY.getValue(), apiKey).replace(PlaceHolders.CITY.getValue(),city);
        ResponseEntity<WeatherResponsePOJO> response = restTemplate.exchange(finalKey, HttpMethod.GET, null, WeatherResponsePOJO.class);
        return response.getBody();
    }
}
