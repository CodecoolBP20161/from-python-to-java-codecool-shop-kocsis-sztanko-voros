package com.codecool.microservices.email_sender_service.service;

public class MailService {
    private static MailService ourInstance = new MailService();

    public static MailService getInstance() {
        return ourInstance;
    }

    private MailService() {
    }
}
