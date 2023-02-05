package com.example.email_notification_service.Services;

import com.example.email_notification_service.Models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String API_KEY = "YOUR_API_KEY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeatherByCity(String city)
    {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}