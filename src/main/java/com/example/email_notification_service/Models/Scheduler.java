package com.example.email_notification_service.Models;

import com.example.email_notification_service.Repositories.UserRepository;
import com.example.email_notification_service.Services.EmailService;
import com.example.email_notification_service.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class Scheduler {

    @Autowired
    private EmailService emailService;


    @Autowired
    private WeatherService weatherService;
    @Autowired
    private UserRepository userRepository;

    //@Scheduled(cron = "0 */2 * * * *")
    @Scheduled(cron = "0 20 23 23 3 *")//(fixedRate = 3600000)
    public void notifyUsers()
    {
        List<User> users = userRepository.findAll();

        for (User user : users)
        {
            emailService.sendEmail(user);
        }
    }
}


