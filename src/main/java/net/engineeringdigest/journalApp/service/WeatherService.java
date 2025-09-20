package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.weatherResponse.WeatherResponsePOJO;
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

    @Value("${weather.api.key}")
    private String apiKey;

    private static String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public WeatherResponsePOJO getWeather(String city){
        String finalKey=API.replace("API_KEY",apiKey).replace("CITY",city);
        ResponseEntity<WeatherResponsePOJO> response = restTemplate.exchange(finalKey, HttpMethod.GET, null, WeatherResponsePOJO.class);
        WeatherResponsePOJO body = response.getBody();
        return body;
    }
}
