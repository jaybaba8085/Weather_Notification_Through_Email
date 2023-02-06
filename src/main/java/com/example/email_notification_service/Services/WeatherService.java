package com.example.email_notification_service.Services;

import com.example.email_notification_service.Models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static final String API_KEY = "1cd484c55ed6a26693398bcae22720c1";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeatherByCity(String city)
    {
        String url = BASE_URL + "q=" + city + "&appid=" + API_KEY;
        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
        return weatherResponse;
    }

}