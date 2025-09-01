package eku.EcommerceApp.controller;

import eku.EcommerceApp.model.DtoNew;
import eku.EcommerceApp.model.DtoWeather;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private static final String API_KEY = "dceec579045f4891b0b100743252808";

    @GetMapping("/{city}")
    public Object getWeather(@PathVariable String city) {

        String url = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + city;
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        Map<String, Object> location = (Map<String, Object>) response.get("location");

        Map<String, Object> current = (Map<String, Object>) response.get("current");

        Map<String, Object> result = new HashMap<>();

        DtoNew d=new DtoNew(
                (String) current.get("last_updated"),
                 (String) current.get("last_updated")
                );

        String name = (String) location.get("name");
        String region = (String) location.get("region");
        String country = (String) location.get("country");

        return response;


    }
}
