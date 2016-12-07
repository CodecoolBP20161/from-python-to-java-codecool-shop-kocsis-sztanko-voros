package com.codecool.microservices.email_sender_service;

import com.codecool.microservices.email_sender_service.controller.MailServiceController;
import com.codecool.microservices.email_sender_service.service.MailService;

import java.net.URISyntaxException;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;

public class EmailSenderService {

    private MailServiceController controller;

    public static void main(String[] args) {

        port(60000);

        EmailSenderService application = new EmailSenderService();

        application.controller = new MailServiceController(MailService.getInstance());

        // --- MAPPINGS ---
        get("/email", application.controller::sendEmail);

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
        });
    }
}
