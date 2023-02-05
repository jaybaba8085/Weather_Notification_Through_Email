package com.example.email_notification_service.Services;

import com.example.email_notification_service.Models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(WeatherResponse weatherResponse) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@gmail.com");
        message.setSubject("Weather Update for " + weatherResponse.getCityName());
        message.setText("Temperature: " + weatherResponse.getMainWeather().getTemperature() + "°C, Humidity: " + weatherResponse.getMainWeather().getHumidity() + "%");
        javaMailSender.send(message);
    }
}
