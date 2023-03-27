package com.example.email_notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication

@EnableScheduling
public class EmailNotificationServiceApplication extends SpringServletContainerInitializer {

	public static void main(String[] args) {

		SpringApplication.run(EmailNotificationServiceApplication.class, args);
	}

}
