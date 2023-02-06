package com.example.email_notification_service.Controllers;


import com.example.email_notification_service.Models.Scheduler;
import com.example.email_notification_service.Models.User;
import com.example.email_notification_service.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private Scheduler scheduler;

    @GetMapping(value = "/sendmail")
    public void sendmail()
    {
          scheduler.notifyUsers();
    }
}
