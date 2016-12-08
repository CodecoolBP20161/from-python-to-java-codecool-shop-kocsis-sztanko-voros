package com.codecool.microservices.email_sender_service.controller;

import com.codecool.microservices.email_sender_service.service.MailService;
import spark.Request;
import spark.Response;

import javax.mail.MessagingException;
import java.io.IOException;

public class MailServiceController {
    public static final String SUBJECT_PARAM_KEY = "subject";
    public static final String EMAIL_PARAM_KEY = "email";
    public static final String USERNAME_PARAM_KEY = "username";
    MailService mailService;

    public MailServiceController(MailService mailService) {
        this.mailService = mailService;
    }

    public String sendEmail(Request request, Response response) throws MessagingException, IOException {
        String subject = request.queryParams(SUBJECT_PARAM_KEY);
        String recipient = request.queryParams(EMAIL_PARAM_KEY);
        String username = request.queryParams(USERNAME_PARAM_KEY);
        mailService.sendMail(subject, recipient, username);
        return "Email sent.";
    }
}
