package com.example.userapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendWelcomeEmail(String name, String email) {
        // Simulate sending email
        try {
            logger.info("Simulating sending email to {} <{}>", name, email);
            Thread.sleep(2000); // simulate time-consuming email send
            logger.info("Welcome email sent to {} <{}>", name, email);
        } catch (InterruptedException e) {
            logger.error("Failed to send email to {} <{}>", name, email);
            Thread.currentThread().interrupt();
        }
    }
}