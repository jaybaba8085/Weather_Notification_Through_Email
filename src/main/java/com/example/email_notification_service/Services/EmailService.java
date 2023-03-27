package com.example.email_notification_service.Services;

import com.example.email_notification_service.Models.User;
import com.example.email_notification_service.Models.WeatherResponse;
import com.example.email_notification_service.Repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Properties;


@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;


    @Autowired
    private RestTemplate restTemplate;

    public void sendEmail(User user)
    {

                 String city = user.getCity();
                 WeatherResponse weather = weatherService.getWeatherByCity(city);
                 sendEmail(user.getEmail(), weather);

    }
    private void sendEmail(String email, WeatherResponse weather) {
        // Use the JavaMailSender to send an email with the weather information

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("icosmiccoder@gmail.com");
            helper.setTo(email);
            helper.setSubject("Weather Update for " + weather.getCityName());

            String htmlTemplate = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "  <head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Weather Report</title>\n" +
                    "    <style>\n" +
                    "      .container {\n" +
                    "        display: flex;\n" +
                    "        flex-direction: column;\n" +
                    "        align-items: center;\n" +
                    "        padding: 20px;\n" +
                    "      }\n" +
                    "      .header {\n" +
                    "        font-size: 36px;\n" +
                    "        font-weight: bold;\n" +
                    "        margin-bottom: 20px;\n" +
                    "      }\n" +
                    "      .weather-details {\n" +
                    "        display: flex;\n" +
                    "        flex-direction: row;\n" +
                    "        justify-content: space-between;\n" +
                    "        width: 80%;\n" +
                    "        margin-bottom: 20px;\n" +
                    "      }\n" +
                    "      .weather-details p {\n" +
                    "        font-size: 24px;\n" +
                    "      }\n" +
                    "      .weather-description {\n" +
                    "        margin-top: 20px;\n" +
                    "        font-size: 18px;\n" +
                    "      }\n" +
                    "    </style>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div class=\"container\">\n" +
                    "      <div class=\"header\">Weather Report for {{cityName}}</div>\n" +
                    "      <div class=\"weather-details\">\n" +
                    "        <p>Temperature: {{temperature}}°C</p>\n" + "  " +
                    "        <p> Humidity: {{humidity}}%</p>\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>";

            htmlTemplate = htmlTemplate.replace("{{cityName}}",weather.getCityName())
                    .replace("{{temperature}}", String.valueOf(weather.getMainWeather().getTemperature()))
                    .replace("{{humidity}}",String.valueOf(weather.getMainWeather().getHumidity()));

                helper.setText(htmlTemplate, true);


                javaMailSender.send(message);
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
