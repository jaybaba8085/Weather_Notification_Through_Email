package com.example.email_notification_service.Services;

import com.example.email_notification_service.Models.User;
import com.example.email_notification_service.Models.WeatherResponse;
import com.example.email_notification_service.Repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherService weatherService;


    public void sendNotifications() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String city = user.getCity();
           WeatherResponse weather = weatherService.getWeatherByCity(city);
            sendEmail(user.getEmail(), String.valueOf(weather));
        }
    }

    private void sendEmail(String email, String weather) {
        // Use the JavaMailSender to send an email with the weather information
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setSubject("Weather Update");
            helper.setText("The weather in your city is: " + weather, true);
        } catch (MessagingException e) {
            e.printStackTrace();


        }
    }
}
