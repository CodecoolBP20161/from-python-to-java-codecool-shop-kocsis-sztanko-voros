package com.codecool.microservices.email_send_service;

import com.codecool.microservices.email_send_service.controller.EmailSendServiceController;

import java.net.URISyntaxException;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;

public class EmailSendService {

    private EmailSendServiceController controller;

    public static void main(String[] args) {

        port(60000);

        EmailSendService application = new EmailSendService();

        application.controller = new EmailSendServiceController(com.codecool.microservices.email_send_service.service.EmailSendService.getInstance());

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
