package com.example.email_notification_service.Models;

import com.example.email_notification_service.Services.EmailService;
import com.example.email_notification_service.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Scheduler {

    private final List<String> cities = Arrays.asList("Mumbai", "Bangalore", "Pune");

    @Autowired
    private EmailService emailService;

    @Autowired
    private WeatherService weatherService;

    @Scheduled(fixedRate = 3600000)
    public void notifyUsers() {
        for (String city : cities) {
            WeatherResponse weatherResponse = weatherService.getWeatherByCity(city);
            emailService.sendEmail(weatherResponse);
        }
    }
}