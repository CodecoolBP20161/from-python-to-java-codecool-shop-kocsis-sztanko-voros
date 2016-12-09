package com.codecool.microservices.email_send_service.controller;

import com.codecool.microservices.email_send_service.service.EmailSendService;
import spark.Request;
import spark.Response;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TimerTask;

public class EmailSendServiceController extends TimerTask{
    EmailSendService emailSendService;

    public EmailSendServiceController(EmailSendService emailSendService) {
        this.emailSendService = emailSendService;
    }

    public String getStatus(Request request, Response response) throws MessagingException, IOException {
        return "ok";
    }

    public void run() {
        try {
            emailSendService.sendEmailOrders();
        } catch (IOException | MessagingException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
