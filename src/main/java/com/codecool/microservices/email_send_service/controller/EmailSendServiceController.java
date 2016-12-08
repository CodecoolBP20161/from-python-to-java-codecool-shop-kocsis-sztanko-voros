package com.codecool.microservices.email_send_service.controller;

import com.codecool.microservices.email_send_service.service.EmailSendService;
import spark.Request;
import spark.Response;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TimerTask;

public class EmailSendServiceController extends TimerTask{
    public static final String SUBJECT_PARAM_KEY = "subject";
    public static final String EMAIL_PARAM_KEY = "email";
    public static final String USERNAME_PARAM_KEY = "username";
    EmailSendService emailSendService;

    public EmailSendServiceController(EmailSendService emailSendService) {
        this.emailSendService = emailSendService;
    }

    public String getStatus(Request request, Response response) throws MessagingException, IOException {
        return "ok";
    }

    public void run() {
        try {
            emailSendService.sendEmailByTime();
        } catch (IOException | MessagingException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
