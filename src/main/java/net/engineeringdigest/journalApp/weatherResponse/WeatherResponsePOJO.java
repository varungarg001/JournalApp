package net.engineeringdigest.journalApp.weatherResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Data
@NoArgsConstructor
public class WeatherResponsePOJO {
    private Current current;

    @Getter
    @Setter
    public static class Current{
        @JsonProperty("weather_description")
        private ArrayList<String>weatherDescription;

        private int temperature;
        private int feelslike;

        @JsonProperty("is_day")
        private String isDay;
    }




}
